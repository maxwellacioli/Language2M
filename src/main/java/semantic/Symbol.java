package semantic;

public class Symbol {
    public String name;
    public VarType type;

    public Symbol(String name, VarType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public VarType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(VarType type) {
        this.type = type;
    }
}
