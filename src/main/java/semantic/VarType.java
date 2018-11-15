package semantic;

public enum VarType {

    INTEIRO("inteiro"),
    REAL("real"),
    CARACTER("caracter"),
    TEXT("texto"),
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
        } else if(type.equals(TEXT.getName())) {
            return TEXT;
        } else if(type.equals(LOGICO.getName())) {
            return LOGICO;
        }

        return null;
    }

    public static boolean isNumeric(VarType type) {
        return (type.equals(INTEIRO) || type.equals(REAL));
    }

    public static boolean hasEqualType(VarType type1, VarType type2) {
        return (type1.equals(type2));
    }
}
