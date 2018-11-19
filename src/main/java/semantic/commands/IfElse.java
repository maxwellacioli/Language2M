package semantic.commands;

import semantic.SymbolTable;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;

public class IfElse extends Node {

    public IfElse(Node child1, Node child2, Node child3) {
        super(null);
        addChild(child1);
        addChild(child2);
        addChild(child3);
    }

    @Override
    public LLVMValueRef codeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef, SymbolTable symbolTable) {
        //TODO verificar se é um inteiro de 1 bit
        //LLVMValueRef cond = Node.visitorExp(getChildren().get(0), moduleRef, contextRef, builderRef, symbolTable);


        return null;
    }
}
