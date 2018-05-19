package semantic;

import syntactic.grammar.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private List<Node> children = new ArrayList<Node>();
    private Symbol symbol;

    public Node(Symbol symbol) {
        this.symbol = symbol;
    }

    public Node(Symbol symbol, Node parent) {
        this.symbol = symbol;
        this.parent = parent;
    }

    public void setParent(Node parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChildren(List<Node> children) {
        for(Node n : children) {
            n.setParent(this);
        }
        this.children.addAll(children);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Node getParent() {
        return parent;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        if(this.children.size() == 0)
            return true;
        else
            return false;
    }

    public void removeParent() {
        this.parent = null;
    }
}
