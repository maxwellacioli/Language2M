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

	//Funcao que verifica se uma derivacao tem um comando
	//caso tenha, pode desempilhar o elemento do top da pilha da ast
	private Boolean hasCommand(Derivation derivation) {
		for (GrammarSymbol grammarSymbol:
			 derivation.getSymbolsList()) {
			if(grammarSymbol instanceof NonTerminal) {
				NonTerminal nonTerminal = (NonTerminal) grammarSymbol;
				if(Commands.getInstance().isCommand(nonTerminal.getName())) {
					return true;
				}
			}
		}
		
		return false;
	}

	//Verifica se a derivacao tem apenas um nao terminal como comando
	private Boolean hasToTransferReference(Derivation derivation) {
		int count = 0;
		for (GrammarSymbol grammarSymbol:
				derivation.getSymbolsList()) {
			if(grammarSymbol instanceof NonTerminal) {
				NonTerminal nonTerminal = (NonTerminal) grammarSymbol;
				if(Commands.getInstance().isCommand(nonTerminal.getName())) {
					count++;
				}
			}
		}

		if(count == 1) {
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

						//Verifica se o topo da pilha preditiva e da pilha da ast sao iguais
						if(astStack.peek().getGrammarSymbol() instanceof Terminal) {
							Terminal nodeaux = (Terminal) astStack.peek().getGrammarSymbol();
							if(nodeaux.getCategory().equals(token.getCategory())) {
								astStack.pop();
								//TODO ATualizar valor lexico do token na AST
								//node = astStack.pop();
								//node.setTokenValue(terminal);
							}
						}
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
					if(topNonTerminal.getName() == NonTerminalName.FUNCTIONS) {
						//o token = "principal" significa que nao temos uma assintura de funcao
						if(!token.getLexValue().equals("principal")) {
							//verifica se a ast esta construida antes de adiciona-la
							if(ast != null) {
								programAST.add(ast);
							}
							ast = new AST(token.getLexValue());
							//TODO AST
							node = new Node(topNonTerminal);
							ast.setRoot(node);
							astStack.push(node);
							functionSymbol = new FunctionSymbol(token.getLexValue(), null);
							globalTable.insertSymbol(functionSymbol);
							localSymbolTable = new SymbolTable(token.getLexValue());
						}

					} else if(topNonTerminal.getName() == NonTerminalName.PARAMSFAT) {
						changeFunctionFlag();
					} else if(topNonTerminal.getName() == NonTerminalName.RETURNTYPE) {
						changeFunctionReturnFlag();
					}else if (topNonTerminal.getName() == NonTerminalName.MAJORF) {
						//TODO AST
						programAST.add(ast);
						ast = new AST(token.getLexValue());
						node = new Node(topNonTerminal);
						ast.setRoot(node);
						astStack.push(node);

						localSymbolTable = new SymbolTable(token.getLexValue());
					}

					//Tabela de Simbolos local
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
//								topGrammarSymbol = stack.peek();

								terminal = new Terminal(
										precedenceAnalyzer.getEndOfSentence());

							}
						}
					} else {
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

							//FIXME Copiar por valor e nao por referencia (clonagem) <-------------
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
								NonTerminal nonTermAux = (NonTerminal) stack.pop();

								//TODO AST
								//Verifica se uma derivacao contem um comandos antes de remover o elemento do
								//topo da pilha da ast
								if(hasCommand(derivation)) {
									node = astStack.pop();
								}

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
										nodeAux = new Node(term);

										//TODO AST
										//Adiciona o operador ou palavra chave ao nome do no interno
										if(LexicalTable.getOperatorsMap().containsValue(term.getCategory()) ||
												LexicalTable.getKeyWordsMap().containsValue(term.getCategory())) {
											node.setName(term.getToken().getCategory().getName());
										} else if(term.getCategory().equals(TokenCategory.ID)) {
											//Antes de adicionar como filho verifica se o pai eh um comando
											if(node.getGrammarSymbol() instanceof NonTerminal) {
												NonTerminal nonT = (NonTerminal) node.getGrammarSymbol();
												if(Commands.getInstance().isCommand(nonT.getName())) {
													childrenList.add(nodeAux);
													astStack.push(nodeAux);
												}
											}
										}

										//TODO Se a categoria for um tipo
									} else {
										nonTerm = (NonTerminal) symb;
										stack.push(nonTerm);
										nodeAux = new Node(nonTerm);

										//TODO AST
										//Se encontrar uma declaracao, remove o pai dela da arvore
										if(nonTerm.getName().equals(NonTerminalName.DECLARATION)) {
											node.removeNode();
										} else {
											if (Commands.getInstance().isCommand(nonTerm.getName())) {
												//FIXME codigo
												if(nonTerm.getName().equals(NonTerminalName.TYPE)) {
													NonTerminal parent = (NonTerminal) node.getParent().getGrammarSymbol();
													if(parent.getName().equals(NonTerminalName.READIN) ||
															parent.getName().equals(NonTerminalName.CASTING)) {
														childrenList.add(nodeAux);
														astStack.push(nodeAux);
													}
												} else if(nonTerm.getName().equals(NonTerminalName.NAME)) {
													NonTerminal parent = (NonTerminal) node.getParent().getGrammarSymbol();
													if(parent.getName().equals(NonTerminalName.PARAMITEM) ||
															parent.getName().equals(NonTerminalName.READIN)) {
														childrenList.add(nodeAux);
														astStack.push(nodeAux);
													}
												} else {
													childrenList.add(nodeAux);
													astStack.push(nodeAux);
												}
											}
										}
									}

//									stack.push(symb);
								}
//								//TODO AST (se nao for feita transferencia de referencia)
//								node.addChildren(childrenList);
								childrenList.clear();

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

								//Remove o no do pai deste
								node = astStack.pop();
								node.removeNode();
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
//			System.out.println("test");
		}
	}
}
