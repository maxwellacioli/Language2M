package br.com.ic.lexical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LexicalTable {

	public static HashMap<String, TokenCategory> lexemMap = new HashMap<String, TokenCategory>();
	public static List<Character> symbolList = new ArrayList<Character>();

	static {

		// Operadores

		lexemMap.put("+", TokenCategory.OPARITADIT);
		lexemMap.put("-", TokenCategory.OPARITADIT);
		lexemMap.put("*", TokenCategory.OPARITMULT);
		lexemMap.put("/", TokenCategory.OPARITMULT);
		lexemMap.put("^", TokenCategory.OPARITEXP);

		lexemMap.put("<", TokenCategory.OPREL1);
		lexemMap.put(">", TokenCategory.OPREL1);
		lexemMap.put("<=", TokenCategory.OPREL1);
		lexemMap.put(">=", TokenCategory.OPREL1);
		lexemMap.put("==", TokenCategory.OPREL2);
		lexemMap.put("~=", TokenCategory.OPREL2);

		lexemMap.put("=", TokenCategory.OPATRIB);
		lexemMap.put("++", TokenCategory.OPCONC);

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

		lexemMap.put("caracter", TokenCategory.TCHAR);
		lexemMap.put("cadeia", TokenCategory.TCCHAR);
		lexemMap.put("longo", TokenCategory.TLONG);
		lexemMap.put("decimal", TokenCategory.TDEC);
		lexemMap.put("inteiro", TokenCategory.TINT);
		lexemMap.put("logico", TokenCategory.TLOGIC);
		lexemMap.put("principal", TokenCategory.MAJOR);
		lexemMap.put("se", TokenCategory.PRIF);
		lexemMap.put("entao", TokenCategory.PRELSE);
		lexemMap.put("senao", TokenCategory.PRELSEIF);
		lexemMap.put("iterador", TokenCategory.PRITERATOR);
		lexemMap.put("enquanto", TokenCategory.PRWHILE);
		lexemMap.put("execute", TokenCategory.PRDO);
		lexemMap.put("verdade", TokenCategory.CONSTLOGIC);
		lexemMap.put("falso", TokenCategory.CONSTLOGIC);
		lexemMap.put("no", TokenCategory.OPNEGLOGIC);
		lexemMap.put("e", TokenCategory.OPLOGICAND);
		lexemMap.put("ou", TokenCategory.OPLOGICOR);
		lexemMap.put("vazio", TokenCategory.TEMPTY);
		lexemMap.put("imprima", TokenCategory.PRPRINTOUT);
		lexemMap.put("leia", TokenCategory.PRREADIN);
		lexemMap.put("retorne", TokenCategory.PRRETURN);
		
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
