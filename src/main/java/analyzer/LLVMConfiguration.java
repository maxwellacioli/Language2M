package analyzer;

import org.bytedeco.javacpp.*;
import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.javacpp.LLVM.*;

public class LLVMConfiguration {

    private static LLVMConfiguration llvmConfigurationInstance;
    private static BytePointer error;
    private static LLVMModuleRef globalMod;
    private static LLVMBuilderRef globalBuilder;

    //Configurações do printf
    private static String printStringCode = "";
    private static boolean strCodeFlag = false;
    private static List<LLVMValueRef> printArgs;

    private LLVMConfiguration() {
        error = new BytePointer((Pointer) null);
        globalMod = LLVMModuleCreateWithName("globalMod");
        globalBuilder = LLVMCreateBuilder();
        printArgs = new ArrayList<LLVMValueRef>();
    }

    public static LLVMConfiguration getInstance() {
        if (llvmConfigurationInstance == null ) {
            llvmConfigurationInstance = new LLVMConfiguration();
        }
        return llvmConfigurationInstance;
    }

    public static void addPrintArg(LLVMValueRef arg) {
        printArgs.add(arg);
    }

    //Adiciona o código da string que será impressa
    public static void insertStringCode(LLVMBuilderRef builder) {
        LLVMValueRef strCode = LLVMBuildGlobalString(builder, printStringCode, "strigPrintCode");
        printArgs.add(0, strCode);
    }

    public static void resetPrintConfig() {
        resetStrCode();
        cleanPrintArgs();
        changeStrCodeFlag();
    }

    private static void cleanPrintArgs() {
        printArgs.clear();
    }

    public static void addStrCode(String sc) {
        printStringCode += sc;
    }

    private static void resetStrCode() {
        printStringCode = "";
    }

    public static List<LLVMValueRef> getPrintArgs() {
        return printArgs;
    }

    public static void changeStrCodeFlag() {
        strCodeFlag = !strCodeFlag;
    }

    public static boolean getStrCodeFlag() {
        return strCodeFlag;
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
        System.err.println();

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

        //Adiciona a função printf no módulo principal
        insertPrintfFunction();
        //Adiciona a função scanf no módulo principal
        insertScanfFunction();
//        insertFflushFunction();
    }

    private static void insertPrintfFunction() {
        LLVMTypeRef[] printfParams = { LLVMPointerType(LLVMInt8Type(), 0) };
        LLVMTypeRef llvmPrintfType = LLVMFunctionType(LLVMInt32Type(), new PointerPointer(printfParams), 0, 1);
        LLVMAddFunction(globalMod, "printf", llvmPrintfType);
    }

    private static void insertScanfFunction() {
        LLVMTypeRef[] scanfParams = { LLVMPointerType(LLVMInt8Type(), 0) };
        LLVMTypeRef llvmScanffType = LLVMFunctionType(LLVMInt32Type(), new PointerPointer(scanfParams), 0, 1);
        LLVMAddFunction(globalMod, "scanf", llvmScanffType);
    }

//    private static void insertFflushFunction() {
//        LLVMTypeRef[] fflushParam = { LLVMPointerType(LLVMStructType(LLVMInt8Type(), 0, 0), 0) };
//        LLVMTypeRef llvmFflushfType = LLVMFunctionType(LLVMInt32Type(), new PointerPointer(fflushParam), 1, 0);
//        LLVMAddFunction(globalMod, "fflush", llvmFflushfType);
//    }
}
