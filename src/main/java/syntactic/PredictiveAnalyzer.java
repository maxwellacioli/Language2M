package syntactic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import analyzer.LLVMConfiguration;
import lexical.TokenCategory;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.LLVM.LLVMTypeRef;
import org.bytedeco.javacpp.LLVM.LLVMValueRef;

//Headers required by LLVM
import static org.bytedeco.javacpp.LLVM.*;

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

	//AST
	private List<AST> programAst;
	private AST ast;
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

		//TODO AST
		programAst = new ArrayList<AST>();
		astStack = new Stack<Node>();
		node = null;
		ast = null;
		//desativa a flag quando nao e' para executar acoes semanticas
		//no caso das derivacoes 19 e 28 da gramatica
		semanticActionFlag = true;

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

	private void changeSemanticActionFlag() {
		semanticActionFlag = !semanticActionFlag;
	}

	private Boolean isType(Token token) {
		String type = token.getLexValue();

		return (type.equals(VarType.INTEIRO.getName()) ||
				type.equals(VarType.REAL.getName()) ||
				type.equals(VarType.CARACTER.getName()) ||
				type.equals(VarType.CADEIA.getName()) ||
				type.equals(VarType.LOGICO.getName())
				);
	}

	private void changeNodeReference(Node node, Node child) {
		Node parent;
		parent = node.getParent();

		int index = parent.getChildren().indexOf(node);

		node.removeNode();
		parent.addChild(index, child);
	}

	private void derivationSemantincAction(int derivationNumber) {
		Cmd cmd = null;
		ListCmds listCmds = null;
		CmdsRec cmdsRec = null;
		Escope escope = null;
		Attribution attribution = null;
		Do doCmd = null;
		Id id = null;
		If ifCmd = null;
		IfElse ifElse = null;
		IfElseFat ifElseFat = null;
		Iterator iterator = null;
		Printout printout = null;
		ReadIn readIn = null;
		Return returnCmd = null;
		While whileCmd = null;
		Exp exp = null;
		Node parent = null;

		switch (derivationNumber) {
			case 15:
				listCmds = new ListCmds();
				node = astStack.pop();
				if(node.isRoot()) {
					ast.setRoot(listCmds);
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
					ast.setRoot(new ListCmds(cmd, cmdsRec));
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
					ast.setRoot(new ListCmds(cmd, cmdsRec));
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
				//TODO settar Id valor léxico
				id = new Id();
				exp = new Exp();
				node = astStack.pop();

				changeNodeReference(node, new Attribution(id, exp));

				astStack.push(exp);
				astStack.push(id);
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
				ifElseFat = new IfElseFat();
				node = astStack.pop();

				changeNodeReference(node, new IfElse(exp, escope, ifElseFat));

				astStack.push(ifElseFat);
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
			case 25:
				escope = new Escope();
				exp = new Exp();
				node = astStack.pop();

				changeNodeReference(node, new Do(escope, exp));

				astStack.push(exp);
				astStack.push(escope);
				break;
			case 26:
				attribution = new Attribution();
				exp = new Exp();
				Exp exp1 = new Exp();
				escope = new Escope();
				node = astStack.pop();

				changeNodeReference(node, new Iterator(attribution, exp, exp1, escope));

				astStack.push(escope);
				astStack.push(exp1);
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

				astStack.push(escope);
				break;
			case 29:
				node = astStack.pop();
				node.removeNode();
				break;
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
							}
						} else if(functionReturnFlag) {
							if(!token.getLexValue().equals(")")) {
								functionSymbol.setType(VarType.getVarType(token.getLexValue()));
								changeFunctionReturnFlag();
							}
						}

						//TODO AST
						if(!astStack.isEmpty()) {
							//Verifica se no topo da pilha e' um id
							if(astStack.peek() instanceof Id) {
//								astStack.pop();
								//TODO ATualiza token no topo da AST
								Id id = (Id) astStack.pop();
								if(token.getCategory().equals(TokenCategory.ID)) {
									id.setToken(token);
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
					//Cria a ast da funcao que esta sendo analizada
					if(topNonTerminal.getName() == NonTerminalName.FUNCTIONSREC) {
						//se o token atual for "principal" significa que nao temos uma assintura de funcao
						if(!token.getLexValue().equals("principal")) {
							//Verifica se a ast foi criada antes de adicionar a lista de asts
							if(ast != null) {
								programAst.add(ast);
							}

							functionSymbol = new FunctionSymbol(token.getLexValue(), null);
							globalTable.insertSymbol(functionSymbol);
							localSymbolTable = new SymbolTable(token.getLexValue());

							ast = new AST(token.getLexValue());
							node = new Escope();
							ast.setRoot(node);
							astStack.push(node);
						}
					}
 						else if (topNonTerminal.getName() == NonTerminalName.MAIN) {
						//Verifica se a ast foi criada antes de adicionar a lista de asts
						if(ast != null) {
							programAst.add(ast);
						}
						ast = new AST(token.getLexValue());
						node = new Escope();
						ast.setRoot(node);
						astStack.push(node);

						localSymbolTable = new SymbolTable(token.getLexValue());

						LLVMBuilderRef builder = LLVMConfiguration.getInstance().getGlobalBuilder();

						LLVMTypeRef mainType = LLVMFunctionType(LLVMInt32Type(), new PointerPointer((Pointer)null), 0, 0);
						LLVMValueRef mainFunc = LLVMAddFunction(LLVMConfiguration.getInstance().getGlobalMod(), token.getLexValue(), mainType);
						LLVMBasicBlockRef entry = LLVMAppendBasicBlock(mainFunc, "entry");
						LLVMPositionBuilderAtEnd(builder, entry);

						LLVMValueRef ret = LLVMConstInt(LLVMInt32Type(), 1, 1);
						LLVMBuildRet(builder, ret);
					}

					//TODO Adicionar no' a AST quando NT for EXPRESSION
					if (topNonTerminal.getName() == NonTerminalName.EXP) {
						if (!OperatorsGrammar.getInstance()
								.getOperatorsGrammarSymbols()
								.contains(terminal.getCategory())) {
							SyntaticAnalyzer.printError(token);
							System.exit(1);

						} else {
							//TODO AST
							Exp exp = precedenceAnalyzer.precedenceAnalysis(token);

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

							//TODO ############# AST #############
							//Nao executa acoes semanticas
							if(derivationNumber == CASTING_DERIVATION ||
									derivationNumber == DECLARATION_DERIVATION) {
								changeSemanticActionFlag();
							}

							//executa acao semantica para construir a ast
							if(derivationNumber >= 15 && derivationNumber <= 30) {
								if(semanticActionFlag) {
									derivationSemantincAction(derivationNumber);
								}
							}

							//TODO AST
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
			//Adiciona a ast da funcao "principal"
			programAst.add(ast);
			//Test to debug
			System.out.println();
		}
	}
}
