package com.github.expressionsample;

/**
 * <p>DealToStringFunctionCompiler.</p>
 *
 * @author anavarro - Apr 27, 2014
 *
 */
public interface DealToStringFunctionCompiler {

    /**
     * <p>compile.</p>
     *
     * @param expression
     * @return
     */
    DealToStringFunction compile(final String expression);
}
