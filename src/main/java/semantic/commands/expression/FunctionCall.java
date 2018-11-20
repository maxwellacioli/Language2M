package semantic.commands.expression;

import analyzer.LLVMConfiguration;
import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.*;
import org.bytedeco.javacpp.*;
import semantic.commands.Node;

import static org.bytedeco.javacpp.LLVM.*;

import java.util.ArrayList;

public class FunctionCall extends Exp {

    //TODO Implementar verificação de tipo da função na sua chamada
    public FunctionCall(Token tk) {
        super(tk, ProgramAST.getInstance().getFunctionAst(tk.getLexValue()).getFunctionSymbol().getType());
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {

        ArrayList<LLVMValueRef> funcArgsArray = new ArrayList<LLVMValueRef>();
        for (Node node:
                getChildren()) {
            funcArgsArray.add(node.getLlvmValueRef());
        }

        LLVMValueRef[] funcArgs = new LLVMValueRef[funcArgsArray.size()];
        funcArgsArray.toArray(funcArgs);

        //pegar nome da função
        LLVMValueRef function = LLVMGetNamedFunction(LLVMConfiguration.getInstance().getGlobalMod(), getToken().getLexValue());
        LLVMValueRef functionResult = LLVMBuildCall(builderRef, function, new PointerPointer<LLVMValueRef>(funcArgs), funcArgs.length,
                SemanticAnalyzer.getInstance().tempGenerator());
        return  functionResult;
    }
}
