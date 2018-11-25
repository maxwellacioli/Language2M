package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import org.bytedeco.javacpp.PointerPointer;
import semantic.SymbolTable;
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

            LLVMValueRef left = getChildren().get(0).getLlvmValueRef();
            LLVMValueRef right = getChildren().get(1).getLlvmValueRef();

            LLVMTypeRef leftType = LLVMTypeOf(left);
            LLVMTypeRef rightType = LLVMTypeOf(right);

            //pega o tamanho das strings
            int leftLength = LLVMGetArrayLength(leftType);
            int rightLength = LLVMGetArrayLength(rightType);

            //TODO VERIFICAR SE SÃO STRINGS

            List<LLVMValueRef> maskElemList = new ArrayList<LLVMValueRef>();
            LLVMValueRef tempVector;
            LLVMValueRef tempArray;
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

                tempArray = LLVMConstArray(LLVMInt8Type(), appendedVectors , maskElemArray.length);
    //            tempVector = LLVMBuildAlloca(builderRef, LLVMVectorType(LLVMInt8Type(), maskElemArray.length), "tempVector");
    //            LLVMBuildStore(builderRef, appendedVectors, tempVector);

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

                tempArray = LLVMConstArray(LLVMInt8Type(), appendedVectors , maskElemArray.length);
    //            tempVector = LLVMBuildAlloca(builderRef, LLVMVectorType(LLVMInt8Type(), maskElemArray.length), "tempVector");
    //            LLVMBuildStore(builderRef, appendedVectors, tempVector);
            }
            return  tempArray;
        }

        return  null;
    }
}
