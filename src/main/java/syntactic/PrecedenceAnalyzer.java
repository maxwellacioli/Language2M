package syntactic;

import java.util.ArrayList;
import java.util.Stack;

import org.bytedeco.javacpp.annotation.Const;
import semantic.VarType;
import semantic.commands.Node;
import semantic.commands.expression.*;
import syntactic.grammar.NonTerminal;
import syntactic.grammar.NonTerminalName;
import syntactic.grammar.OperatorsGrammar;
import syntactic.grammar.GrammarSymbol;
import syntactic.grammar.Terminal;
import lexical.LexicalAnalyzer;
import lexical.Token;
import lexical.TokenCategory;

public class PrecedenceAnalyzer {
	private LexicalAnalyzer lexicalAnalyzer;
	private Stack<GrammarSymbol> operatorsStack;
	private Token endOfSentence;
	private Terminal currentTerm;
	private PrecedenceTable precedenceTable;
	private int paramCount = 0;
	private boolean functionCallFlag = false;

	//TODO AST
	private Stack<Exp> expStack;

	private Terminal stackTerm;
	private Terminal tapeTerm;

	public PrecedenceAnalyzer(LexicalAnalyzer lexicalAnalyzer) {
		this.lexicalAnalyzer = lexicalAnalyzer;
		operatorsStack = new Stack<GrammarSymbol>();
		precedenceTable = PrecedenceTable.getInstance();
		expStack = new Stack<Exp>();
	}

	public Token getEndOfSentence() {
		return endOfSentence;
	}

	private void checkEndOfSentence(Token terminal) {
		if (!OperatorsGrammar.getInstance().getOperatorsGrammarSymbols().contains(terminal.getCategory())) {
			endOfSentence = terminal;
		} else if (terminal.getCategory().equals(TokenCategory.PARAMBEGIN)) {
			paramCount++;
		} else if (terminal.getCategory().equals(TokenCategory.PARAMEND)) {
			if (paramCount == 0) {
				endOfSentence = terminal;
			} else {
				paramCount--;
			}
		}
	}

	private void changeFunctionCallFlag() {
		functionCallFlag = !functionCallFlag;
	}

	/*private void printRedution(Stack<GrammarSymbol> operatorsStack, int elementsToPop) {
		System.out.print(NonTerminalName.EXP + "(" + count++ + ")" + " = ");
		GrammarSymbol grammarSymbol;
		Terminal term;
		System.out.print(NonTerminalName.EXP + " = ");

		for (int i = 0 ; i < elementsToPop ; i--) {
			grammarSymbol = operatorsStack.pop();

			if (grammarSymbol.isTerminal()) {
				term = ((Terminal) grammarSymbol).getCategory();
				if (term.getCategoryValue() >= TokenCategory.CONSTNUMINT.getCategoryValue()
						&& term.getCategoryValue() <= TokenCategory.CONSTCCHAR.getCategoryValue()
						|| term.equals(TokenCategory.ID)) {
					System.out.print(term + "(" + "\"" + currentTerm.getTerminalValue() + "\"" + ")" + " ");
				} else {

					System.out.print(term + " ");
				}
			} else {
				nonTerm = (NonTerminal) grammarSymbol;
				System.out.print(nonTerm.getName() + " ");
			}
		}
		System.out.println();
	}*/

	private int getIndexOfTerminalSymbol(Terminal terminal) {
		return OperatorsGrammar.getInstance().getOperatorsGrammarSymbols().indexOf(terminal.getCategory());
	}

	private void handlerError() {
//		throw new RuntimeException("Handler invalido para reducao!");
		System.out.println("Error");
		System.exit(1);
	}

	//TODO AST
	private void createBinaryNode(Terminal op) {
		Node right;
		Node left;
		if(!functionCallFlag) {
			right = expStack.pop();
			left = expStack.pop();
		} else {
			int size = expStack.peek().getChildren().size();
			right = expStack.peek().getChildren().remove(size - 1);
			left = expStack.peek().getChildren().remove(size - 2);
		}

		switch (op.getCategory()) {
			case OPLOGICOR:
				expStack.push(new OpBinaryOr(op.getToken(), left, right));
				break;
			case OPLOGICAND:
				expStack.push(new OpBinaryAnd(op.getToken(), left, right));
				break;
			case OPREL2:
				expStack.push(new OpBinaryRel2(op.getToken(), left, right));
				break;
			case OPREL1:
				expStack.push(new OpBinaryRel1(op.getToken(), left, right));
				break;
			case OPCONC:
				expStack.push(new OpBinaryConc(op.getToken(), left, right));
				break;
			case OPARITADIT:
				if(functionCallFlag) {
					expStack.peek().addChild(new OpBinaryArithAdit(op.getToken(), left, right));
				} else {
					expStack.push(new OpBinaryArithAdit(op.getToken(), left, right));
				}
				break;
			case OPARITMULT:
				if(functionCallFlag) {
					expStack.peek().addChild(new OpBinaryMult(op.getToken(), left, right));
				} else {
					expStack.push(new OpBinaryMult(op.getToken(), left, right));
				}
				break;
			case OPARITEXP:
				if(functionCallFlag) {
					expStack.peek().addChild(new OpBinaryExp(op.getToken(), left, right));
				} else {
					expStack.push(new OpBinaryExp(op.getToken(), left, right));
				}
				break;
		}
	}

	private Node createId(Token token) {
		switch (token.getCategory()) {
			case CONSTNUMINT:
				return new Id(currentTerm.getToken(), VarType.INTEIRO);
			case CONSTCCHAR:
				return new Id(currentTerm.getToken(), VarType.CADEIA);
			case CONSTNUMREAL:
				return new Id(currentTerm.getToken(), VarType.REAL);
			case CONSTLOGIC:
				return new Id(currentTerm.getToken(), VarType.LOGICO);
			case CONSTCHAR:
				return new Id(currentTerm.getToken(), VarType.CARACTER);
		}
		return null;
	}

	private Node createConstant(Token token) {
		switch (token.getCategory()) {
			case CONSTNUMINT:
				return new IntegerConstant(currentTerm.getToken());
			case CONSTCCHAR:
				return new ChainCharConstant(currentTerm.getToken());
			case CONSTNUMREAL:
				return new RealConstant(currentTerm.getToken());
			case CONSTLOGIC:
				return new LogicConstant(currentTerm.getToken());
			case CONSTCHAR:
				return new CharConstant(currentTerm.getToken());
		}
		return null;
	}



	private void createFunctionCallNode(Terminal functionName) {
		expStack.push(new FunctionCall(functionName.getToken()));
	}

	public Exp precedenceAnalysis(Token token) {
		int tableValue;
		int tableAux;
		int count = 1;
		endOfSentence = null;
		operatorsStack.removeAllElements();
		expStack.removeAllElements();

		checkEndOfSentence(token);

		while (true) {
			// Se pv e eof no cabecote => Aceita!
			if ((operatorsStack.size() == 1) && !operatorsStack.peek().isTerminal() && (endOfSentence != null)) {
				System.out.println();
				return expStack.peek();
			} else {
				if (((operatorsStack.size() == 1) && !operatorsStack.peek().isTerminal()) || (endOfSentence != null)
						|| operatorsStack.isEmpty()) {
					tableAux = OperatorsGrammar.getInstance().getOperatorsGrammarSymbols().size();

					// Se pv e terminal no cabecote
					if (operatorsStack.isEmpty()
							|| ((operatorsStack.size() == 1) && !operatorsStack.peek().isTerminal())) {
						tapeTerm = new Terminal(token);

						tableValue = precedenceTable.getPrecedenceTableList().get(tableAux)
								.get(getIndexOfTerminalSymbol(tapeTerm));

					} // Se terminal no top da pilha e eof no cabecote
					else {
						if (!operatorsStack.peek().isTerminal()) {
							stackTerm = (Terminal) operatorsStack.elementAt(operatorsStack.size() - 2);
						} else {
							stackTerm = (Terminal) operatorsStack.peek();
						}

						tableValue = precedenceTable.getPrecedenceTableList().get(getIndexOfTerminalSymbol(stackTerm))
								.get(tableAux);
					}
					// end
				} else {
					if (!operatorsStack.peek().isTerminal()) {
						stackTerm = (Terminal) operatorsStack.elementAt(operatorsStack.size() - 2);
					} else {
						stackTerm = (Terminal) operatorsStack.peek();
					}

					tableValue = precedenceTable.getPrecedenceTableList().get(getIndexOfTerminalSymbol(stackTerm))
							.get(getIndexOfTerminalSymbol(new Terminal(token)));

				}

				// Verificacao da acao

				if (tableValue == PrecedenceTable.ELT) { // Acao ELT

					//terminal que vai para a pilha de operadores
					Terminal termToStack = new Terminal((token));
					operatorsStack.push(termToStack);

					if (lexicalAnalyzer.hasMoreTokens()) {
						token = lexicalAnalyzer.nextToken();
					}

					if(termToStack.getCategory().equals(TokenCategory.ID) && token.getLexValue().equals("(")) {
						createFunctionCallNode(termToStack);
						changeFunctionCallFlag();
					}
					checkEndOfSentence(token);

				} else if (tableValue > PrecedenceTable.ELT) { // Acao Reduz

					// Se a producao for 11, sera necessario 2 acoes pop para
					// tirar o '(' e ')'
					// referente a producao EXP = ( EXP )

					if (tableValue >= PrecedenceTable.R12 && tableValue <= PrecedenceTable.R17) {
						currentTerm = (Terminal) operatorsStack.pop();
						operatorsStack.push(new NonTerminal(NonTerminalName.EXP));

						//TODO AST
						if(tableValue != PrecedenceTable.R17) {
							Node constant = createConstant(currentTerm.getToken());
							if(functionCallFlag) {
								expStack.peek().addChild(constant);
							} else {
								expStack.push((Exp)constant);
							}
						} else {
							currentTerm = (Terminal) operatorsStack.pop();
							if(functionCallFlag) {
								expStack.peek().addChild(createId(currentTerm.getToken()));
							} else {
								expStack.push((Exp)createId(currentTerm.getToken()));
							}
						}

						//reducoes com operadores unarios
					} else if (tableValue == PrecedenceTable.R8 || tableValue == PrecedenceTable.R9) {
						if (!operatorsStack.peek().isTerminal()) {
							if (operatorsStack.elementAt(operatorsStack.size() - 2).isTerminal()) {
								operatorsStack.pop();
								operatorsStack.pop();
								operatorsStack.push(new NonTerminal(NonTerminalName.EXP));
							} else {
								handlerError();
							}
						}
					}
					//reducao da producao E = id ( E )
					else if (tableValue == PrecedenceTable.R18) {
						if (operatorsStack.peek().isTerminal()) {
							if (!operatorsStack.elementAt(operatorsStack.size() - 2).isTerminal()) {
								if (operatorsStack.elementAt(operatorsStack.size() - 3).isTerminal()) {
									if (operatorsStack.elementAt(operatorsStack.size() - 4).isTerminal()) {
										operatorsStack.pop();
										operatorsStack.pop();
										operatorsStack.pop();
										operatorsStack.pop();
										operatorsStack.push(new NonTerminal(NonTerminalName.EXP));
									} else {
										handlerError();
									}
								} else {
									handlerError();
								}
							} else {
								handlerError();
							}
						} else {
							handlerError();
						}
					} else if (tableValue == PrecedenceTable.R11) {
						if (operatorsStack.size() > 3
								&& (operatorsStack.elementAt(operatorsStack.size() - 4).isTerminal())) {
							Terminal termAux = (Terminal) operatorsStack.elementAt(operatorsStack.size() - 4);

							if (termAux.getCategory() == TokenCategory.ID) {
								tableValue = PrecedenceTable.R18;
							}
						}
						if (operatorsStack.peek().isTerminal()) {
							if (!operatorsStack.elementAt(operatorsStack.size() - 2).isTerminal()) {
								if (operatorsStack.elementAt(operatorsStack.size() - 3).isTerminal()) {
									operatorsStack.pop();
									operatorsStack.pop();
									operatorsStack.pop();

									// Caso seja R18 desempilha o id no indice
									// topo -4
									if (tableValue == PrecedenceTable.R18) {
										currentTerm = ((Terminal) operatorsStack.pop());

										//Desativa a adição de parametros na chamada de função
										changeFunctionCallFlag();
									}

									operatorsStack.push(new NonTerminal(NonTerminalName.EXP));
								} else {
									handlerError();
								}
							} else {
								handlerError();
							}
						} else {
							handlerError();
						}
					} else {
						if (!operatorsStack.peek().isTerminal()) {
							GrammarSymbol symbol = operatorsStack.elementAt(operatorsStack.size() - 2);

							if (symbol.isTerminal()) {
								if ((operatorsStack.size() > 2)
										&& !operatorsStack.elementAt(operatorsStack.size() - 3).isTerminal()) {
									operatorsStack.pop();
									operatorsStack.pop();
									operatorsStack.pop();
									operatorsStack.push(new NonTerminal(NonTerminalName.EXP));

									//TODO AST
									//Se o operador da redução for um ',', então não se cria um nó binario
									//porque faz parte da redução de parametros de uma chamada de função
									if(!stackTerm.getToken().getCategory().equals(TokenCategory.SEP1)) {
										createBinaryNode((Terminal) symbol);
									}
								} else {
									handlerError();
								}
							} else {
								handlerError();
							}
						} else {
							handlerError();
						}
					}
					
					ArrayList<GrammarSymbol> derivation = OperatorsGrammar.getInstance().getOperatorDerivation(tableValue - 1)
							.getSymbolsList();
					TokenCategory term;
					NonTerminal nonTerm;

					System.out.print(NonTerminalName.EXP + "(" + count++ + ")" + " = ");

					for (GrammarSymbol grammarSymbol : derivation) {
						if (grammarSymbol.isTerminal()) {
							term = ((Terminal) grammarSymbol).getCategory();
							if (term.getValue() >= TokenCategory.CONSTNUMINT.getValue()
									&& term.getValue() <= TokenCategory.CONSTCCHAR.getValue()
									|| term.equals(TokenCategory.ID)) {
								System.out.print(term + "(" + "\"" + currentTerm.getTerminalValue() + "\"" + ")" + " ");
							} else {

								System.out.print(term + " ");
							}
						} else {
							nonTerm = (NonTerminal) grammarSymbol;
							System.out.print(nonTerm.getName() + " ");
						}
					}
					System.out.println();

				} else { // Acao ERRO
					SyntaticAnalyzer.printError(token);
					System.exit(1);
				}
			}
		}
	}
}
