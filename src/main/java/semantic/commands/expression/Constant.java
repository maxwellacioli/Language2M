package semantic.commands.expression;

import lexical.Token;
import static org.bytedeco.javacpp.LLVM.*;

import org.bytedeco.javacpp.LLVM;
import semantic.VarType;

public class Constant extends Exp {

    private static VarType type;
    private static Token token;

    public Constant(Token token){
        super(token, null);
        checkConstantType(token);
        type = this.getType();
        this.token = token;
    }

    private void checkConstantType (Token token) {
        switch (token.getCategory()) {
            case CONSTNUMINT:
                setType(VarType.INTEIRO);
                break;
            case CONSTNUMREAL:
                setType(VarType.REAL);
                break;
            case CONSTLOGIC:
                setType(VarType.LOGICO);
                break;
            case CONSTCHAR:
                setType(VarType.CARACTER);
                break;
            case CONSTCCHAR:
                setType(VarType.CADEIA);
                break;
        }
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef) {
//        switch (type) {
//            case REAL:
//                return LLVMConstReal(LLVMDoubleType(), Double.parseDouble(token.getLexValue()));
//            case INTEIRO:
//                return LLVMConstInt(LLVMInt32Type(), Integer.parseInt(token.getLexValue()), 1);
//            case CADEIA:
//                System.out.println(token.getLexValue());
//                return LLVMConstString(token.getLexValue(), token.getLexValue().length(), 1);
//
//        }
        System.out.println(this.getToken().getLexValue());
        return  null;
    }
}
