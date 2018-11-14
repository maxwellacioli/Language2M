package semantic;

import java.util.List;
import java.util.ArrayList;

public class SymbolTable {
    private String tableName;
    private List<Symbol> symbolTable;

    public SymbolTable(String name) {
        this.tableName = name;
        symbolTable = new ArrayList<Symbol>();
    }

    public void insertSymbol(Symbol symbolInput) {
        if(alreadyDeclared(symbolInput.getName())) {
            //TODO melhorar a forma que o erro e' mostrado!
            throw new RuntimeException("Varia'vel ja' declarada!");
        }
        symbolTable.add(symbolInput);
    }

    public VarType getSymbolType(String symbolName) {
        VarType type = null;
        for (Symbol symbol :
             symbolTable) {
            if(symbol.getName().equals(symbolName)) {
                type = symbol.getType();
            }
        }
        return type;
    }

    //Necessario para verificar tipo de variaveis em expressoes
    public VarType lookType(String name) {
        for (Symbol symbol : symbolTable) {
            if(symbol.getName().equals(name)) {
                return symbol.getType();
            }
        }
        return null;
    }

    private Boolean alreadyDeclared(String name) {
        for (Symbol inputSymbol : symbolTable) {
            if(inputSymbol.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public String getTableName() {
        return tableName;
    }
}
