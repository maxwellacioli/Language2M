package semantic;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;

import static org.bytedeco.javacpp.LLVM.*;
import static org.bytedeco.javacpp.LLVM.LLVMInitializeNativeTarget;

public class SemanticAnalyzer {

    public SemanticAnalyzer() {

    }

    //TODO implementar
    //Gera um nome de temporario
    public String tempGenerator() {
        return "";
    }

    //TODO implementar
    //Gera o nome de um rotulo
    public void labelGenerator() {

    }

    //TODO implementar
    //Gera um codigo de tres enderecos quando nao ha o segundo operando
    public void codGenerator(String op, String optor1, String optor2, String dest) {

    }

    //TODO implementar
    //Gera um codigo de tres enderecos quando nao ha o segundo operando
    public void codGenerator(String op, String optor1, String dest) {

    }

    //TODO implementar
    //
    public void sendLabel(String label) {

    }

    //TODO implementar
    public void GTgenerator(String label) {

    }

    //TODO implementar
    public VarType checkType(String varName) {

        return null;
    }
}
