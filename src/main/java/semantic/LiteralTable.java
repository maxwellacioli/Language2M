package semantic;

import org.bytedeco.javacpp.LLVM.*;

import java.util.HashMap;
import java.util.Map;

public class LiteralTable {

    private Map<String, LLVMValueRef> literalTable;
    private static LiteralTable literalTableInstance;

    public LiteralTable() {
        literalTable = new HashMap<String, LLVMValueRef>();
    }

    public static LiteralTable getInstance() {
        if(literalTableInstance == null) {
            literalTableInstance = new LiteralTable();
        }
        return literalTableInstance;
    }

    public Map<String, LLVMValueRef> getLiteralTable() {
        return literalTable;
    }

    public void insertLiteral(String name, LLVMValueRef llvmValueRef) {
        LLVMValueRef value = literalTable.get(name);
        if(value == null) {
            literalTable.put(name, llvmValueRef);
        }
    }

    public LLVMValueRef getLiteral(String name) {
        return literalTable.get(name);
    }
}