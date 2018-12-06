package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

import semantic.Symbol;
import semantic.SymbolTable;
import semantic.VarType;
import semantic.commands.Printout;

public class Id extends Exp {

    public Id() {}

    public Id(Token token) {
        super(token, null);
    }
 
    public Id(Token token, VarType type) {
        super(token, type);
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        Symbol symbol = symbolTable.getLocalSymbolTable().get(getName());

        //verifica se a variavél existe na tabela de simbolos local
        if(symbol == null) {
            System.err.println("Variável " + "<'" + getName() + "'>" + " não declarada.");
            System.exit(1);
        }

        LLVMValueRef valueRef = symbol.getLlvmValueRef();
        LLVMValueRef value = null;

        //verifica se o tipo é texto, caso seja, não precisa carregar o array da memória, pois já temos
        //o array e não um ponteiro para o mesmo
        if(!getType().equals(VarType.TEXT)) {
           value = LLVMBuildLoad(builderRef, valueRef, getName());
        }

        //verifica se a flag de impressao está ativa, caso esteja, é adicionado na string base do comando printf, o
        //respectivo código da variável que será impressa na tela
        if(LLVMConfiguration.getStrCodeFlag() &&
                (this.getParent() instanceof Printout || this.getParent() instanceof OpBinaryConc)) {
            if(getType().equals(VarType.INTEIRO)) {
                LLVMConfiguration.getInstance().addPrintArg(value);
                LLVMConfiguration.getInstance().addStrCode("%d");
            } else if(getType().equals(VarType.REAL)) {
                LLVMConfiguration.getInstance().addPrintArg(value);
                LLVMConfiguration.getInstance().addStrCode("%.2lf");
            }
            //caso o tipo seja texto, é alocado espaço na memória para o respcetivo array, e passado seu ponteiro
            //como parametro nos argumentos do printf
            else if(getType().equals(VarType.TEXT)) {
                LLVMTypeRef stringType = LLVMTypeOf(valueRef);
                int stringLength = LLVMGetArrayLength(stringType);
                int typeKind = LLVMGetTypeKind(stringType);

                LLVMValueRef strValue;

                if(typeKind == LLVMArrayTypeKind) {
                    strValue = LLVMBuildAlloca(builderRef, LLVMArrayType(LLVMInt8Type(), stringLength), "strValue");
                } else {
                    strValue = LLVMBuildAlloca(builderRef, LLVMVectorType(LLVMInt8Type(), stringLength), "strValue");
                }

                LLVMBuildStore(builderRef, valueRef, strValue);

                LLVMConfiguration.getInstance().addPrintArg(strValue);
                LLVMConfiguration.getInstance().addStrCode("%s");
            }
        }

        //caso o tipo seja texto é retornado o próprimo array, caso não, é retornado o ponteiro para o id, que é feito pelo
        //próximo comando
        if(getType().equals(VarType.TEXT)) {
            return valueRef;
        }

        return  value;

    }
}
