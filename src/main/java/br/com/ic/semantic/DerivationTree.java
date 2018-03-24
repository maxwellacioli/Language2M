package br.com.ic.semantic;

import br.com.ic.syntactic.grammar.Symbol;

import javax.swing.*;
import java.awt.*;

public class DerivationTree extends JTree {

    private static DerivationTree derivationTreeSingleton;

    public static DerivationTree getInstance() {
        if(derivationTreeSingleton == null) {
            derivationTreeSingleton = new DerivationTree();
        }
        return derivationTreeSingleton;
    }


}
