package syntactic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import lexical.TokenCategory;

//Headers required by LLVM

import semantic.*;
import semantic.commands.*;
import semantic.commands.expression.Exp;
import semantic.commands.expression.Id;
import syntactic.grammar.Derivation;
import syntactic.grammar.Grammar;
import syntactic.grammar.NonTerminal;
import syntactic.grammar.NonTerminalName;
import syntactic.grammar.OperatorsGrammar;
import syntactic.grammar.GrammarSymbol;
import syntactic.grammar.Terminal;
import lexical.LexicalAnalyzer;
import lexical.Token;

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
	private final int DECLARATION_DERIVATION = 19;
	private final int CASTING_DERIVATION = 28;
	private final int PARAM_DERIVATION = 7;
	private final int CMDREC_DERIVATION = 17;

	//Pilha preditiva
	private Stack<GrammarSymbol> stack;

	//FunctionAST
//	private List<FunctionAST> programFunctionAst;
	private FunctionAST functionAst;
	private Stack<Node> astStack;
	private Node node;
	private boolean semanticActionFlag;

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

		//TODO FunctionAST
//		programFunctionAst = new ArrayList<FunctionAST>();
		astStack = new Stack<Node>();
		node = null;
		functionAst = null;
		//desativa a flag quando nao e' para executar acoes semanticas
		//no caso das derivacoes 19 e 28 da gramatica
		semanticActionFlag = true;

		derivation = new Derivation();
	}

//	public List<FunctionAST> getProgramASTList() {
//		return programFunctionAst;
//	}

	private void changeSymbolFlag() {
		symbolFlag = !symbolFlag;
	}

	private void changeFunctionFlag() {
		functionFlag = !functionFlag;
	}

	private void changeFunctionReturnFlag() {
		functionReturnFlag = !functionReturnFlag;
	}

	private void changeSemanticActionFlag() {
		semanticActionFlag = !semanticActionFlag;
	}

	private Boolean isType(Token token) {
		String type = token.getLexValue();

		return (type.equals(VarType.INTEIRO.getName()) ||
				type.equals(VarType.REAL.getName()) ||
				type.equals(VarType.CARACTER.getName()) ||
				type.equals(VarType.TEXT.getName()) ||
				type.equals(VarType.LOGICO.getName())
				);
	}

	private Boolean isId(Token token) {
		return (token.getCategory().equals(TokenCategory.ID));
	}

	private void changeNodeReference(Node node, Node child) {
		Node parent;
		parent = node.getParent();

		int index = parent.getChildren().indexOf(node);

		node.removeNode();
		parent.addChild(index, child);
	}

	private void derivationSemantincAction(int derivationNumber) {
		Node cmd = null;
		Node listCmds = null;
		Node cmdsRec = null;
		Node escope = null;
		Node attribution = null;
		Node doCmd = null;
		Node id = null;
		Node ifCmd = null;
		Node ifElse = null;
		Node elseNode = null;
		Node iterator = null;
		Node printout = null;
		Node readIn = null;
		Node returnCmd = null;
		Node whileCmd = null;
		Node exp = null;
		Node parent = null;

		switch (derivationNumber) {
			case 15:
				listCmds = new ListCmds();
				node = astStack.pop();
				if(node.isRoot()) {
					functionAst.setRoot(listCmds);
				} else {
					changeNodeReference(node, listCmds);
				}
				astStack.push(listCmds);
				break;
			case 16:
				cmd = new Cmd();
				cmdsRec = new CmdsRec();
				node = astStack.pop();

				if(node.isRoot()) {
					functionAst.setRoot(new ListCmds(cmd, cmdsRec));
				} else {
					changeNodeReference(node, new ListCmds(cmd, cmdsRec));
				}

				astStack.push(cmdsRec);
				astStack.push(cmd);
				break;
			case 17:
				cmd = new Cmd();
				cmdsRec = new CmdsRec();
				node = astStack.pop();

				if(node.isRoot()) {
					functionAst.setRoot(new ListCmds(cmd, cmdsRec));
				} else {
					changeNodeReference(node, new ListCmds(cmd, cmdsRec));
				}

				astStack.push(cmdsRec);
				astStack.push(cmd);
				break;
			case 18:
				node = astStack.pop();
				node.removeNode();
				break;
			case 20:
				attribution = new Attribution();
				node = astStack.pop();

				changeNodeReference(node, attribution);

				astStack.push(attribution);
				break;
			case 21:
				exp = new Exp();
				node = astStack.pop();

				changeNodeReference(node, new Printout(exp));

				astStack.push(exp);
				break;
			case 22:
				id = new Id();
				node = astStack.pop();

				changeNodeReference(node, new ReadIn(id));

				astStack.push(id);
				break;
			case 23:
				exp = new Exp();
				escope = new Escope();
				elseNode = new Else();
				node = astStack.pop();

				changeNodeReference(node, new If(exp, escope, elseNode));

				astStack.push(elseNode);
				astStack.push(escope);
				astStack.push(exp);
				break;
			case 24:
				exp = new Exp();
				escope = new Escope();
				node = astStack.pop();

				changeNodeReference(node, new While(exp, escope));

				astStack.push(escope);
				astStack.push(exp);
				break;
			case 26:
				attribution = new Attribution();
				exp = new Exp();
				Node attribution1 = new Attribution();
				escope = new Escope();
				node = astStack.pop();

				changeNodeReference(node, new Iterator(attribution, exp, attribution1, escope));

				astStack.push(escope);
				astStack.push(attribution1);
				astStack.push(exp);
				astStack.push(attribution);
				break;
			case 27:
				exp = new Exp();
				node = astStack.pop();

				changeNodeReference(node, new Return(exp));

				astStack.push(exp);
				break;
			case 28:
				escope = new Escope();
				node = astStack.pop();

				changeNodeReference(node, escope);

				Node ie = new IfElse(node.getParent().getChildren().get(0),
						node.getParent().getChildren().get(1), node.getParent().getChildren().get(2));

				changeNodeReference(node.getParent(), ie);

				astStack.push(escope);
				break;
			case 29:
				node = astStack.pop();
				node.removeNode();
				break;

			case 30:
				id = new Id();
				exp = new Exp();
				node = astStack.pop();

				changeNodeReference(node, new Attribution(id, exp));

				astStack.push(exp);
				astStack.push(id);
		}
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

//				if(token.getLexValue().equals("repita")){
//					System.out.println("test");
//				}

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
								changeFunctionReturnFlag();
							}else if(isType(token)) {
								varType = VarType.getVarType(token.getLexValue());
								functionSymbol.insertParamType(varType);
							} else if(!token.getLexValue().equals(",") && !isType(token)){
								Symbol symbol = new Symbol(token.getLexValue(), varType);
								localSymbolTable.insertSymbol(symbol);
								//Parametros da funcao
								functionSymbol.insertFuncParam(token.getLexValue(), varType);
							}
						} else if(functionReturnFlag) {
							if(!token.getLexValue().equals(")")) {
								VarType retType = VarType.getVarType(token.getLexValue());
								functionSymbol.setType(retType);
								changeFunctionReturnFlag();
							}
						}

						//TODO FunctionAST
						if(!astStack.isEmpty()) {
							//Verifica se no topo da pilha e' um id
							if(astStack.peek() instanceof Id) {
//								astStack.pop();
								//TODO ATualiza token no topo da FunctionAST
								Node id = astStack.pop();
								if(token.getCategory().equals(TokenCategory.ID)) {
									id.setName(token.getLexValue());
								}
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
					//Cria a functionAst da funcao que esta sendo analizada
					if(topNonTerminal.getName() == NonTerminalName.FUNCTIONSREC) {
						//se o token atual for "principal" significa que nao temos uma assintura de funcao
						if(!token.getLexValue().equals("principal")) {
							functionSymbol = new FunctionSymbol(token.getLexValue(), null);
							globalTable.insertSymbol(functionSymbol);
							localSymbolTable = new SymbolTable(token.getLexValue());

							functionAst = new FunctionAST(token.getLexValue(), localSymbolTable, functionSymbol);
							ProgramAST.getInstance().insertAst(functionAst);
							node = new Escope();
							functionAst.setRoot(node);
							astStack.push(node);
						}
					}
 						else if (topNonTerminal.getName() == NonTerminalName.MAIN) {
						functionAst = new FunctionAST(token.getLexValue());
						ProgramAST.getInstance().insertAst(functionAst);
						node = new Escope();
						functionAst.setRoot(node);
						astStack.push(node);

						localSymbolTable = new SymbolTable(token.getLexValue());
					}

					//TODO Adicionar no' a FunctionAST quando NT for EXPRESSION
					if (topNonTerminal.getName() == NonTerminalName.EXP) {
						if (!OperatorsGrammar.getInstance()
								.getOperatorsGrammarSymbols()
								.contains(terminal.getCategory())) {
							SyntaticAnalyzer.printError(token);
							System.exit(1);

						} else {
							//TODO FunctionAST
							Node exp = precedenceAnalyzer.precedenceAnalysis(token, localSymbolTable);

							if (exp != null) {

								stack.pop();
//								topGrammarSymbol = stack.peek();

								terminal = new Terminal(
										precedenceAnalyzer.getEndOfSentence());

								//FIXME Fazer acoes semanticas para estes NT
								if(astStack.peek() instanceof Exp) {
									changeNodeReference(astStack.pop(), exp);
								}
							}
						}
					} else {
						derivation = null;

						if(topNonTerminal.getName().equals(NonTerminalName.LISTNAME)) {
							System.out.println();
						}

						derivationNumber = predictiveTable
								.getDerivationNumber(
										topNonTerminal.getName(),
										terminal.getCategory());

						if (derivationNumber != null) {

							// Tabela de Simbolos local
							if(derivationNumber == DECLARATION_DERIVATION) {
								changeSymbolFlag();
								varType = VarType.getVarType(token.getLexValue());
							} else if(derivationNumber == PARAM_DERIVATION) {
								changeFunctionFlag();
								varType = VarType.getVarType(token.getLexValue());
							}

							//TODO ############# FunctionAST #############
							//Nao executa acoes semanticas
							if(derivationNumber == CASTING_DERIVATION ||
									derivationNumber == DECLARATION_DERIVATION) {
								changeSemanticActionFlag();
							}

							//TODO TO REMOVE
							if(derivationNumber == 28) {
								changeSemanticActionFlag();
							}


							//executa acao semantica para construir a functionAst
							if(derivationNumber >= 15 && derivationNumber <= 30) {
								if(semanticActionFlag) {
									derivationSemantincAction(derivationNumber);
								}
							}

							//TODO FunctionAST
							//Verifica se a flag de acao semantica esta desativada
							//e ativa quando desempilhar O NT <CMDREC>, pois nao deve
							//ser executada acao semantica depois das derivaceos 19 e 28
							if(derivationNumber == CMDREC_DERIVATION) {
								if(!semanticActionFlag) {
										changeSemanticActionFlag();
								}
							}

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

								//A partir daqui eh feito o empilhamento na pilha preditiva
								GrammarSymbol symb;
								Terminal term;
								NonTerminal nonTerm;

								for (int i = derivation.getSymbolsList().size() - 1; i >= 0; i--) {
									symb = derivation.getSymbolsList().get(i);
									if (symb.isTerminal()) {
										term = (Terminal) symb;
										stack.push(term);
									} else {
										nonTerm = (NonTerminal) symb;
										stack.push(nonTerm);
									}
								}

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

							} else {
								System.out.println(topNonTerminal.getName()
										+ "(" + leftCount + ")" + " = epsilon");
								stack.pop();
							}

							System.out.println();
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
			//Adiciona a functionAst da funcao "principal"
			this.functionAst.setSymbolTable(this.localSymbolTable);
			//Test to debug
			System.out.println();
		}
	}
}
