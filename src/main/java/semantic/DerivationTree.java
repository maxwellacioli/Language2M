package semantic;

import syntactic.grammar.Derivation;
import syntactic.grammar.Symbol;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class DerivationTree {

    private Node root;
    private static DerivationTree derivationTreeSingleton;

    private DerivationTree() {

    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public static DerivationTree getInstance() {
        if (derivationTreeSingleton == null) {
            derivationTreeSingleton = new DerivationTree();
        }

        return derivationTreeSingleton;
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
