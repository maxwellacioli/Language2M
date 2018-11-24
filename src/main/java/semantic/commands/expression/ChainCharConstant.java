package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import semantic.SymbolTable;
import semantic.VarType;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class ChainCharConstant extends Exp {

    public ChainCharConstant(Token token) {
        super(token, VarType.TEXT);
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        String strValue = getToken().getLexValue();

        LLVMValueRef str = LLVMBuildGlobalString(builderRef, strValue, getToken().getLexValue());

//        String strValue1 = "Maxwell";
//        LLVMValueRef str1 = LLVMConstString(strValue1, strValue1.length()+1, 1);
//        LLVMValueRef test = LLVMBuildAlloca(builderRef, LLVMArrayType(LLVMInt8Type(), 3), "test");
//        //Realocar espaço na memória
//        test = LLVMBuildAlloca(builderRef, LLVMArrayType(LLVMInt8Type(), 8), "test");
//        LLVMBuildStore(builderRef, str1, test);

        LLVMConfiguration.getInstance().addPrintArg(str);
        LLVMConfiguration.getInstance().addStrCode("%s");

        return null;
    }
}
