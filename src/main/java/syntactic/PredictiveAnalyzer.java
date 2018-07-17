package syntactic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

	private Stack<GrammarSymbol> stack;

	//AST
	private List<AST> programAST;
	private Stack<Node> astStack;
	private AST ast;
	private Node node;
	private ArrayList<Node> childrenList;

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

		if(type.equals(VarType.INTEIRO.getName()) ||
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
		NonTerminal topNonTerminal;
		Integer derivationNumber;
		Stack<Integer> prodCount = new Stack<Integer>();
		int leftCount = 0;
		int rightCount = 1;
		int rightCountAux = 0;

		if (lexicalAnalyzer.hasMoreTokens()) {

			token = lexicalAnalyzer.nextToken();

			terminal = new Terminal(token);
			NonTerminal program = new NonTerminal(NonTerminalName.PROGRAM);
			//TODO AST
//			node = new Node(program);
//			ast.setRoot(node);
//			astStack.push(node);

			stack.push(program);

			prodCount.push(1);

			while (!stack.isEmpty()) {

				topGrammarSymbol = stack.peek();

				if (topGrammarSymbol.isTerminal()) {

					if (topGrammarSymbol.getValue() == terminal.getValue()) {
						//Adiciona simbolos a tabela de simbolo local
						if(symbolFlag) {
							if(token.getLexValue().equals(";") || token.getLexValue().equals(")") || token.getLexValue().equals("{")) {
								changeSymbolFlag();
							} else if(!token.getLexValue().equals(",") && !isType(token)){
								Symbol symbol = new Symbol(token.getLexValue(), varType);
								localSymbolTable.insertSymbol(symbol);
							}
						} else if(functionFlag) {
							if(token.getLexValue().equals(")")) {
								changeFunctionFlag();
							}else if(isType(token)) {
								varType = VarType.getVarType(token.getLexValue());
								functionSymbol.insertParamType(varType);
							} else if(!token.getLexValue().equals(",") && !isType(token)){
								Symbol symbol = new Symbol(token.getLexValue(), varType);
								localSymbolTable.insertSymbol(symbol);
							}
						} else if(functionReturnFlag) {
							functionSymbol.setType(VarType.getVarType(token.getLexValue()));
							changeFunctionReturnFlag();
						}

						stack.pop();

						//TODO AST
//						node = astStack.pop();
//						node.setTokenValue(terminal);

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

					//TODO Adicionar nome da funcao e params a tabela global e criar a tabela de simbolos da funcao
					//Cria a ast da funcao que esta sendo analizada
					if(topNonTerminal.getName() == NonTerminalName.FUNCTIONS) {
						//o token = "principal" significa que nao temos uma assintura de funcao
						if(!token.getLexValue().equals("principal")) {
							//verifica se a ast esta construida antes de adiciona-la
							if(ast != null) {
								programAST.add(ast);
							}
							ast = new AST(token.getLexValue());
							functionSymbol = new FunctionSymbol(token.getLexValue(), null);
							globalTable.insertSymbol(functionSymbol);
							localSymbolTable = new SymbolTable(token.getLexValue());
						}

					} else if(topNonTerminal.getName() == NonTerminalName.PARAMSFAT) {
						changeFunctionFlag();
					} else if(topNonTerminal.getName() == NonTerminalName.RETURNTYPE) {
						changeFunctionReturnFlag();
					}else if (topNonTerminal.getName() == NonTerminalName.MAJORF) {
						programAST.add(ast);
						ast = new AST(token.getLexValue());
						localSymbolTable = new SymbolTable(token.getLexValue());
					}

					//TODO Tabela de Simbolos local
					if(topNonTerminal.getName() == NonTerminalName.DECLARATION) {
						changeSymbolFlag();
						varType = VarType.getVarType(token.getLexValue());
					}

					//TODO Adicionar no' a AST quando NT for EXPRESSION
					if (topNonTerminal.getName() == NonTerminalName.EXPRESSION) {
						if (!OperatorsGrammar.getInstance()
								.getOperatorsGrammarSymbols()
								.contains(terminal.getCategory())) {
							SyntaticAnalyzer.printError(token);
							System.exit(1);

						} else {
							if (precedenceAnalyzer.precedenceAnalysis(token)) {

								stack.pop();
								topGrammarSymbol = stack.peek();

								terminal = new Terminal(
										precedenceAnalyzer.getEndOfSentence());

							}
						}
					} else {

						derivationNumber = null;
						derivation = null;

						if (topNonTerminal.getName() == NonTerminalName.VALUE
								&& terminal.getCategory() != TokenCategory.ARRAYBEGIN) {

							derivationNumber = Grammar.EXPRESSION;

						} else {
							derivationNumber = predictiveTable
									.getDerivationNumber(
											topNonTerminal.getName(),
											terminal.getCategory());
						}

						if (derivationNumber != null) {
							leftCount = prodCount.pop();
							rightCountAux = rightCount;

							//FIXME Copiar por valor e nao por referencia (clonagem)
							if(grammar.getGrammarMap().get(derivationNumber) != null) {
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
//								node = astStack.pop();
//								Node nodeAux;

								// TODO REMOVE
								GrammarSymbol symb;
								Terminal term;
								NonTerminal nonTerm;

								for (int i = derivation.getSymbolsList().size() - 1; i >= 0; i--) {
									symb = derivation.getSymbolsList().get(i);
//									if (symb.isTerminal()) {
//										term = (Terminal) symb;
//										stack.push(term);
//
//										nodeAux = new Node(term);
//									} else {
//										nonTerm = (NonTerminal) symb;
//										stack.push(nonTerm);
//
//										nodeAux = new Node(nonTerm);
//									}

									stack.push(symb);

									//TODO AST
//									nodeAux = new Node(symb);
//									childrenList.add(nodeAux);
//									astStack.push(nodeAux);
								}
								//TODO AST
//								node.addChildren(childrenList);
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
												NonTerminalName.EXPRESSION)) {
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
//								node = astStack.pop();
//								node.addChild(new Node());
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
			programAST.add(ast);
			System.out.println("test");
		}
	}
}
