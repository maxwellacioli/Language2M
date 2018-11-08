package semantic.commands;

import javax.smartcardio.ATR;

public class Attribution extends Node {

    public Attribution () {}

    public Attribution(Node child1, Node child2) {
        addChild(child1);
        addChild(child2);
    }

}
