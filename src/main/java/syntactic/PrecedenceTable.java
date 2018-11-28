package syntactic;

import java.util.ArrayList;
import syntactic.grammar.OperatorsGrammar;

public class PrecedenceTable {
	private OperatorsGrammar operatorsGrammar;
	private ArrayList<Integer> lineTable;
	private ArrayList<ArrayList<Integer>> precedenceTableList;
	private static PrecedenceTable precedenceTableSingleton;
	public static final int AC = 100;
	
	//Erro
	public static final int ERROR = -1;
	
	//Empilha e le proximo token
	public static final int ELT = 0;
	
	//Reducoes
	public static final int R1 = 1;
	public static final int R2 = 2;
	public static final int R3 = 3;
	public static final int R4 = 4;
	public static final int R5 = 5;
	public static final int R6 = 6;
	public static final int R7 = 7;
	public static final int R8 = 8;
	public static final int R9 = 9;
	public static final int R10 = 10;
	public static final int R11 = 11;
	public static final int R12 = 12;
	public static final int R13 = 13;
	public static final int R14 = 14;
	public static final int R15 = 15;
	public static final int R16 = 16;
	public static final int R17 = 17;
	public static final int R18 = 18;

	

	private PrecedenceTable() {
		operatorsGrammar = OperatorsGrammar.getInstance();
		lineTable = new ArrayList<Integer>();
		precedenceTableList = new ArrayList<ArrayList<Integer>>();

		loadTable();
	}

	public static PrecedenceTable getInstance() {
		if (precedenceTableSingleton == null) {
			precedenceTableSingleton = new PrecedenceTable();
		}
		return precedenceTableSingleton;
	}

	public ArrayList<ArrayList<Integer>> getPrecedenceTableList() {
		return precedenceTableList;
	}

	public OperatorsGrammar getOperatorsGrammar() {
		return operatorsGrammar;
	}

	private void loadTable() {

		// 'oparitadit' x (todos)
		lineTable.add(R6);
		lineTable.add(R6);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R6);
		lineTable.add(R6);
		lineTable.add(R6);
		lineTable.add(R6);
		lineTable.add(R6);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R6);
		lineTable.add(R6);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'opconc' x (todos)
		lineTable.add(ELT);
		lineTable.add(R5);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R5);
		lineTable.add(R5);
		lineTable.add(R5);
		lineTable.add(R5);
		lineTable.add(R5);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R5);
		lineTable.add(R5);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'oparitmul' x (todos)
		lineTable.add(R7);
		lineTable.add(R7);
		lineTable.add(R7);
		lineTable.add(R7);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R7);
		lineTable.add(R7);
		lineTable.add(R7);
		lineTable.add(R7);
		lineTable.add(R7);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R7);
		lineTable.add(R7);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'oparitmod' x (todos)
		lineTable.add(R8);
		lineTable.add(R8);
		lineTable.add(R8);
		lineTable.add(R8);
		lineTable.add(R8);
		lineTable.add(R8);
		lineTable.add(ELT);
		lineTable.add(R8);
		lineTable.add(R8);
		lineTable.add(R8);
		lineTable.add(R8);
		lineTable.add(R8);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R8);
		lineTable.add(R8);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'opnegun' x (todos)
		lineTable.add(R9);
		lineTable.add(R9);
		lineTable.add(R9);
		lineTable.add(R9);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R9);
		lineTable.add(R9);
		lineTable.add(R9);
		lineTable.add(R9);
		lineTable.add(R9);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R9);
		lineTable.add(R9);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'opneglogic' x (todos os terminais)
		lineTable.add(R10);
		lineTable.add(R10);
		lineTable.add(R10);
		lineTable.add(R10);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R10);
		lineTable.add(R10);
		lineTable.add(R10);
		lineTable.add(R10);
		lineTable.add(R10);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R10);
		lineTable.add(R10);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'parambegin' x (todos)
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ERROR);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'paramend' x (todos)
		lineTable.add(R11);
		lineTable.add(R11);
		lineTable.add(R11);
		lineTable.add(R11);
		lineTable.add(R11);
		lineTable.add(R11);
		lineTable.add(ERROR);
		lineTable.add(R11);
		lineTable.add(R11);
		lineTable.add(R11);
		lineTable.add(R11);
		lineTable.add(R11);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(R11);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'oplogicor' x (todos)
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R1);
		lineTable.add(R1);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ERROR);
		lineTable.add(R1);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'oplogicand' x (todos)
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R2);
		lineTable.add(R2);
		lineTable.add(R2);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R2);
		lineTable.add(R2);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'oprel1' x (todos)
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R4);
		lineTable.add(R4);
		lineTable.add(R4);
		lineTable.add(R4);
		lineTable.add(R4);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R4);
		lineTable.add(R4);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'oprel2' x (todos)
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R3);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R3);
		lineTable.add(R3);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R3);
		lineTable.add(R3);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'constnumint' x (todos)
		lineTable.add(R12);
		lineTable.add(R12);
		lineTable.add(R12);
		lineTable.add(R12);
		lineTable.add(R12);
		lineTable.add(R12);
		lineTable.add(ERROR);
		lineTable.add(R12);
		lineTable.add(R12);
		lineTable.add(R12);
		lineTable.add(R12);
		lineTable.add(R12);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(R12);
		lineTable.add(R12);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'constnumdec' x (todos)
		lineTable.add(R13);
		lineTable.add(R13);
		lineTable.add(R13);
		lineTable.add(R13);
		lineTable.add(R13);
		lineTable.add(R13);
		lineTable.add(ERROR);
		lineTable.add(R13);
		lineTable.add(R13);
		lineTable.add(R13);
		lineTable.add(R13);
		lineTable.add(R13);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(R13);
		lineTable.add(R13);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'constlogic' x (todos)
		lineTable.add(R14);
		lineTable.add(R14);
		lineTable.add(R14);
		lineTable.add(R14);
		lineTable.add(R14);
		lineTable.add(R14);
		lineTable.add(ERROR);
		lineTable.add(R14);
		lineTable.add(R14);
		lineTable.add(R14);
		lineTable.add(R14);
		lineTable.add(R14);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(R14);
		lineTable.add(R14);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		//'constcchar' x (todos)
		lineTable.add(R15);
		lineTable.add(R15);
		lineTable.add(R15);
		lineTable.add(R15);
		lineTable.add(R15);
		lineTable.add(R15);
		lineTable.add(ERROR);
		lineTable.add(R15);
		lineTable.add(R15);
		lineTable.add(R15);
		lineTable.add(R15);
		lineTable.add(R15);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(R15);
		lineTable.add(R15);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'id' x (todos)
		lineTable.add(R16);
		lineTable.add(R16);
		lineTable.add(R16);
		lineTable.add(R16);
		lineTable.add(R16);
		lineTable.add(R16);
		lineTable.add(ELT);
		lineTable.add(R16);
		lineTable.add(R16);
		lineTable.add(R16);
		lineTable.add(R16);
		lineTable.add(R16);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(ERROR);
		lineTable.add(R16);
		lineTable.add(R16);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'sep1' x (todos)
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R18);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(R18);
		lineTable.add(ERROR);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();

		// 'pilha vazia' x (todos)
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ERROR);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ELT);
		lineTable.add(ERROR);
		lineTable.add(AC);
		precedenceTableList.add(lineTable);
		lineTable = new ArrayList<Integer>();
	}
}
