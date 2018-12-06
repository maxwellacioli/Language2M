package semantic.commands.expression; 

import analyzer.LLVMConfiguration; 
import lexical.Token;
import org.bytedeco.javacpp.LLVM;
import semantic.*;
import org.bytedeco.javacpp.*;
import semantic.commands.Node;
import semantic.commands.Printout;

import static org.bytedeco.javacpp.LLVM.*;

import java.util.ArrayList;

public class FunctionCall extends Exp {

    public FunctionCall(Token tk) {
        super(tk, ProgramAST.getInstance().getFunctionAst(tk.getLexValue()).getFunctionSymbol().getType());
    }

    @Override
    public LLVM.LLVMValueRef codeGen(LLVM.LLVMModuleRef moduleRef, LLVM.LLVMContextRef contextRef, LLVM.LLVMBuilderRef builderRef, SymbolTable symbolTable, LLVMValueRef func) {

        //array list com os argumentos da função
        ArrayList<LLVMValueRef> funcArgsArray = new ArrayList<LLVMValueRef>();
        for (Node node:
                getChildren()) {
            funcArgsArray.add(node.getLlvmValueRef());
        }

        //cria um array com os argumentos da função
        LLVMValueRef[] funcArgs = new LLVMValueRef[funcArgsArray.size()];
        //converte o arraylist em array
        funcArgsArray.toArray(funcArgs);

        //nome da função
        LLVMValueRef function = LLVMGetNamedFunction(LLVMConfiguration.getInstance().getGlobalMod(), getToken().getLexValue());
        //realiza a chamada da função
        LLVMValueRef functionResult = LLVMBuildCall(builderRef, function, new PointerPointer<LLVMValueRef>(funcArgs), funcArgs.length,
                SemanticAnalyzer.getInstance().tempGenerator());

        if(LLVMConfiguration.getStrCodeFlag() &&
                (this.getParent() instanceof Printout || this.getParent() instanceof OpBinaryConc)) {
            LLVMConfiguration.getInstance().addPrintArg(functionResult);
            LLVMConfiguration.getInstance().addStrCode("%d");
        }

        return  functionResult;
    }
}
