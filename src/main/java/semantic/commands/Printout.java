package semantic.commands; 

import analyzer.LLVMConfiguration;
import org.bytedeco.javacpp.*;
import semantic.SymbolTable;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledDocument;

import static org.bytedeco.javacpp.LLVM.*;

public class Printout extends Node {

    public Printout(Node child) {
        super(null);
        addChild(child);
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        Node.visitorExp(getChildren().get(0), LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable, func);

        List<LLVMValueRef> printArgs = LLVMConfiguration.getInstance().getPrintArgs();
        LLVMConfiguration.insertStringCode(builderRef);

        //Converter ArrayList para Array
        LLVMValueRef[] args = new LLVMValueRef[printArgs.size()];
        args = printArgs.toArray(args);

        LLVMValueRef printFunction = LLVMGetNamedFunction(moduleRef, "printf");
        LLVMBuildCall(builderRef, printFunction, new PointerPointer(args), printArgs.size(), "printf");
        
//        LLVMValueRef fflushFunction = LLVMGetNamedFunction(moduleRef, "fflush");
//        LLVMValueRef stdout = LLVMBuildGlobalString(builderRef, "stdout", "stdout");
//        LLVMValueRef[] fflushArgs = { stdout };
//        
//        LLVMBuildCall(builderRef, fflushFunction, new PointerPointer(fflushArgs), fflushArgs.length, "fflush");
        
//        try {
//			PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), true);
//			out.flush();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}

        LLVMConfiguration.resetPrintConfig();
        return null;
    }
}
