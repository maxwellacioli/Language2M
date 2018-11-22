package syntactic.grammar;

public enum NonTerminalName {

	PROGRAM(1),
	FUNCTIONS(2),
	FUNCTIONSREC(3),
	MAIN(4),
	PARAMS(5),
	LISTPARAMS(6),
	LISTPARAMSREC(7),
	TYPE(8),
	ESCOPE(9),
	CMDS(10),
	CMDSREC(11),
	CMD(12),
	IFELSEFAT(13),
	ATTRIB(14),
	LISTNAME(15),
	LISTNAMEREC(16),
	EXP(17);

	private int value;

	private NonTerminalName(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
