package lexical;

public enum TokenCategory {

	MAJOR(1, "principal"),
	ID(2, null),
	TEMPTY(3, "vazio"),
	TINT(4, "inteiro"),
	TLONG(5, "longo"),
	TLOGIC(6, "logico"),
	TCHAR(7, "caracter"),
	TCCHAR(8, "cadeia"),
	TDEC(9, "real"),
	ESCBEGIN(10, "{"),
	ESCEND(11, "}"),
	PARAMBEGIN(12, "("),
	PARAMEND(13, ")"),
	ARRAYBEGIN(14, "["),
	ARRAYEND(15, "]"),
	COMMENT(16, "/$"),
	SEP1(18, ","),
	SEP2(19, ";"),
	CONSTNUMINT(20, null),
	CONSTNUMDEC(21, null),
	CONSTLOGIC(22, null),
	CONSTCHAR(23, null),
	CONSTCCHAR(24, null),
	PRIF(25, "se"),
	PRELSE(26, "entao"),
	PRELSEIF(27, "senao"),
	PRITERATOR(28, "iterador"),
	PRDO(29, "faca"),
	PRWHILE(30, "enquanto"),
	PRPRINTOUT(31, "imprima"),
	PRREADIN(32, "leia"),
	PRRETURN(33, "retorne"),
	OPLOGICAND(34, "e"),
	OPLOGICOR(35, "ou"),
	OPNEGLOGIC(36, "no"),
	OPARITADIT(37, null),
	OPARITMULT(38, null),
	OPARITEXP(39, null),
	OPNEGUN(40, null),
	OPREL1(41, null),
	OPREL2(42, null),
	OPATRIB(43, "="),
	OPCONC(44, "++"),
	UNKNOWN(45, null);
	
	private int value;
	private String name;
	
	private TokenCategory(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public int getCategoryValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
