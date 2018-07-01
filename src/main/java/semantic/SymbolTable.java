package semantic;

import java.util.List;
import java.util.ArrayList;

public class SymbolTable {
    private static SymbolTable symbolTableSingleton;
    private List<Symbol> symbolTable;

    private SymbolTable() {
        symbolTable = new ArrayList<Symbol>();
    }

    public static SymbolTable getInstance() {
        if (symbolTableSingleton == null) {
            symbolTableSingleton = new SymbolTable();
        }
        return symbolTableSingleton;
    }

    public void insertSymbol(Symbol symbolInput) {
        if(alreadyDeclared(symbolInput.getName())) {
            //TODO melhorar a forma que o erro e' mostrado!
            throw new RuntimeException("Varia'vel ja' declarada!");
        }
        symbolTable.add(symbolInput);
    }

    //Necessario para verificar tipo de variaveis em expressoes
    public VarType lookType(String name) {
        for (Symbol symbol : symbolTable) {
            if(symbol.name.equals(name)) {
                return symbol.getType();
            }
        }
        return null;
    }

    private Boolean alreadyDeclared(String name) {
        for (Symbol inputSymbol : symbolTable) {
            if(inputSymbol.name.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
