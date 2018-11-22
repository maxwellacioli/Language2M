package semantic;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private String tableName;
    private Map<String, Symbol> localSymbolTable;

    public SymbolTable(String name) {
        this.tableName = name;
        localSymbolTable = new HashMap<String, Symbol>();
    }

    public void insertSymbol(Symbol symbolInput) {
        if(alreadyDeclared(symbolInput.getName())) {
            throw new RuntimeException("Varia'vel ja' declarada!");
        }
        localSymbolTable.put(symbolInput.getName(), symbolInput);
    }

    public VarType lookType(String name) {
        Symbol symbol = localSymbolTable.get(name);
        if(symbol == null) {
            throw new RuntimeException("Varia'vel nao declarada!");
        }
        return symbol.getType();
    }

    private Boolean alreadyDeclared(String name) {
        return (localSymbolTable.get(name) != null);
    }

    public Map<String, Symbol> getLocalSymbolTable() {
        return localSymbolTable;
    }

    public String getTableName() {
        return tableName;
    }

    public void removeSymbol(String name) {
        localSymbolTable.remove(name);
    }
}
