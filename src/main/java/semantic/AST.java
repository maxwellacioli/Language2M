package semantic;

import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;
import semantic.commands.Node;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class AST {

    private String name;
    private Node root;
    private SymbolTable symbolTable;

    public AST (String name, SymbolTable symbolTable) {
        this.name = name;
        this.symbolTable = symbolTable;
    }

    public AST (String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void allocateSymbols(LLVM.LLVMBuilderRef builder) {
        Map<String, Symbol> st = symbolTable.getLocalSymbolTable();
        Iterator<Map.Entry<String, Symbol>> entries = st.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Symbol> entry = entries.next();
            Symbol symbol = entry.getValue();
            String symbolName = symbol.getName();
            VarType symbolType = symbol.getType();
            LLVM.LLVMValueRef value = null;

            switch (symbolType) {
                case INTEIRO:
//                    value = LLVMBuildAlloca(builder, LLVMInt32Type(), symbolName);
                    break;

            }
            symbol.setLlvmValueRef(value);
        }
    }
}
