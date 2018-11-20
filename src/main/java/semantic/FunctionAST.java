package semantic;

import analyzer.LLVMConfiguration;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;
import semantic.commands.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class FunctionAST {

    private String name;
    private Node root;
    private SymbolTable symbolTable;
    private FunctionSymbol functionSymbol;

    public FunctionAST(String name, SymbolTable symbolTable, FunctionSymbol functionSymbol) {
        this.name = name;
        this.symbolTable = symbolTable;
        this.functionSymbol = functionSymbol;
    }

    public FunctionAST(String name) {
        this.name = name;
    }

    public FunctionSymbol getFunctionSymbol() {
        return functionSymbol;
    }

    public void functionCodeGen(LLVMModuleRef moduleRef, LLVMContextRef contextRef, LLVMBuilderRef builderRef) {
        LLVMTypeRef funcType;
        LLVMValueRef func;

        if(functionSymbol != null) {
            ArrayList<LLVMTypeRef> argsTypeArray = new ArrayList<LLVMTypeRef>();
            for (VarType vt:
                 functionSymbol.getParamsType()) {
                argsTypeArray.add(getType(vt));
            }

            LLVMTypeRef[] argsType = new LLVMTypeRef[argsTypeArray.size()];
            argsTypeArray.toArray(argsType);

            funcType = LLVMFunctionType(getType(functionSymbol.getType()), new PointerPointer<LLVMTypeRef>(argsType), argsType.length, 0);
            func = LLVMAddFunction(moduleRef, name, funcType);
            LLVMBasicBlockRef entry = LLVMAppendBasicBlock(func, "entry");
            LLVMPositionBuilderAtEnd(builderRef, entry);

            //Alocação das variaveis na memoria
            if(checkParamsCount(funcType)) {
                throw new RuntimeException("A quantidade de parametros passados não é igual ao" +
                        "numero de parametros que a função aceita!");
            }
            allocateSymbols(builderRef);
            loadFuncParams(builderRef, func);

//            Node.visitorExp(getRoot(), LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable);
            Node.VisitCmd(getRoot(),  LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable, func);

            //TODO FECHAR ESCOPO DA FUNÇÃO /\ #########
        } else {
            funcType = LLVMFunctionType(LLVMInt32Type(), new PointerPointer((Pointer)null), 0, 0);
            func = LLVMAddFunction(moduleRef, name, funcType);
            LLVMBasicBlockRef entry = LLVMAppendBasicBlock(func, "entry");
            LLVMPositionBuilderAtEnd(builderRef, entry);

            //Alocação das variaveis na memoria
            allocateSymbols(builderRef);

            Node.VisitCmd(getRoot(), LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable, func);

            LLVMValueRef ret = LLVMConstInt(LLVMInt32Type(), 1, 1);
            LLVMBuildRet(builderRef, ret);
        }
    }

    private void loadFuncParams(LLVMBuilderRef builderRef, LLVMValueRef function) {
        int index = 0;
        for (Param param: functionSymbol.getParamsList()
             ) {
            Symbol symbol = symbolTable.getLocalSymbolTable().get(param.getName());
            LLVMValueRef paramLLvm = symbol.getLlvmValueRef();
            LLVMBuildStore(builderRef, LLVMGetParam(function, index++), paramLLvm);
        }
    }

    private Boolean checkParamsCount(LLVMTypeRef funcParams) {
        return (functionSymbol.getParamsList().size()!=LLVMCountParamTypes(funcParams));
    }

    private LLVMTypeRef getType(VarType type) {
        switch (type) {
            case INTEIRO:
                return LLVMInt32Type();
            case REAL:
                return LLVMDoubleType();
            case LOGICO:
                return LLVMInt1Type();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void allocateSymbols(LLVMBuilderRef builder) {
        Map<String, Symbol> st = symbolTable.getLocalSymbolTable();
        Iterator<Map.Entry<String, Symbol>> entries = st.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Symbol> entry = entries.next();
            Symbol symbol = entry.getValue();
            String symbolName = symbol.getName();
            VarType symbolType = symbol.getType();
            LLVMValueRef value = null;

            switch (symbolType) {
                case INTEIRO:
                    value = LLVMBuildAlloca(builder, LLVMInt32Type(), symbolName);
                    LLVMBuildStore(builder, LLVMConstInt(LLVMInt32Type(), 0, 1),value);
                    break;
                case REAL:
                    value = LLVMBuildAlloca(builder, LLVMDoubleType(), symbolName);
                    LLVMBuildStore(builder, LLVMConstReal(LLVMDoubleType(), 0),value);
                    break;
                case LOGICO:
                    value = LLVMBuildAlloca(builder, LLVMInt1Type(), symbolName);
                    LLVMBuildStore(builder, LLVMConstInt(LLVMInt1Type(), 0, 1),value);
                    break;

            }
            symbol.setLlvmValueRef(value);
        }
    }
}
