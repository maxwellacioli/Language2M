package br.com.ic.semantic;

import sun.reflect.generics.tree.Tree;

public class PredictiveAST implements Tree{

    private static PredictiveAST predictiveASTSingleton;

    public static PredictiveAST getInstance() {
        if (predictiveASTSingleton == null ) {
            predictiveASTSingleton = new PredictiveAST();
        }
        return predictiveASTSingleton;
    }
}
