package semantic.commands;

public class Do extends Node {

    public Do (Node child1, Node child2) {
        addChild(child1);
        addChild(child2);
    }
}
