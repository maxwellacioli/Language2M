package semantic.commands;

public class Iterator extends Node {

    public Iterator (Node child1, Node child2, Node child3, Node child4) {
        addChild(child1);
        addChild(child2);
        addChild(child3);
        addChild(child4);
    }
}