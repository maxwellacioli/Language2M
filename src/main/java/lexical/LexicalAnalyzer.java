package lexical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {

	private List<String> linesList;
	private int currentLine, currentColumn, tkBeginColumn = 0, tkBeginLine = 0;
	private String line;
	private String filePath;

	private final char LINE_BREAK = '\n';

	private Token currentToken;

	private static LexicalAnalyzer lexicalAnalyzer;

	private LexicalAnalyzer() {
		linesList = new ArrayList<String>();
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Token getCurrentToken() {
		return currentToken;
	}

	private void setCurrentToken(Token currentToken) {
		this.currentToken = currentToken;
	}

	public static LexicalAnalyzer getInstance() {
		if(lexicalAnalyzer == null) {
			lexicalAnalyzer = new LexicalAnalyzer();
		}
		return  lexicalAnalyzer;
	}

	public void readFile(List<String> linesList) {
		this.linesList = linesList;
	}

	public boolean hasMoreTokens() {

		if (!linesList.isEmpty()) {
			if (currentLine < linesList.size()) {

				line = linesList.get(currentLine);
				line = line.replace('\t', ' ');

				if (line.substring(currentColumn).matches("\\s*")) {
					currentLine++;
					currentColumn = 0;
					while (currentLine < linesList.size()) {
						line = linesList.get(currentLine);
						if (line.matches("\\s*")) {
							currentLine++;
						} else {
							return true;
						}
					}
				} else if (currentColumn < line.length()) {
					return true;
				} else {
					currentLine++;
					currentColumn = 0;
					while (currentLine < linesList.size()) {
						line = linesList.get(currentLine);
						if (line.matches("\\s*")) {
							currentLine++;
						} else {
							return true;
						}
					}
				}
			}
		}

		return false;

	}

	public Token nextToken() {

		Token token;

		char currentChar;
		String tkValue = "";

		tkBeginColumn = currentColumn;
		tkBeginLine = currentLine;

		currentChar = line.charAt(currentColumn);

		// Ignora sequencia de espacos vazios
		while (currentChar == ' ' || currentChar == '\t') {
			currentChar = nextChar();
			tkBeginColumn++;
		}

		if (Character.toString(currentChar).matches("\\d")) {
			tkValue += currentChar;
			currentChar = nextChar();
			while (Character.toString(currentChar).matches("\\d")) {
				tkValue += currentChar;
				currentChar = nextChar();
			}
			if (currentChar == '.') {
				tkValue += currentChar;
				currentChar = nextChar();
				while (Character.toString(currentChar).matches("\\d")) {
					tkValue += currentChar;
					currentChar = nextChar();
				}
			}

//			if (currentChar != ' ') {
//				while (!LexicalTable.getSymbolList().contains(currentChar)) {
//					tkValue += currentChar;
//
//					// Vai para o proximo
//					currentChar = nextChar();
//					if (currentChar == LINE_BREAK) {
//						break;
//					}
//				}
//			}
		} else {

			// Enquanto nao for encontrado um simbolo especial, os
			// caracteres
			// serao concatenados em uma string que devera ser um token
			// identificador ou palavra chave.
			while (!LexicalTable.getSymbolList().contains(currentChar)) {
				tkValue += currentChar;

				// Vai para o proximo
				currentChar = nextChar();
				if (currentChar == LINE_BREAK) {
					break;
				}
			}
		}

		if (tkValue == "") {
			// Verificaca~o de constantes inteiras ou decimais

			switch (currentChar) {

			case '"': // Compondo um token que possivelmente e' um cchar

				tkValue += currentChar;
				currentChar = nextChar();

				if (currentChar == '"') {
					tkValue += currentChar;
					currentColumn++;
					break;
				}

				// Buscar os pro'ximos caracteres ate' que encontre uma ", ou
				// acabe a linha
				while (currentChar != LINE_BREAK) {
					tkValue += currentChar;
					currentChar = nextChar();

					if (currentChar == '"') {
						tkValue += currentChar;
						currentColumn++;
						break;
					}
				}
				break;

			// TODO Verifica��o de coment�rios
			case '/':
				tkValue += currentChar;
				currentChar = nextChar();

				if (currentChar == '$') {
					tkValue += currentChar;
					currentLine++;
					currentColumn = 0;
				}
				break;

			// TODO AJEITAR -> Verificar se tem abre comentario antes;
			case '$':
				tkValue += currentChar;
				currentChar = nextChar();
				if (currentChar == '/') {
					tkValue += currentChar;
					currentChar = nextChar();
				}
				break;

			case '\'': // Compondo um token que possivelmente e' um char

				tkValue += currentChar;

				// Buscar os pr�ximos dois caracteres
				currentChar = nextChar();
				if (currentChar != LINE_BREAK) {
					tkValue += currentChar;
				}
				currentChar = nextChar();
				if (currentChar == '\'') {
					tkValue += currentChar;
					currentColumn++;
				}
				break;

			// TODO TRATAR N COISAS.... (=)...
			case '<':
			case '>':
			case '~':
			case '=': // Compondo um token que pode ser <=, >=, ~= ou ==

				tkValue += currentChar;
				currentChar = nextChar();
				if (currentChar == '=') {
					tkValue += currentChar;
					currentColumn++;
				}
				break;

			case '+': // Compondo um token que pode ser operador aditivo, de
						// concatenacao ou constante numurica
				tkValue += currentChar;
				currentChar = nextChar();

				if (currentChar == '+') {
					tkValue += currentChar;
					currentChar = nextChar();
				}

				break;

			default:
				tkValue += currentChar;
				currentColumn++;
				break;
			}
		}

		tkValue = tkValue.trim();

		token = new Token();

		token.setLexValue(tkValue);
		token.setLine(tkBeginLine+1);
		token.setColumn(tkBeginColumn+1);
		token.setCategory(analyzeCategory(tkValue));

		//Atualiza o valor da cadeia de caracteres ou caracter
		if(token.getCategory().equals(TokenCategory.CONSTTEXT)) {
			token.setLexValue(tkValue.substring(1, tkValue.length()-1).replace("\\n", "\n"));
		}

		if (token.getCategory().equals(TokenCategory.COMMENT)) {
			if (hasMoreTokens()) {
				return nextToken();
			}
		}

		setCurrentToken(token);
		return token;

	}

	private TokenCategory analyzeCategory(String tkValue) {

		if (isOpNegUnary(tkValue)) {
			return TokenCategory.OPNEGUN;

		} else if (LexicalTable.getLexemMap().containsKey(tkValue)) {
			return LexicalTable.getLexemMap().get(tkValue);

		} else if (LexicalTable.getKeyWordsMap().containsKey(tkValue)) {
			return LexicalTable.getKeyWordsMap().get(tkValue);

		} else if (LexicalTable.getOperatorsMap().containsKey(tkValue)) {
			return LexicalTable.getOperatorsMap().get(tkValue);

		} else if (isCchar(tkValue)) {
			return TokenCategory.CONSTTEXT;

		} else if (isConstInt(tkValue)) {
			return TokenCategory.CONSTNUMINT;

		} else if (isConstDec(tkValue)) {
			return TokenCategory.CONSTNUMREAL;

		} else if (isIdentifier(tkValue)) {
			return TokenCategory.ID;
		}

		return TokenCategory.UNKNOWN;
	}

	private Character nextChar() {

		currentColumn++;

		if (currentColumn < line.length()) {
			return line.charAt(currentColumn);
		} else {
			return LINE_BREAK;
		}

	}

	private Character previousNotBlankChar() {

		int previousColumn = tkBeginColumn - 1;
		char previousChar;

		while (previousColumn >= 0) {
			previousChar = line.charAt(previousColumn);
			if (previousChar != ' ' && previousChar != '\t') {
				return previousChar;
			}
			previousColumn--;
		}
		return null;

	}

	private boolean isOpNegUnary(String tkValue) {

		if (tkValue.equals("-")) { // Decide se o - e' o operador aditivo ou se e'
									// o una'rio negativo

			Character previousChar = previousNotBlankChar();
			if ((previousChar != null)
					&& Character.toString(previousChar).matches("[_a-zA-Z0-9]")) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	private boolean isConstDec(String tkValue) {
		if (tkValue.matches("(\\d)+\\.(\\d)+")) {
			return true;
		} else if (tkValue.matches("(\\d)+\\.")) {
			printError("constante decimal em formato errado.", tkValue);
		}
		return false;
	}

	private boolean isConstInt(String tkValue) {
		if (tkValue.matches("(\\d)+")) {
			return true;
		}
		return false;
	}

	private boolean isCchar(String tkValue) {
		if (tkValue.startsWith("\"") && tkValue.endsWith("\"")) {
			return true;
		} else if (tkValue.startsWith("\"")) {
			printError(
					"cadeia de caracteres na~o fechada corretamente com '\"'.",
					tkValue);
		}
		return false;
	}

	private boolean isChar(String tkValue) {
		if (tkValue.matches("'(.?)'")) {
			return true;
		} else if (tkValue.startsWith("'")) {
			printError("caracter na~o fechado corretamente com '.", tkValue);
		}
		return false;
	}

	private boolean isIdentifier(String tkValue) {

		if (tkValue.matches("[_a-zA-Z][_a-zA-Z0-9]*")) {
			if (tkValue.length() < 16) {
				return true;
			} else {
				printError("identificador muito longo.", tkValue);
			}

			// Caso em que o identificador nao comeca com o caractere esperado.
			// Tambem neo considera tkValue que comeca com ", ' ou numero pois
			// caso
			// algum tkValue nessa condicao chegue ate aqui, e' um cchar ou um
			// char que
			// nao foi propriamente fechado, ou uma constante decimal em formato
			// errado.
		} else if (tkValue.matches("[^_a-zA-Z\"'].*")) {
			printError("identificador nao iniciado com letra ou '_'.", tkValue);

			// Caso em que o identificador come�a com o caracter esperado, mas
			// cont�m algum caracter inv�lido,
		} else if (tkValue.matches("[_a-zA-Z].*")) {
			printError("identificador contem caracter invalido.", tkValue);

		}
		return false;
	}

	private void printError(String string, String token) {
		System.err.println("Erro na <linha, coluna> " + "= <" + (currentLine + 1)
				+ "," + currentColumn + ">. " + "'" + token + "'" + " "
				+ string);
		System.exit(1);
	}
}
