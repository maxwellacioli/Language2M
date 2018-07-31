package semantic.commands;

public class CmdsRec extends Node {
    public CmdsRec() {}

    public CmdsRec(Node child1, Node child2) {
        addChild(child1);
        addChild(child2);
    }
}
