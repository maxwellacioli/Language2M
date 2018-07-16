package syntactic;

import java.util.ArrayList;
import java.util.Stack;

import semantic.AST;
import semantic.Node;
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

	private Stack<GrammarSymbol> stack;
	//TODO AST
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
		precedenceAnalyzer = new PrecedenceAnalyzer(lexicalAnalyzer);

		stack = new Stack<GrammarSymbol>();
		//TODO AST
		astStack = new Stack<Node>();
		ast = AST.getInstance();
		childrenList = new ArrayList<Node>();

		derivation = new Derivation();
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
			node = new Node(program);
			ast.setRoot(node);
			astStack.push(node);

			stack.push(program);

			prodCount.push(1);

			while (!stack.isEmpty()) {

				topGrammarSymbol = stack.peek();

				if (topGrammarSymbol.isTerminal()) {

					if (topGrammarSymbol.getValue() == terminal.getValue()) {
						stack.pop();

						//TODO AST
						node = astStack.pop();
						if(token.getLexValue().equals("x")) {
//							System.out.println("test!");
						}
						node.setTokenValue(terminal);

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

					//FIXME Adicionar no' a AST quando NT for EXPRESSION
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

							if(derivationNumber == 38) {
//								System.out.println("test");
							}
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
								node = astStack.pop();
								Node nodeAux;

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
									nodeAux = new Node(symb);
									childrenList.add(nodeAux);
									astStack.push(nodeAux);
								}
								//TODO AST
								node.addChildren(childrenList);
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
								node = astStack.pop();
								node.addChild(new Node());
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
			System.out.println("test");
		}
	}
}
