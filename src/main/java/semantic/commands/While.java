package semantic.commands;

public class While extends Node {

    public While (Node child1, Node child2) {
        addChild(child1);
        addChild(child2);
    }
}
