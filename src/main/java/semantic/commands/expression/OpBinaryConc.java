package semantic.commands.expression;

import lexical.Token;
import semantic.commands.Node;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.LLVM.LLVMTypeRef;
import org.bytedeco.javacpp.LLVM.LLVMValueRef;

//Headers required by LLVM
import static org.bytedeco.javacpp.LLVM.*;

public class OpBinaryConc extends OpBinary {

    public OpBinaryConc(Token tk, Node exp1, Node exp2) {
        super(tk, null, exp1, exp2);
    }

    @Override
    public LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef) {
        System.out.println(getToken().getLexValue());
        return null;
    }
}
