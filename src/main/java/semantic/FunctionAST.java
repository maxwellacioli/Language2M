package semantic;

import analyzer.LLVMConfiguration;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.LLVM.*;
import semantic.commands.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
            allocateSymbols(builderRef);

            Node.visitor(getRoot(), LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable);

            //TODO FECHAR ESCOPO DA FUNÇÃO /\ #########
        } else {
            funcType = LLVMFunctionType(LLVMInt32Type(), new PointerPointer((Pointer)null), 0, 0);
            func = LLVMAddFunction(moduleRef, name, funcType);
            LLVMBasicBlockRef entry = LLVMAppendBasicBlock(func, "entry");
            LLVMPositionBuilderAtEnd(builderRef, entry);

            //Alocação das variaveis na memoria
            allocateSymbols(builderRef);

            Node.visitor(getRoot(), LLVMConfiguration.getInstance().getGlobalMod(), contextRef, builderRef, symbolTable);

            LLVMValueRef ret = LLVMConstInt(LLVMInt32Type(), 1, 1);
            LLVMBuildRet(builderRef, ret);
        }
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

    public void allocateSymbols(LLVM.LLVMBuilderRef builder) {
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
