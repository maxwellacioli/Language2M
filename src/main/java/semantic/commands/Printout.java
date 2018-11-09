package semantic.commands;

import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class Printout extends Node {
    private static String strCode = "";
    private static boolean strCodeFlag = false;

    public Printout(Node child) {
        addChild(child);
    }

    public static void addStrCode(String sc) {
        strCode += sc;
    }

    public static void resetStrCode() {
        strCode = "";
    }

    public boolean isStrEmpty() {
        return (strCode.length() == 0);
    }

    public void changeStrCodeFlag() {
        strCodeFlag = !strCodeFlag;
    }

    public boolean getStrCodeFlag() {
        return strCodeFlag;
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef) {
        LLVMTypeRef[] printfParams = { LLVMPointerType(LLVMInt8Type(), 0) };
        LLVMTypeRef llvmPrintfType = LLVMFunctionType(LLVMInt32Type(), new PointerPointer(printfParams), 0, 1);
        LLVMAddFunction(moduleRef, "printf", llvmPrintfType);

        System.out.println("imprima");
        return null;
    }
}
