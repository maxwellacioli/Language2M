package semantic;

import java.util.ArrayList;
import java.util.List;

public class FunctionSymbol extends Symbol {
    private List<VarType> paramsType;

    public FunctionSymbol(String name, VarType type) {
        super(name, type);
        paramsType = new ArrayList<VarType>();
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
