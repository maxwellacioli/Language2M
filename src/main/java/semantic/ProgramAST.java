package semantic;

import java.util.ArrayList;
import java.util.List;

public class ProgramAST {

    private static ProgramAST programAST;
    private List<FunctionAST> astList;

    private ProgramAST() {
        astList = new ArrayList<FunctionAST>();
    }

    public static ProgramAST getInstance() {
        if(programAST == null) {
            programAST = new ProgramAST();
        }
        return programAST;
    }

    public void insertAst(FunctionAST functionAST) {
        boolean exists = false;
        //Verifica se a functionAst foi criada antes de adicionar a lista de asts
        for (FunctionAST fast:
             astList) {
            if(fast.getName().equals(functionAST.getName())) {
                exists = true;
                break;
            }
        }

        if(exists) {
           System.err.println("Função com nome duplicado!");
           System.exit(1);
        } else {
            astList.add(functionAST);
        }
    }

    public FunctionAST getFunctionAst(String name) {
        for (FunctionAST fast:
             astList) {
            if(fast.getName().equals(name)) {
                return fast;
            }
        }
        return null;
    }

    public List<FunctionAST> getAstList() {
        return astList;
    }
}
