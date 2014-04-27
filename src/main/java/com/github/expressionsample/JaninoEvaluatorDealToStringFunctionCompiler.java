package com.github.expressionsample;

import java.lang.reflect.InvocationTargetException;

import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.IExpressionEvaluator;

/**
 * <p>DealToStringFunctionCompiler.</p>
 *
 * @author anavarro - Apr 27, 2014
 *
 */
public final class JaninoEvaluatorDealToStringFunctionCompiler implements DealToStringFunctionCompiler {

    /**
     * Constructor.
     *
     */
    public JaninoEvaluatorDealToStringFunctionCompiler() {
    }
    
    /**
     * <p>compile.</p>
     *
     * @param expression
     * @return
     */
    public DealToStringFunction compile(String expression) {
        try {
            final IExpressionEvaluator ee = CompilerFactoryFactory.getDefaultCompilerFactory().newExpressionEvaluator();
            ee.setExpressionType(String.class);
            ee.setParameters(new String[] { "deal" }, new Class[] { Deal.class });
            ee.cook("(deal.getQuantity() % 10 == 0) ? deal.getTrader() : \"xx\"");

            return new DealToStringFunction() {
                
                /**
                 * {@inheritDoc}
                 *
                 * @see com.github.expressionsample.DealToStringFunction#applyAsString(com.github.expressionsample.Deal)
                 */
                @Override
                public String applyAsString(final Deal aDeal) {
                    try {
                        return (String) ee.evaluate(new Object[] { aDeal });
                    } catch (InvocationTargetException e) {
                        System.out.println("Evaluate Error " + e);
                    }
                    return null;
                }
            };
        } catch (Exception e) {
            System.out.println("CompileError e" + e);
        }
        return null;
        
    }

}
