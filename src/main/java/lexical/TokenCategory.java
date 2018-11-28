package lexical;

public enum TokenCategory {

	MAIN("principal", 1),
	ID( null, 2),
	TEMPTY("vazio", 3),
	TINT("inteiro", 4),
	TLOGIC("logico", 5),
	TCHAR("letra", 6),
	TCCHAR("texto", 7),
	TREAL("real", 8),
	ESCBEGIN("{", 9),
	ESCEND( "}", 10),
	PARAMBEGIN("(", 11),
	PARAMEND(")", 12),
	ARRAYBEGIN("[", 13),
	ARRAYEND("]", 14),
	COMMENT("/$", 15),
	SEP1(",", 16),
	SEP2(";", 17),
	CONSTNUMINT(null, 18),
	CONSTNUMREAL(null, 19),
	CONSTLOGIC(null, 20),
	CONSTCHAR(null, 21),
	CONSTCCHAR(null, 22),
	PRIF("se", 23),
	PRELSE("senao", 24),
	PRELSEIF("senaose", 25),
	PRITERATOR("repita", 26),
	PRDO("faca", 27),
	PRWHILE("enquanto", 28),
	PRPRINTOUT("imprima", 29),
	PRREADIN("leia", 30),
	PRRETURN("retorne", 31),
	OPLOGICAND("e", 32),
	OPLOGICOR("ou", 33),
	OPNEGLOGIC("no", 34),
	OPARITADIT(null, 35),
	OPARITMULT(null, 36),
	OPARITEXP(null, 37),
	OPNEGUN(null, 38),
	OPREL1(null, 39),
	OPREL2(null, 40),
	OPATRIB("=", 41),
	OPCONC("++", 42),
	OPARITMOD(null, 43),
	UNKNOWN(null, 44);

	private String name;
	private int value;
	
	private TokenCategory(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
}
