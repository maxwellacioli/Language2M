package semantic;

import syntactic.grammar.NonTerminalName;

import java.util.ArrayList;
import java.util.List;

public class Commands {
    private List<NonTerminalName> commandsList;
    private static Commands commands;

    private Commands() {
        commandsList = new ArrayList<NonTerminalName>();
     initCommands();
    }

    public Commands getInstance() {
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
}
