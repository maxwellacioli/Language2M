package semantic.commands;

import analyzer.LLVMConfiguration;
import org.bytedeco.javacpp.LLVM.*;
import semantic.SymbolTable;
import semantic.commands.expression.Exp;
import syntactic.grammar.GrammarSymbol;
import syntactic.grammar.Terminal;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
    private String name;
    private Node parent;
    private List<Node> children;
    private GrammarSymbol grammarSymbol;
    private LLVMValueRef llvmValueRef;

    public Node(String name) {
        this(null, null, name);
    }

    public Node(Node parent, GrammarSymbol grammarSymbol, String name) {
        children = new ArrayList<Node>();
        this.parent = parent;
        this.grammarSymbol = grammarSymbol;
    }

    public Node(GrammarSymbol grammarSymbol, String name) {
        this(null, grammarSymbol, name);
        children = new ArrayList<Node>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
                                       LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func);

    //Percorrer a ast para resolver os nós comandos
    //TODO verificar se o nó é um comandos
    public static void VisitCmd(Node node, LLVMModuleRef moduleRef, LLVMContextRef contextRef,
                                LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
        if(node instanceof Exp) {
            return;
        }

        if(node.children.size() == 0) {
            return;
        }

        if(node instanceof Printout) {
            LLVMConfiguration.changeStrCodeFlag();
        }

        if(!(node instanceof ListCmds)) {
            node.codeGen(moduleRef, contextRef, builderRef, symbolTable, func);
        }

        for (Node n: node.children) {
            VisitCmd(n,moduleRef, contextRef, builderRef, symbolTable, func);
        }
    }
    //A variavel strCode é necessaria para construir a sequencia de strings que serão impressas
    //Busca em profundidade (pós-ordem)
    public static LLVMValueRef visitorExp(Node node, LLVMModuleRef moduleRef, LLVMContextRef contextRef,
                                          LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {
// TODO Implemntar condição para fazer a chamada quando temos ListCmds
// TODO pois não pode ser executada a geração de código para este tipo de nó

        if(node.children.size() == 0) {
            return node.codeGen(moduleRef, contextRef, builderRef, symbolTable, func);
        }

        for (Node n: node.children) {
            n.setLlvmValueRef(visitorExp(n,moduleRef, contextRef, builderRef, symbolTable, func));
        }

        return node.codeGen(moduleRef, contextRef, builderRef, symbolTable, func);
    }
}
