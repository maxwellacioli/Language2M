package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.SizeTPointer;
import semantic.Symbol;
import semantic.SymbolTable;
import semantic.VarType;
import semantic.commands.Node;
import semantic.commands.Printout;

import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.javacpp.LLVM.*;

public class OpBinaryConc extends OpBinary {

    public OpBinaryConc(Token tk, Node exp1, Node exp2) {
        super(tk, exp1, exp2);
    }
 
    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        if(!LLVMConfiguration.getStrCodeFlag()) {
            //Verifica se os operandos são do tipo text, caso um não seja, a analise é encerrada
            if(checkTextType(getChildren().get(0), getChildren().get(1))) {
                System.err.println("Tipos dos operandos do operador " + "<'" + getToken().getLexValue() + "'> são incompatíveis | " + getToken().getLocation());
                System.exit(1);
            }

            LLVMValueRef left = getChildren().get(0).getLlvmValueRef();
            LLVMValueRef right = getChildren().get(1).getLlvmValueRef();

            LLVMTypeRef leftType = LLVMTypeOf(left);
            LLVMTypeRef rightType = LLVMTypeOf(right);

            //recebe o tamanho das strings
            int leftLength = LLVMGetArrayLength(leftType);
            int rightLength = LLVMGetArrayLength(rightType);


            List<LLVMValueRef> maskElemList = new ArrayList<LLVMValueRef>();
            LLVMValueRef appendedVectors;

            if (leftLength >= rightLength) {
                int totalLength = leftLength + rightLength;
                for(int i = 0 ; i < leftLength - 1 ; i++) {
                    maskElemList.add(LLVMConstInt(LLVMInt32Type(), i, 0));
                }

                for(int i = leftLength ; i < totalLength ; i++ ) {
                    maskElemList.add(LLVMConstInt(LLVMInt32Type(), i, 0));
                }

                LLVMValueRef[] maskElemArray = new LLVMValueRef[maskElemList.size()];
                maskElemList.toArray(maskElemArray);

                LLVMValueRef mask = LLVMConstVector(new PointerPointer(maskElemArray), maskElemArray.length);

                appendedVectors = LLVMBuildShuffleVector(builderRef, left, right, mask, "appendedVectors");

            } else {
                for(int i = 0 ; i < leftLength-1 ; i++) {
                    maskElemList.add(LLVMConstInt(LLVMInt32Type(), rightLength + i, 0));
                }

                for(int i = 0 ; i < rightLength ; i++ ) {
                    maskElemList.add(LLVMConstInt(LLVMInt32Type(), i, 0));
                }

                LLVMValueRef[] maskElemArray = new LLVMValueRef[maskElemList.size()];
                maskElemList.toArray(maskElemArray);

                LLVMValueRef mask = LLVMConstVector(new PointerPointer(maskElemArray), maskElemArray.length);

                appendedVectors = LLVMBuildShuffleVector(builderRef, right, left, mask, "appendedVectors");
            }
            System.out.println(">>>>>>>>> "+ LLVMPrintValueToString(appendedVectors).getString());
            return  appendedVectors;
        }
        return  null;
    }

    private boolean checkTextType(Node left, Node right) {
        Exp leftOperand = (Exp)left;
        Exp rightOperand = (Exp)right;

        if(!leftOperand.getType().equals(VarType.TEXT) || !rightOperand.getType().equals(VarType.TEXT)) {
            return true;
        }

        return false;
    }
}
