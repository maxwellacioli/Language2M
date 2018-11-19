package semantic;

import java.util.ArrayList;
import java.util.List;

public class FunctionSymbol extends Symbol {
    private List<VarType> paramsType;
    private List<Param> paramsList;

    public FunctionSymbol(String name, VarType type) {
        super(name, type);
        paramsType = new ArrayList<VarType>();
        paramsList = new ArrayList<Param>();
    }

    public void insertFuncParam(String name, VarType varType) {
        Param param = new Param(name, varType);
        paramsList.add(param);
    }

    public List<Param> getParamsList() {
        return paramsList;
    }

    public void insertParamType(VarType paramType) {
        paramsType.add(paramType);
    }

    public int getParamsSize() {
        return paramsType.size();
    }

    public List<VarType> getParamsType() {
        return paramsType;
    }
}
