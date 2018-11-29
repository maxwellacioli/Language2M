package semantic;

import static org.bytedeco.javacpp.LLVM.*;

//Classe que representa uma variável
public class Symbol {
    private String name;
    private VarType type;
    private LLVMValueRef llvmValueRef;


    public Symbol(String name, VarType type) {
        this.name = name;
        this.type = type;
    }

    public LLVMValueRef getLlvmValueRef() {
        return llvmValueRef;
    }

    public void setLlvmValueRef(LLVMValueRef llvmValueRef) {
        this.llvmValueRef = llvmValueRef;
    }

    public String getName() {
        return name;
    }

    public VarType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(VarType type) {
        this.type = type;
    }
}
