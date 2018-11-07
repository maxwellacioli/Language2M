package analyzer;

import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class LLVMConfiguration {

    private static LLVMConfiguration llvmConfigurationInstance;
    private static BytePointer error;
    private static LLVMModuleRef globalMod;
    private static LLVMBuilderRef globalBuilder;

    private LLVMConfiguration() {
        error = new BytePointer((Pointer) null);
        globalMod = LLVMModuleCreateWithName("globalMod");
        globalBuilder = LLVMCreateBuilder();
    }

    public static LLVMConfiguration getInstance() {
        if (llvmConfigurationInstance == null ) {
            llvmConfigurationInstance = new LLVMConfiguration();
        }
        return llvmConfigurationInstance;
    }

    public BytePointer getError() {
        return error;
    }

    public LLVMModuleRef getGlobalMod() {
        return globalMod;
    }

    public LLVMBuilderRef getGlobalBuilder() {
        return globalBuilder;
    }

    public static void runLLVM() {
        LLVMVerifyModule(globalMod, LLVMAbortProcessAction, error);
        LLVMDisposeMessage(error); // Handler == LLVMAbortProcessAction -> No
        // need to check errors

        LLVMExecutionEngineRef engine = new LLVMExecutionEngineRef();

        if (LLVMCreateInterpreterForModule(engine, globalMod, error) != 0) {
            System.err.println(error.getString());
            LLVMDisposeMessage(error);
            System.exit(-1);
        }

        LLVMPassManagerRef pass = LLVMCreatePassManager();
        LLVMAddConstantPropagationPass(pass);
        LLVMAddInstructionCombiningPass(pass);
        LLVMAddPromoteMemoryToRegisterPass(pass);
        LLVMAddGVNPass(pass);
        LLVMAddCFGSimplificationPass(pass);
        LLVMRunPassManager(pass, globalMod);
        LLVMDumpModule(globalMod);

        LLVMValueRef mainFunction =  LLVMGetNamedFunction(globalMod, "principal");

        LLVMRunFunction(engine, mainFunction, 0, new PointerPointer<LLVMGenericValueRef>());

        System.err.println();
        System.err.println("; Running (.2M) Source Code With LLVM...");

        LLVMDisposePassManager(pass);
        LLVMDisposeBuilder(globalBuilder);
        LLVMDisposeExecutionEngine(engine);
    }

    public static void initLLVM() {
        LLVMLinkInInterpreter();
        LLVMInitializeNativeAsmPrinter();
        LLVMInitializeNativeAsmParser();
        LLVMInitializeNativeDisassembler();
        LLVMInitializeNativeTarget();

    }
}
