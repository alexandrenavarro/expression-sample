package com.github.expressionsample;

/**
 * <p>Demon.</p>
 *
 * @author anavarro - Apr 27, 2014
 *
 */
public class Demo {


    /**
     * <p>runWithCompiler.</p>
     *
     * @param compiler
     */
    public static void runWithCompiler(DealToStringFunctionCompiler compiler) {
        final DealToStringFunction dealToStringFunction = compiler.compile("(deal.getQuantity() % 10 == 0) ? deal.getTrader() : \"xx\"");
        
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            final Deal deal = new Deal("deal" + i, "trader" + i, i);
            final String evaluatedExpression = dealToStringFunction.applyAsString(deal);
            //System.out.println("evaluatedExpression=" + evaluatedExpression);
        }
        long stop = System.currentTimeMillis();
        System.out.println("time=" + (stop - start));
    }
    
    /**
     * <p>main.</p>
     *
     * @param args
     */
    public static void main(final String[] args) {
        
        
        runWithCompiler(new JavacDealToStringFunctionCompiler());
        //runWithCompiler(new JaninoEvaluatorDealToStringFunctionCompiler());
    }
    

}
