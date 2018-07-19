package lexical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LexicalTable {

	private static HashMap<String, TokenCategory> lexemMap = new HashMap<String, TokenCategory>();
	private static HashMap<String, TokenCategory> keyWordsMap = new HashMap<String, TokenCategory>();
	private static HashMap<String, TokenCategory> operatorsMap = new HashMap<String, TokenCategory>();
	private static List<Character> symbolList = new ArrayList<Character>();

	public static HashMap<String, TokenCategory> getLexemMap() {
		return lexemMap;
	}

	public static HashMap<String, TokenCategory> getKeyWordsMap() {
		return keyWordsMap;
	}

	public static HashMap<String, TokenCategory> getOperatorsMap() {
		return operatorsMap;
	}

	public static List<Character> getSymbolList() {
		return symbolList;
	}

	static {

		// Operadores

		operatorsMap.put("+", TokenCategory.OPARITADIT);
		operatorsMap.put("-", TokenCategory.OPARITADIT);
		operatorsMap.put("*", TokenCategory.OPARITMULT);
		operatorsMap.put("/", TokenCategory.OPARITMULT);
		operatorsMap.put("^", TokenCategory.OPARITEXP);

		operatorsMap.put("<", TokenCategory.OPREL1);
		operatorsMap.put(">", TokenCategory.OPREL1);
		operatorsMap.put("<=", TokenCategory.OPREL1);
		operatorsMap.put(">=", TokenCategory.OPREL1);
		operatorsMap.put("==", TokenCategory.OPREL2);
		operatorsMap.put("~=", TokenCategory.OPREL2);

		operatorsMap.put("=", TokenCategory.OPATRIB);
		operatorsMap.put("++", TokenCategory.OPCONC);

		// Delimitadores

		lexemMap.put("(", TokenCategory.PARAMBEGIN);
		lexemMap.put(")", TokenCategory.PARAMEND);
		lexemMap.put("{", TokenCategory.ESCBEGIN);
		lexemMap.put("}", TokenCategory.ESCEND);
		lexemMap.put("[", TokenCategory.ARRAYBEGIN);
		lexemMap.put("]", TokenCategory.ARRAYEND);
		lexemMap.put("/$", TokenCategory.COMMENT);

//		// Terminador
//
//		lexemMap.put(";", TokenCategory.TERM);

		// Separador

		lexemMap.put(",", TokenCategory.SEP1);
		lexemMap.put(";", TokenCategory.SEP2);

		// Palavras-reservadas (comandos e operadores)

		keyWordsMap.put("caracter", TokenCategory.TCHAR);
		keyWordsMap.put("cadeia", TokenCategory.TCCHAR);
		keyWordsMap.put("longo", TokenCategory.TLONG);
		keyWordsMap.put("real", TokenCategory.TDEC);
		keyWordsMap.put("inteiro", TokenCategory.TINT);
		keyWordsMap.put("logico", TokenCategory.TLOGIC);
		keyWordsMap.put("principal", TokenCategory.MAJOR);
		keyWordsMap.put("se", TokenCategory.PRIF);
		keyWordsMap.put("entao", TokenCategory.PRELSE);
		keyWordsMap.put("senao", TokenCategory.PRELSEIF);
		keyWordsMap.put("iterador", TokenCategory.PRITERATOR);
		keyWordsMap.put("enquanto", TokenCategory.PRWHILE);
		keyWordsMap.put("execute", TokenCategory.PRDO);
		keyWordsMap.put("verdade", TokenCategory.CONSTLOGIC);
		keyWordsMap.put("falso", TokenCategory.CONSTLOGIC);
		keyWordsMap.put("no", TokenCategory.OPNEGLOGIC);
		keyWordsMap.put("e", TokenCategory.OPLOGICAND);
		keyWordsMap.put("ou", TokenCategory.OPLOGICOR);
		keyWordsMap.put("vazio", TokenCategory.TEMPTY);
		keyWordsMap.put("imprima", TokenCategory.PRPRINTOUT);
		keyWordsMap.put("leia", TokenCategory.PRREADIN);
		keyWordsMap.put("retorne", TokenCategory.PRRETURN);
		
		// Simbolos que podem indicador proximo token
		symbolList.add(' ');
		symbolList.add(',');
		symbolList.add(';');
		symbolList.add('+');
		symbolList.add('-');
		symbolList.add('*');
		symbolList.add('^');
		symbolList.add('\\');
		symbolList.add('/');
		symbolList.add('#');
		symbolList.add('$');
		symbolList.add('<');
		symbolList.add('>');
		symbolList.add('=');
		symbolList.add('~');
		symbolList.add('(');
		symbolList.add(')');
		symbolList.add('[');
		symbolList.add(']');
		symbolList.add('{');
		symbolList.add('}');
		symbolList.add('\'');
		symbolList.add('"');
	}
}
