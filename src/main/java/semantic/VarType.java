package semantic;

public enum VarType {

    INTEIRO("inteiro"),
    REAL("real"),
    CARACTER("caracter"),
    CADEIA("cadeia"),
    LONGO("longo"),
    LOGICO("logico");

    private String name;

    private VarType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static VarType getVarType(String type) {
        if(type.equals(INTEIRO.getName())) {
            return INTEIRO;
        } else if(type.equals(REAL.getName())) {
            return REAL;
        } else if(type.equals(CARACTER.getName())) {
            return CARACTER;
        } else if(type.equals(CADEIA.getName())) {
            return CADEIA;
        } else if(type.equals(LONGO.getName())) {
            return LONGO;
        } else if(type.equals(LOGICO.getName())) {
            return LOGICO;
        }

        return null;
    }
}
