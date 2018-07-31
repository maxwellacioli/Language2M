package semantic.commands;

public class Cmds extends Node {
    public Cmds() {}

    public Cmds(Node child1, Node child2) {
        addChild(child1);
        addChild(child2);
    }
}
