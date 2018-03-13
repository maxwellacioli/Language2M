package br.com.ic.syntactic.grammar;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.ic.lexical.TokenCategory;

public class Grammar {
	
	private static Grammar grammarSingleton;
	private ArrayList<Derivation> grammarMap;
	private Derivation derivationAux;
	
	public static final int EXPRESSION = 75;
	
	private Grammar() { 
		
		grammarMap = new ArrayList<Derivation>();
		loadGrammar();
		
	}

    public static Grammar getInstance() {
        if (grammarSingleton == null ) {
        	grammarSingleton = new Grammar();
        }
        return grammarSingleton;
    }
	

	public ArrayList<Derivation> getGrammarMap() {
		return grammarMap;
	}
	
	private void loadGrammar() {
		
		// (1)FUNCTIONS MAJORF
		derivationAux = new Derivation();
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.FUNCTIONS),
				new NonTerminal(NonTerminalName.MAJORF));	
		grammarAddDerivation(derivationAux);

		// (2)�id� PARAMS RETURNTYPE ESCOPE FUNCTIONS
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.ID),
				new NonTerminal(NonTerminalName.PARAMS),
				new NonTerminal(NonTerminalName.RETURNTYPE),
				new NonTerminal(NonTerminalName.ESCOPE),
				new NonTerminal(NonTerminalName.FUNCTIONS));
		grammarAddDerivation(derivationAux);

		// (3)Epsilon
		grammarAddDerivation(null);

		// (4)�major� �paramBegin� �paramEnd� �tEmpty� ESCOPE
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.MAJOR), 
				new Terminal(TokenCategory.PARAMBEGIN),
				new Terminal(TokenCategory.PARAMEND),
				new Terminal(TokenCategory.TEMPTY),
				new NonTerminal(NonTerminalName.ESCOPE));
		grammarAddDerivation(derivationAux);

		// (5)�paramBegin� PARAMSFAT
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PARAMBEGIN),
				new NonTerminal(NonTerminalName.PARAMSFAT));
		grammarAddDerivation(derivationAux);

		// (6)�paramEnd�
		derivationAux.addSymbol(new Terminal(TokenCategory.PARAMEND));		
		grammarAddDerivation(derivationAux);

		// (7)LISTPARAMS �paramEnd�
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.LISTPARAMS),
				new Terminal(TokenCategory.PARAMEND));
		grammarAddDerivation(derivationAux);

		// (8)TYPE NAME LISTPARAMSFAT
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.TYPE),
				new NonTerminal(NonTerminalName.NAME),
				new NonTerminal(NonTerminalName.LISTPARAMSFAT));
		grammarAddDerivation(derivationAux);
		
		// (9)�sep1� LISTPARAMS 
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.SEP1),
				new NonTerminal(NonTerminalName.LISTPARAMS));
		grammarAddDerivation(derivationAux);
		
		// (10)Epsilon
		grammarAddDerivation(null);
		
		// (11)�tInt� 
		derivationAux.addSymbol(new Terminal(TokenCategory.TINT));		
		grammarAddDerivation(derivationAux);
		
		// (12)�tLong� 
		derivationAux.addSymbol(new Terminal(TokenCategory.TLONG));		
		grammarAddDerivation(derivationAux);
		
		// (13)�tLogic� 
		derivationAux.addSymbol(new Terminal(TokenCategory.TLOGIC));		
		grammarAddDerivation(derivationAux);
		
		// (14)�tChar� 
		derivationAux.addSymbol(new Terminal(TokenCategory.TCHAR));		
		grammarAddDerivation(derivationAux);

		// (15)�tCchar� 
		derivationAux.addSymbol(new Terminal(TokenCategory.TCCHAR));		
		grammarAddDerivation(derivationAux);

		// (16)�tDec� 
		derivationAux.addSymbol(new Terminal(TokenCategory.TDEC));		
		grammarAddDerivation(derivationAux);

		// (17)�tEmpty� 
		derivationAux.addSymbol(new Terminal(TokenCategory.TEMPTY));		
		grammarAddDerivation(derivationAux);

		// (18)TYPE RETURNTYPEFAT
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.TYPE),
				new NonTerminal(NonTerminalName.RETURNTYPEFAT));
		grammarAddDerivation(derivationAux);

		// (19)�arrayBegin� �arrayEnd�
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.ARRAYBEGIN),
				new Terminal(TokenCategory.ARRAYEND));
		grammarAddDerivation(derivationAux);

		// (20)Epsilon
		grammarAddDerivation(null);
				
		// (21)�id� NAMEFAT 
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.ID),
				new NonTerminal(NonTerminalName.NAMEFAT));
		grammarAddDerivation(derivationAux);
		
		// (22)�arrayBegin� �constNumInt� �arrayEnd�
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.ARRAYBEGIN),
				new NonTerminal(NonTerminalName.EXPRESSION),
				new Terminal(TokenCategory.ARRAYEND));
		grammarAddDerivation(derivationAux);
		
		// (23)Epsilon
		grammarAddDerivation(null);
		
		// (24)�escBegin� COMMANDS �escEnd� �term�
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.ESCBEGIN),
				new NonTerminal(NonTerminalName.COMMANDS),
				new Terminal(TokenCategory.ESCEND),
				new Terminal(TokenCategory.TERM));
		grammarAddDerivation(derivationAux);
		
		// (25)CMD �term� COMMANDS 
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.CMD),
				new Terminal(TokenCategory.TERM),
				new NonTerminal(NonTerminalName.COMMANDS));
		grammarAddDerivation(derivationAux);
		
		// (26)Epsilon
		grammarAddDerivation(null);
		
		// (27)DECLARATION
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.DECLARATION));
		grammarAddDerivation(derivationAux);
		
		// (28)�id� CMDFAT 
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.ID),
				new NonTerminal(NonTerminalName.CMDFAT));
		grammarAddDerivation(derivationAux);
		
		// (29)PRINTOUT
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.PRINTOUT));
		grammarAddDerivation(derivationAux);
		
		// (30)READIN
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.READIN));
		grammarAddDerivation(derivationAux);
		
		// (31)IFELSE
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.IFELSE));
		grammarAddDerivation(derivationAux);
		
		// (32)WHILE
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.WHILE));
		grammarAddDerivation(derivationAux);
		
		// (33)DOWHILE
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.DOWHILE));
		grammarAddDerivation(derivationAux);
		
		// (34)ITERATOR
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.ITERATOR));
		grammarAddDerivation(derivationAux);

		// (35)RETURN
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.RETURN));
		grammarAddDerivation(derivationAux);
		
		// (36)TYPE NAME
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.TYPE),
				new NonTerminal(NonTerminalName.NAME));
		grammarAddDerivation(derivationAux);
		
		// (37)ATTRIBUTION
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.ATTRIBUTION));
		grammarAddDerivation(derivationAux);
		
		// (38)FUNCCALL
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.FUNCCALL));
		grammarAddDerivation(derivationAux);
		
		// (39)NAMEFAT �opAtrib� VALUE 
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.NAMEFAT),
				new Terminal(TokenCategory.OPATRIB),
				new NonTerminal(NonTerminalName.VALUE));
		grammarAddDerivation(derivationAux);
		
		// (40)ATTRIBUTION
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.ARRAY));
		grammarAddDerivation(derivationAux);
		
		// (41)�arrayBegin� ARRAYFAT
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.ARRAYBEGIN),
				new NonTerminal(NonTerminalName.ARRAYFAT));
		grammarAddDerivation(derivationAux);
		
		// (42)ELEMENTS �arrayEnd� 
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.ELEMENTS),
				new Terminal(TokenCategory.ARRAYEND));
		grammarAddDerivation(derivationAux);		
		
		// (43)�arrayEnd� 
		derivationAux.addSymbol(new Terminal(TokenCategory.ARRAYEND));		
		grammarAddDerivation(derivationAux);
		
		// (44)CONSTANT ELEMENTSFAT 
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.CONSTANT),
				new NonTerminal(NonTerminalName.ELEMENTSFAT));
		grammarAddDerivation(derivationAux);
		
		// (45)�sep1� ELEMENTS  
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.SEP1),
				new NonTerminal(NonTerminalName.ELEMENTS));
		grammarAddDerivation(derivationAux);
		
		// (46)Epsilon
		grammarAddDerivation(null);
		
		// (47)�constNumInt� 
		derivationAux.addSymbol(new Terminal(TokenCategory.CONSTNUMINT));		
		grammarAddDerivation(derivationAux);
		
		// (48)�constNumDec� 
		derivationAux.addSymbol(new Terminal(TokenCategory.CONSTNUMDEC));		
		grammarAddDerivation(derivationAux);
		
		// (49)�constLogic�
		derivationAux.addSymbol(new Terminal(TokenCategory.CONSTLOGIC));		
		grammarAddDerivation(derivationAux);
		
		// (50)�constChar�
		derivationAux.addSymbol(new Terminal(TokenCategory.CONSTCHAR));		
		grammarAddDerivation(derivationAux);
		
		// (51)�constCchar� 
		derivationAux.addSymbol(new Terminal(TokenCategory.CONSTCCHAR));		
		grammarAddDerivation(derivationAux);
		
		// (52)�paramBegin� LISTPARAMSCALL �paramEnd�
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PARAMBEGIN),
				new NonTerminal(NonTerminalName.LISTPARAMSCALL),
				new Terminal(TokenCategory.PARAMEND));
		grammarAddDerivation(derivationAux);
		
		// (53)PARAMITEM LISTPARAMSCALLFAT  
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.PARAMITEM),
				new NonTerminal(NonTerminalName.LISTPARAMSCALLFAT));
		grammarAddDerivation(derivationAux);
		
		// (54)�sep1� LISTPARAMSCALL
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.SEP1),
				new NonTerminal(NonTerminalName.LISTPARAMSCALL));
		grammarAddDerivation(derivationAux);
		
		// (55)Epsilon
		grammarAddDerivation(null);
		
		// (56)CONSTANT
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.CONSTANT));
		grammarAddDerivation(derivationAux);
		
		// (57)NAME
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.NAME));
		grammarAddDerivation(derivationAux);
		
		// (58)�prPrintout� �paramBegin� MESSAGE �paramEnd�
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRPRINTOUT),
				new Terminal(TokenCategory.PARAMBEGIN),
				new NonTerminal(NonTerminalName.MESSAGE),
				new Terminal(TokenCategory.PARAMEND));
		grammarAddDerivation(derivationAux);
		
		// (59)�constCchar� MESSAGEFAT
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.CONSTCCHAR),
				new NonTerminal(NonTerminalName.MESSAGEFAT));
		grammarAddDerivation(derivationAux);
		
		// (60)NAME MESSAGEFAT
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.NAME),
				new NonTerminal(NonTerminalName.MESSAGEFAT));
		grammarAddDerivation(derivationAux);

		// (61)�opConc� MESSAGE
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.OPCONC),
				new NonTerminal(NonTerminalName.MESSAGE));
		grammarAddDerivation(derivationAux);
		
		// (62)Epsilon
		grammarAddDerivation(null);
		
		// (63)�prReadin� �paramBegin� TYPE �sep1� NAME �paramEnd�
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRREADIN),
				new Terminal(TokenCategory.PARAMBEGIN),
				new NonTerminal(NonTerminalName.TYPE),
				new Terminal(TokenCategory.SEP1),
				new NonTerminal(NonTerminalName.NAME),
				new Terminal(TokenCategory.PARAMEND));
		grammarAddDerivation(derivationAux);
		
		// (64)IF ELSEIF ELSE
		derivationAux.addDerivationSymbols(
				new NonTerminal(NonTerminalName.IF),
				new NonTerminal(NonTerminalName.ELSEIF),
				new NonTerminal(NonTerminalName.ELSE));
		grammarAddDerivation(derivationAux);
		
		// (65)�prIf� �paramBegin� EXPRESSION �paramEnd� �escBegin� COMMANDS �escEnd�
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRIF),
				new Terminal(TokenCategory.PARAMBEGIN),
				new NonTerminal(NonTerminalName.EXPRESSION),
				new Terminal(TokenCategory.PARAMEND),
				new Terminal(TokenCategory.ESCBEGIN),
				new NonTerminal(NonTerminalName.COMMANDS),
				new Terminal(TokenCategory.ESCEND));
		grammarAddDerivation(derivationAux);
		
		//(66)�prElseIf� paramBegin� EXPRESSION �paramEnd� �escBegin� COMMANDS �escEnd� ELSEIF
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRELSEIF), 
				new Terminal(TokenCategory.PARAMBEGIN), 
				new NonTerminal(NonTerminalName.EXPRESSION), 
				new Terminal(TokenCategory.PARAMEND), 
				new Terminal(TokenCategory.ESCBEGIN), 
				new NonTerminal(NonTerminalName.COMMANDS), 
				new Terminal(TokenCategory.ESCEND), 
				new NonTerminal(NonTerminalName.ELSEIF));
		grammarAddDerivation(derivationAux);
		
		// (67) Epsilon
		grammarAddDerivation(null);
		
		// (68)�prElse�  �escBegin� COMMANDS �escEnd�
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRELSE), 
				new Terminal(TokenCategory.ESCBEGIN), 
				new NonTerminal(NonTerminalName.COMMANDS), 
				new Terminal(TokenCategory.ESCEND));
		grammarAddDerivation(derivationAux);
		
		// (69) Epsilon
		grammarAddDerivation(null);
		
		//  (70)�prWhile� �paramBegin� EXPRESSION �paramEnd� �escBegin� COMMANDS �escEnd�
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRWHILE), 
				new Terminal(TokenCategory.PARAMBEGIN), 
				new NonTerminal(NonTerminalName.EXPRESSION), 
				new Terminal(TokenCategory.PARAMEND), 
				new Terminal(TokenCategory.ESCBEGIN), 
				new NonTerminal(NonTerminalName.COMMANDS), 
				new Terminal(TokenCategory.ESCEND));
		grammarAddDerivation(derivationAux);
		
		// (71)�prDo� �escBegin� COMMANDS �escEnd� �prWhile� �paramBegin� EXPRESSION �paramEnd�
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRDO), 
				new Terminal(TokenCategory.ESCBEGIN), 
				new NonTerminal(NonTerminalName.COMMANDS), 
				new Terminal(TokenCategory.ESCEND), 
				new Terminal(TokenCategory.PRWHILE), 
				new Terminal(TokenCategory.PARAMBEGIN), 
				new NonTerminal(NonTerminalName.EXPRESSION), 
				new Terminal(TokenCategory.PARAMEND));
		grammarAddDerivation(derivationAux);
		
		//  (72)�prIterator� �paramBegin� 'id' ATTRIBUTION �sep2� EXPRESSION �sep2� 'id' ATTRIBUTION 
	    // �paramEnd� �escBegin� COMMANDS �escEnd�
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRITERATOR), 
				new Terminal(TokenCategory.PARAMBEGIN),
				new Terminal(TokenCategory.ID),
				new NonTerminal(NonTerminalName.ATTRIBUTION), 
				new Terminal(TokenCategory.SEP2), 
				new NonTerminal(NonTerminalName.EXPRESSION), 
				new Terminal(TokenCategory.SEP2),
				new Terminal(TokenCategory.ID),
				new NonTerminal(NonTerminalName.ATTRIBUTION), 
				new Terminal(TokenCategory.PARAMEND), 
				new Terminal(TokenCategory.ESCBEGIN), 
				new NonTerminal(NonTerminalName.COMMANDS), 
				new Terminal(TokenCategory.ESCEND));
		grammarAddDerivation(derivationAux);
		
		//  (73)�prReturn� RETURNFAT
		derivationAux.addDerivationSymbols(
				new Terminal(TokenCategory.PRRETURN), 
				new NonTerminal(NonTerminalName.RETURNFAT));
		grammarAddDerivation(derivationAux);
		
		// (74)CONSTANT
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.CONSTANT));
		grammarAddDerivation(derivationAux);
		
		// (75)NAME
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.NAME));
		grammarAddDerivation(derivationAux);
		
		// (76)EXPRESSION
		derivationAux.addSymbol(new NonTerminal(NonTerminalName.EXPRESSION));
		// derivationAux.addSymbol(new NonTerminal(NonTerminalName.NAMEFAT));
		grammarAddDerivation(derivationAux);
	}

	private void grammarAddDerivation(Derivation derivation) {		
		grammarMap.add(derivation);		
		if(derivation != null) {			
			derivationAux = new Derivation();
		}
	}	
	
}
