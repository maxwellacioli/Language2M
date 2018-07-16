package semantic;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class AST {

    private Node root;
    private static AST derivationTreeSingleton;

    private AST() {

    }

    public static AST getInstance() {
        if (derivationTreeSingleton == null) {
            derivationTreeSingleton = new AST();
        }

        return derivationTreeSingleton;
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
