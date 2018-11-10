package semantic.commands.expression;

import lexical.Token;
import semantic.commands.Node;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class OpBinaryArithAdit extends OpBinary {
    public OpBinaryArithAdit(Token tk, Node exp1, Node exp2) {
        super(tk, exp1, exp2);


    }

    //TODO Verificação de compatibilidade de tipos é na análise semântica
//    @Override
//    public LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
//        LLVMValueRef left;
//        LLVMValueRef right;
//
//        switch (getToken().getCategory()) {
//            case :
//                break;
//            case REAL:
//                break;
//            case CADEIA:
//                break;
//            case CARACTER:
//                break;
//            case LOGICO:
//                break;
//        }
//        return null;
//    }
}


