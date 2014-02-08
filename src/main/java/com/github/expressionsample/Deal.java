package com.github.expressionsample;

/**
 * <p>Deal.</p>
 * 
 * @author anavarro - Feb 8, 2014
 * 
 */
public class Deal {

    private String dealId;
    private String trader;
    private int    quantity;

    /**
     * Constructor.
     * 
     */
    public Deal() {
    }

    /**
     * Constructor.
     * 
     * @param aDealId
     * @param aTrader
     * @param aQuantity
     */
    public Deal(final String aDealId, final String aTrader, final int aQuantity) {
        super();
        this.dealId = aDealId;
        this.trader = aTrader;
        this.quantity = aQuantity;
    }

    /**
     * Returns the dealId.
     * 
     * @return The dealId to return.
     */
    public String getDealId() {
        return this.dealId;
    }

    /**
     * Sets the dealId.
     * 
     * @param aDealId The dealId to set.
     */
    public void setDealId(String aDealId) {
        this.dealId = aDealId;
    }

    /**
     * Returns the trader.
     * 
     * @return The trader to return.
     */
    public String getTrader() {
        return this.trader;
    }

    /**
     * Sets the trader.
     * 
     * @param aTrader The trader to set.
     */
    public void setTrader(String aTrader) {
        this.trader = aTrader;
    }

    /**
     * Returns the quantity.
     * 
     * @return The quantity to return.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Sets the quantity.
     * 
     * @param aQuantity The quantity to set.
     */
    public void setQuantity(int aQuantity) {
        this.quantity = aQuantity;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Deal [dealId=" + this.dealId + ", trader=" + this.trader + ", quantity=" + this.quantity + "]";
    }

}
