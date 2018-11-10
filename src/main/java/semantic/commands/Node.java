package semantic.commands;

import analyzer.LLVMConfiguration;
import org.bytedeco.javacpp.LLVM.*;
import syntactic.grammar.GrammarSymbol;
import syntactic.grammar.Terminal;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
    private Node parent;
    private List<Node> children;
    private GrammarSymbol grammarSymbol;
    private LLVMValueRef llvmValueRef;

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

    public void setLlvmValueRef(LLVMValueRef llvmValueRef) {
        this.llvmValueRef = llvmValueRef;
    }

    public LLVMValueRef getLlvmValueRef() {
        return llvmValueRef;
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
    public abstract LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef,
                                       LLVMBuilderRef builderRef);

    //A variavel strCode é necessaria para construir a sequencia de strings que serão impressas
    public static LLVMValueRef visitor(Node node, LLVMModuleRef moduleRef, LLVMContextRef contextRef,
                                       LLVMBuilderRef builderRef) {
// TODO Implemntar condição para fazer a chamada quando temos ListCmds
// TODO pois não pode ser executada a geração de código para este tipo de nó

        if(node.children.size() == 0) {
            return node.codeGen(moduleRef, contextRef, builderRef);
        }

        if(node instanceof Printout) {
            LLVMConfiguration.changeStrCodeFlag();
        }

        for (Node n: node.children) {
            if(n instanceof  ListCmds) {
                visitor(n,moduleRef, contextRef, builderRef);
            } else {
                n.setLlvmValueRef(visitor(n,moduleRef, contextRef, builderRef));
            }
        }

        if(node instanceof  ListCmds) {
            return null;
        }
        return node.codeGen(moduleRef, contextRef, builderRef);
    }
}
