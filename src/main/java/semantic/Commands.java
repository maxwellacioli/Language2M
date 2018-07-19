package semantic;

import lexical.TokenCategory;
import syntactic.grammar.NonTerminal;
import syntactic.grammar.NonTerminalName;
import syntactic.grammar.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Commands {
    private List<NonTerminalName> commandsList;
    private static Commands commands;

    private Commands() {
        commandsList = new ArrayList<NonTerminalName>();
     initCommands();
    }

    public static Commands getInstance() {
        if(commands == null) {
            commands = new Commands();
        }
        return commands;
    }

    public Boolean isCommand(NonTerminalName nonTerminalName) {
        if(commandsList.contains(nonTerminalName)) {
            return true;
        } else {
            return false;
        }
    }

    private void initCommands() {
        commandsList.add(NonTerminalName.COMMANDS);
        commandsList.add(NonTerminalName.ATTRIBUTION);
        commandsList.add(NonTerminalName.CMD);
        commandsList.add(NonTerminalName.CMDFAT);
        commandsList.add(NonTerminalName.NAMEFAT1);
        commandsList.add(NonTerminalName.VALUE);
        commandsList.add(NonTerminalName.ARRAY);
        commandsList.add(NonTerminalName.EXPRESSION);
        commandsList.add(NonTerminalName.ARRAYFAT);
        commandsList.add(NonTerminalName.ELEMENTS);
        commandsList.add(NonTerminalName.CONSTANT);
        commandsList.add(NonTerminalName.ELEMENTSFAT);
        commandsList.add(NonTerminalName.FUNCCALL);
        commandsList.add(NonTerminalName.LISTPARAMSCALL);
        commandsList.add(NonTerminalName.LISTPARAMSCALLFAT);
        commandsList.add(NonTerminalName.PARAMITEM);
        commandsList.add(NonTerminalName.NAME); //SO ADD SE O PAI FOR PARAMITEM OU READIN
        commandsList.add(NonTerminalName.MESSAGE);
        commandsList.add(NonTerminalName.MESSAGEFAT);
        commandsList.add(NonTerminalName.TYPE); //SO ADD SE O PAI FOR READIN OU CASTING
        commandsList.add(NonTerminalName.ELSE);


        commandsList.add(NonTerminalName.PRINTOUT);
        commandsList.add(NonTerminalName.READIN);
        commandsList.add(NonTerminalName.IF);
        commandsList.add(NonTerminalName.IFELSE);
        commandsList.add(NonTerminalName.ELSEIF);
        commandsList.add(NonTerminalName.WHILE);
        commandsList.add(NonTerminalName.DOWHILE);
        commandsList.add(NonTerminalName.DOWHILE);
        commandsList.add(NonTerminalName.ITERATOR);
        commandsList.add(NonTerminalName.RETURN);
        commandsList.add(NonTerminalName.CASTING);
        commandsList.add(NonTerminalName.EXPRESSION);
    }

    public static Node semanticAction(NonTerminalName nonTerminalName) {
        if(nonTerminalName.equals(NonTerminalName.RETURN)) {
            Node returne = new Node(new Terminal(TokenCategory.PRRETURN));
            Node expression = new Node(new NonTerminal(NonTerminalName.EXPRESSION));
            returne.addChild(expression);
            return returne;
        }

        return null;
    }
}
