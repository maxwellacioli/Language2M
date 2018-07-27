package syntactic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import lexical.LexicalTable;
import semantic.*;
import syntactic.grammar.Derivation;
import syntactic.grammar.Grammar;
import syntactic.grammar.NonTerminal;
import syntactic.grammar.NonTerminalName;
import syntactic.grammar.OperatorsGrammar;
import syntactic.grammar.GrammarSymbol;
import syntactic.grammar.Terminal;
import lexical.LexicalAnalyzer;
import lexical.Token;
import lexical.TokenCategory;

public class PredictiveAnalyzer {

	private Grammar grammar;
	private PredictiveTable predictiveTable;
	private LexicalAnalyzer lexicalAnalyzer;
	private PrecedenceAnalyzer precedenceAnalyzer;

	//Tabela de Simbolos
	private SymbolTable globalTable;
	private FunctionSymbol functionSymbol;
	private SymbolTable localSymbolTable;
	private Boolean functionFlag;
	private Boolean symbolFlag;
	private Boolean functionReturnFlag;
	private VarType varType;

	//Pilha preditiva
	private Stack<GrammarSymbol> stack;

	//AST
	private List<AST> programAST;
	private Stack<Node> astStack;
	private AST ast;
	private Node node;
	private ArrayList<Node> childrenList;
	private Boolean transferRef;


	//Variavel derivacao auxiliar
	private Derivation derivation;

	public PredictiveAnalyzer(Grammar grammar, PredictiveTable predictiveTable,
							  LexicalAnalyzer lexicalAnalyzer) {

		this.grammar = grammar;
		this.predictiveTable = predictiveTable;
		this.lexicalAnalyzer = lexicalAnalyzer;

		globalTable = new SymbolTable("Global");
		symbolFlag = false;
		functionFlag = false;
		functionReturnFlag = false;

		precedenceAnalyzer = new PrecedenceAnalyzer(lexicalAnalyzer);
		stack = new Stack<GrammarSymbol>();

		//TODO AST
		programAST = new ArrayList<AST>();
		astStack = new Stack<Node>();
		childrenList = new ArrayList<Node>();
		transferRef = false;

		derivation = new Derivation();
	}

	private void changeSymbolFlag() {
		symbolFlag = !symbolFlag;
	}

	private void changeFunctionFlag() {
		functionFlag = !functionFlag;
	}

	private void changeFunctionReturnFlag() {
		functionReturnFlag = !functionReturnFlag;
	}

	public Boolean isType(Token token) {
		String type = token.getLexValue();

		if (type.equals(VarType.INTEIRO.getName()) ||
				type.equals(VarType.REAL.getName()) ||
				type.equals(VarType.CARACTER.getName()) ||
				type.equals(VarType.CADEIA.getName()) ||
				type.equals(VarType.LONGO.getName()) ||
				type.equals(VarType.LOGICO.getName())
				) {
			return true;
		}
		return false;
	}


	public void predictiveAnalyze() {

		GrammarSymbol topGrammarSymbol;
		Token token;
		Terminal terminal;
		NonTerminal topNonTerminal = null;
		Integer derivationNumber;
		Stack<Integer> prodCount = new Stack<Integer>();
		int leftCount;
		int rightCount = 1;
		int rightCountAux;

		if (lexicalAnalyzer.hasMoreTokens()) {

			token = lexicalAnalyzer.nextToken();

			terminal = new Terminal(token);
			NonTerminal program = new NonTerminal(NonTerminalName.PROGRAM);
			stack.push(program);


			prodCount.push(1);

			while (!stack.isEmpty()) {

				topGrammarSymbol = stack.peek();

				if (topGrammarSymbol.isTerminal()) {

					if (topGrammarSymbol.getValue() == terminal.getValue()) {
						//Adiciona simbolos a tabela de simbolo local
//						if(symbolFlag) {
//							if(token.getLexValue().equals(";") || token.getLexValue().equals(")") || token.getLexValue().equals("{")) {
//								changeSymbolFlag();
//							} else if(!token.getLexValue().equals(",") && !isType(token)){
//								Symbol symbol = new Symbol(token.getLexValue(), varType);
//								localSymbolTable.insertSymbol(symbol);
//							}
//						} else if(functionFlag) {
//							if(token.getLexValue().equals(")")) {
//								changeFunctionFlag();
//							}else if(isType(token)) {
//								varType = VarType.getVarType(token.getLexValue());
//								functionSymbol.insertParamType(varType);
//							} else if(!token.getLexValue().equals(",") && !isType(token)){
//								Symbol symbol = new Symbol(token.getLexValue(), varType);
//								localSymbolTable.insertSymbol(symbol);
//							}
//						} else if(functionReturnFlag) {
//							functionSymbol.setType(VarType.getVarType(token.getLexValue()));
//							changeFunctionReturnFlag();
//						}
//
//						//Verifica se o topo da pilha preditiva e da pilha da ast sao iguais
//						if(astStack.peek().getGrammarSymbol() instanceof Terminal) {
//							Terminal nodeaux = (Terminal) astStack.peek().getGrammarSymbol();
//							if(nodeaux.getCategory().equals(token.getCategory())) {
//								astStack.pop();
//								//TODO ATualizar valor lexico do token na AST
//								//node = astStack.pop();
//								//node.setTokenValue(terminal);
//							}
//						}
						stack.pop();

						if (lexicalAnalyzer.hasMoreTokens()) {
							token = lexicalAnalyzer.nextToken();
							terminal = new Terminal(token);
						}

					} else {
						SyntaticAnalyzer.printError(token);
						System.exit(1);
					}

				} else {

					topNonTerminal = (NonTerminal) topGrammarSymbol;
					//Cria a ast da funcao que esta sendo analizada
//					if(topNonTerminal.getName() == NonTerminalName.FUNCTIONS) {
//						//o token = "principal" significa que nao temos uma assintura de funcao
//						if(!token.getLexValue().equals("principal")) {
//							//verifica se a ast esta construida antes de adiciona-la
//							if(ast != null) {
//								programAST.add(ast);
//							}
//							ast = new AST(token.getLexValue());
//							//TODO AST
//							node = new Node(topNonTerminal);
//							ast.setRoot(node);
//							astStack.push(node);
//							functionSymbol = new FunctionSymbol(token.getLexValue(), null);
//							globalTable.insertSymbol(functionSymbol);
//							localSymbolTable = new SymbolTable(token.getLexValue());
//						}
//
//					}else if (topNonTerminal.getName() == NonTerminalName.MAIN) {
//						//TODO AST
//						programAST.add(ast);
//						ast = new AST(token.getLexValue());
//						node = new Node(topNonTerminal);
//						ast.setRoot(node);
//						astStack.push(node);
//
//						localSymbolTable = new SymbolTable(token.getLexValue());
//					}

					//Tabela de Simbolos local
//					if(topNonTerminal.getName() == NonTerminalName.DECLARATION) {
//						changeSymbolFlag();
//						varType = VarType.getVarType(token.getLexValue());
//					}

					//TODO Adicionar no' a AST quando NT for EXPRESSION
					if (topNonTerminal.getName() == NonTerminalName.EXP) {
						if (!OperatorsGrammar.getInstance()
								.getOperatorsGrammarSymbols()
								.contains(terminal.getCategory())) {
							SyntaticAnalyzer.printError(token);
							System.exit(1);

						} else {
							if (precedenceAnalyzer.precedenceAnalysis(token)) {

								stack.pop();
//								topGrammarSymbol = stack.peek();

								terminal = new Terminal(
										precedenceAnalyzer.getEndOfSentence());

							}
						}
					} else {
						derivation = null;

						derivationNumber = predictiveTable
								.getDerivationNumber(
										topNonTerminal.getName(),
										terminal.getCategory());

						if(derivationNumber == 33) {
							System.out.println();
						}

						if (derivationNumber != null) {
							leftCount = prodCount.pop();
							rightCountAux = rightCount;

							//FIXME Copiar por valor e nao por referencia (clonagem) <-------------
							if (grammar.getGrammarMap().get(derivationNumber) != null) {
								derivation = (Derivation) grammar.getGrammarMap().get(
										derivationNumber).clone();
							} else {
								derivation = grammar.getGrammarMap().get(
										derivationNumber);
							}

							if (derivation != null) {
								System.out.print(topNonTerminal.getName() + "("
										+ leftCount + ")" + " = ");
								stack.pop();

								//TODO AST
								//Verifica se uma derivacao contem um comandos antes de remover o elemento do
								//topo da pilha da ast

								//A partir daqui eh feito o empilhamento na pilha preditiva
								GrammarSymbol symb;
								Terminal term;
								NonTerminal nonTerm;
								Node nodeAux;

								for (int i = derivation.getSymbolsList().size() - 1; i >= 0; i--) {
									symb = derivation.getSymbolsList().get(i);
									if (symb.isTerminal()) {
										term = (Terminal) symb;
										stack.push(term);
//										nodeAux = new Node(term);

										//TODO AST
										//TODO Se a categoria for um tipo
									} else {
										nonTerm = (NonTerminal) symb;
										stack.push(nonTerm);
//										nodeAux = new Node(nonTerm);

										//TODO AST
									}

									//stack.push(symb);
								}
								//TODO AST
								// se nao for feita transferencia de referencia adiciona os filhos
//								if(!transferRef) {
//									node.addChildren(childrenList);
//								}
//								childrenList.clear();

								for (int i = 0; i < derivation.getSymbolsList()
										.size(); i++) {
									symb = derivation.getSymbolsList().get(i);
									if (symb.isTerminal()) {
										term = (Terminal) symb;
										System.out.print("'"
												+ term.getCategory().toString()
												.toLowerCase() + "'"
												+ " ");
									} else {
										nonTerm = (NonTerminal) symb;
										if (!nonTerm.getName().equals(
												NonTerminalName.EXP)) {
											System.out.print(nonTerm.getName()
													+ "(" + ++rightCount + ")"
													+ " ");
										} else {

											System.out.print(nonTerm.getName()
													+ " ");
										}
									}
								}
								System.out.println();
							} else {
								System.out.println(topNonTerminal.getName()
										+ "(" + leftCount + ")" + " = epsilon");
								stack.pop();

								//TODO AST
								//Remove o no do pai deste
//								node = astStack.pop();
//								node.removeNode();
							}

							if (rightCount > rightCountAux) {
								int aux = rightCount;
								while (aux > rightCountAux) {
									prodCount.push(aux--);
								}
							}

						} else {
							SyntaticAnalyzer.printError(terminal
									.getTerminalToken());
							System.exit(1);
						}
					}
				}
			}
//			programAST.add(ast);
//			System.out.println("test");
		}
	}
}
