package com.atm.product.neoatm.listeners;

/**
 * Interface to handle withdraw process
 *
 * @author Arbaz Rizvi
 * @created 24/8/17.
 */

public interface OnWithdrawListener {
    void onWithdrawSuccess(int amountWithdraw);
}
