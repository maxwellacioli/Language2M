package semantic.commands;

import analyzer.LLVMConfiguration;
import org.bytedeco.javacpp.*;

import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.javacpp.LLVM.*;

public class Printout extends Node {

    public Printout(Node child) {
        addChild(child);
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef) {
        List<LLVMValueRef> printArgs = LLVMConfiguration.getInstance().getPrintArgs();
        LLVMConfiguration.insertStringCode();

        //Converter ArrayList para Array
        LLVMValueRef[] args = new LLVMValueRef[printArgs.size()];
        args = printArgs.toArray(args);

        LLVMValueRef printFunction = LLVMGetNamedFunction(moduleRef, "printf");
        LLVMBuildCall(builderRef, printFunction, new PointerPointer(args), printArgs.size(), "printf");

        LLVMConfiguration.resetPrintConfig();
        return null;
    }
}
