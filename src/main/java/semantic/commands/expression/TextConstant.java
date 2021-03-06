package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import semantic.SymbolTable;
import semantic.VarType;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class TextConstant extends Exp {

    public TextConstant(Token token) {
        super(token, VarType.TEXT);
    }
 
    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        String strValue = getToken().getLexValue();

        int length = getToken().getLexValue().length() + 1;
        LLVMValueRef str1 = LLVMConstString(getToken().getLexValue(), length, 1);

        if(LLVMConfiguration.getStrCodeFlag()){
            LLVMValueRef str = LLVMBuildGlobalString(builderRef, strValue, getToken().getLexValue());
            LLVMConfiguration.getInstance().addPrintArg(str);
            LLVMConfiguration.getInstance().addStrCode("%s");
        }

        return str1;
    }
}
