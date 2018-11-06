package semantic.commands.expression;

import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import org.bytedeco.javacpp.LLVM.*;
import semantic.VarType;

public class Id extends Exp {

    public Id() {}

    public Id(Token token) {
        super(token, null);
    }

    public Id(Token token, VarType type) {
        super(token, type);
    }

}
