package semantic;

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

    public void dfsTree(Node node) {
        List<Node> children = node.getChildren();
        if(children.size() == 0) {
            return;
        }

        for (Node child : children) {
            dfsTree(child);
        }
    }
}
