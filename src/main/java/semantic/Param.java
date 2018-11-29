package semantic;

//Classe que representa um parametro de uma função
public class Param {
    private String name;
    private VarType varType;

    public Param(String name, VarType varType) {
        this.name = name;
        this.varType = varType;
    }

    public String getName() {
        return name;
    }

    public VarType getVarType() {
        return varType;
    }
}
