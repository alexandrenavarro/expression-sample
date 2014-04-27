package com.github.expressionsample;

/**
 * <p>DefaultDealToStringFunction.</p>
 *
 * @author anavarro - Apr 27, 2014
 *
 */
public final class DefaultDealToStringFunction implements DealToStringFunction {

    /**
     * {@inheritDoc}
     *
     * @see com.github.expressionsample.DealToStringFunction#applyAsString(com.github.expressionsample.Deal)
     */
    @Override
    public String applyAsString(final Deal deal) {
        return (deal.getQuantity() % 10 == 0) ? deal.getTrader() : "xx";
    }

}
