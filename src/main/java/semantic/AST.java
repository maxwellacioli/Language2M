package semantic;

import semantic.commands.Node;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class AST {

    private String name;
    private Node root;

    public AST (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

}
