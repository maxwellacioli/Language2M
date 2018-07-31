package semantic.commands;

public class IfElse extends Node {

    public IfElse(Node child1, Node child2, Node child3) {
        addChild(child1);
        addChild(child2);
        addChild(child3);
    }
}
