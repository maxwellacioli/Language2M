package semantic.commands;

public class Printout extends Node {
    private static String strCode = "";

    public Printout(Node child) {
        addChild(child);
    }

    public static void addStrCode(String sc) {
        strCode += sc;
    }
}
