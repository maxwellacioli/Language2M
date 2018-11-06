package semantic.commands;

import org.bytedeco.javacpp.LLVM.*;
import syntactic.grammar.GrammarSymbol;
import syntactic.grammar.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private List<Node> children;
    private GrammarSymbol grammarSymbol;

    //TODO
//    public static int label = 0;
//    public int newLabel() {
//        return ++label;
//    }

    public Node() {
        this(null, null);
    }

    public Node(Node parent, GrammarSymbol grammarSymbol) {
        children = new ArrayList<Node>();
        this.parent = parent;
        this.grammarSymbol = grammarSymbol;
    }

    public Node(GrammarSymbol grammarSymbol) {
        this(null, grammarSymbol);
        children = new ArrayList<Node>();
    }

    public void setTokenValue(Terminal term) {
        if (grammarSymbol instanceof  Terminal) {
            ((Terminal) grammarSymbol).setToken(term.getToken());
        }
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(int index, Node child) {
        child.setParent(this);
        this.children.add(index, child);
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

    public void removeNode() {
        this.parent.getChildren().remove(this);
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

    //Geração de IR pela LLVM
    public static LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef,
                                       LLVMBuilderRef builderRef) { return  null; }
}
