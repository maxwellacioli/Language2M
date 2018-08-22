package semantic.commands.expression;

import lexical.Token;
import semantic.VarType;

public class Constant extends Exp {

    public Constant(Token token){
        super(token, null);
        checkConstantType(token);
    }

    private void checkConstantType (Token token) {
        switch (token.getCategory()) {
            case CONSTNUMINT:
                setType(VarType.INTEIRO);
                break;
            case CONSTNUMREAL:
                setType(VarType.REAL);
                break;
            case CONSTLOGIC:
                setType(VarType.LOGICO);
                break;
            case CONSTCHAR:
                setType(VarType.CARACTER);
                break;
            case CONSTCCHAR:
                setType(VarType.CADEIA);
                break;
        }
    }
}
