package semantic.commands;

import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class Printout extends Node {
    private static String strCode = "";

    public Printout(Node child) {
        addChild(child);
    }

    public static void addStrCode(String sc) {
        strCode += sc;
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef) {
        System.out.println("imprima");
        return null;
    }
}
