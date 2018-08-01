package semantic.commands;

public class ListCmds extends Node {
    public ListCmds() {}

    public ListCmds(Node child1, Node child2) {
        addChild(child1);
        addChild(child2);
    }
}
