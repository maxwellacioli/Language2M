package br.com.ic.semantic;

import br.com.ic.syntactic.grammar.NonTerminal;
import br.com.ic.syntactic.grammar.Symbol;
import br.com.ic.syntactic.grammar.Terminal;

import javax.swing.tree.DefaultMutableTreeNode;

public class NodeTree extends DefaultMutableTreeNode {

    private Symbol symbol;
// FIXME necessário? duplicidade com name de symbol....
// private String name;
    private String lexval;
    //FIXME definir estrutura do value;
    private String value;

    public NodeTree (Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    //    public String getName() {
//        return this.name;
//    }

    public String getLexval() {
        return this.lexval;
    }
}
