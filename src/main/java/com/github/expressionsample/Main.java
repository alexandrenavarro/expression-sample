package com.github.expressionsample;

import java.lang.reflect.InvocationTargetException;

import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.IExpressionEvaluator;

/**
 * <p>Main.</p>
 * 
 * @author anavarro - Feb 8, 2014
 * 
 */
public class Main {

    /**
     * Constructor.
     * 
     */
    public Main() {
    }

    /**
     * <p>main.</p>
     * 
     * @param args
     */
    public static void main(String[] args) {
        final IExpressionEvaluator expressionEvaluator = compileExpression();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            final Deal deal = new Deal("deal" + i, "trader" + i, i);
            final String evaluatedExpression = evaluateExpression(expressionEvaluator, deal);
            System.out.println("evaluatedExpression=" + evaluatedExpression);
        }
        long stop = System.currentTimeMillis();
        System.out.println("time=" + (stop - start));
    }

    /**
     * <p>compileExpression.</p>
     * 
     * @return
     */
    private static IExpressionEvaluator compileExpression() {
        try {

            final IExpressionEvaluator ee = CompilerFactoryFactory.getDefaultCompilerFactory().newExpressionEvaluator();
            ee.setExpressionType(String.class);
            ee.setParameters(new String[] { "deal" }, new Class[] { Deal.class });
            ee.cook("(deal.getQuantity() % 10 == 0) ? deal.getTrader() : \"xx\"");

            // final ExpressionEvaluator ee = new ExpressionEvaluator(
            // "(deal.getQuantity() % 10 == 0) ? deal.getTrader() : \"xx\"", // expression
            // String.class, // expressionType
            // new String[] { "deal"}, // parameterNames
            // new Class[] { Deal.class} // parameterTypes
            // );

            return ee;
        } catch (Exception e) {
            System.out.println("CompileError e" + e);
        }
        return null;
    }

    /**
     * <p>evaluateExpression.</p>
     * 
     * @param expressionEvaluator
     * @param deal
     * @return
     */
    private static String evaluateExpression(IExpressionEvaluator expressionEvaluator, Deal deal) {
        try {
            return (String) expressionEvaluator.evaluate(new Object[] { deal });
        } catch (InvocationTargetException e) {
            System.out.println("Evaluate Error " + e);
        }
        return null;
    }

}
