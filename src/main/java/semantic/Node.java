package semantic;

import syntactic.grammar.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private List<Node> children;
    private Symbol symbol;

    public Node(Node parent, Symbol symbol) {
        children = new ArrayList<Node>();
        this.parent = parent;
        this.symbol = symbol;
    }

    public Node(Symbol symbol) {
        new Node(null, symbol);
        children = new ArrayList<Node>();
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

    public List<Node> getChildren() {
        return children;
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
