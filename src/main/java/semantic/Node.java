package semantic;

import syntactic.grammar.GrammarSymbol;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private List<Node> children;
    private GrammarSymbol grammarSymbol;

    public Node(Node parent, GrammarSymbol grammarSymbol) {
        children = new ArrayList<Node>();
        this.parent = parent;
        this.grammarSymbol = grammarSymbol;
    }

    public Node(GrammarSymbol grammarSymbol) {
        new Node(null, grammarSymbol);
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

    public GrammarSymbol getGrammarSymbol() {
        return grammarSymbol;
    }

    public Node getParent() {
        return parent;
    }

    public void setGrammarSymbol(GrammarSymbol grammarSymbol) {
        this.grammarSymbol = grammarSymbol;
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
