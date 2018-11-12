package semantic;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;

import static org.bytedeco.javacpp.LLVM.*;
import static org.bytedeco.javacpp.LLVM.LLVMInitializeNativeTarget;

public class SemanticAnalyzer {

    private int tempCount;
    private String tempName;

    private int labelCount;
    private String labelName;

    private static SemanticAnalyzer semanticAnalyzer;

    private SemanticAnalyzer() {
        tempCount = 1;
        labelCount = 1;
        tempName = "temp";
        labelName = "label";
    }

    public static SemanticAnalyzer getInstance() {
        if(semanticAnalyzer == null) {
            semanticAnalyzer = new SemanticAnalyzer();
        }
        return semanticAnalyzer;
    }

    public String tempGenerator() {

        return tempName + String.valueOf(tempCount++);
    }

    public String labelGenerator() {
        return labelName + String.valueOf(labelCount++);
    }


//    public void sendLabel(String label) {
//
//    }
//
//    public void GTgenerator(String label) {
//
//    }

    //TODO implementar
    public VarType checkType(String varName) {

        return null;
    }
}
