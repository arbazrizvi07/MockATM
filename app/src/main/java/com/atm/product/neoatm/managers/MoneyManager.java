package com.atm.product.neoatm.managers;

/**
 * Created by neosoft on 24/8/17.
 * MonyManager is responsible for :
 * -Withdraw money
 * -Check balance
 */

public class MoneyManager {
    private static final MoneyManager ourInstance = new MoneyManager();
    private int userBalance;

    public static MoneyManager getInstance() {
        return ourInstance;
    }

    private MoneyManager() {
        userBalance = 20000;
    }

    public synchronized int getUserBalance() {
        return userBalance;
    }

    /**
     * Method to deduct money from main user balance
     *
     * @param amount
     * @return
     */
    public synchronized int withdrawMoney(int amount) {
        if (amount != 0) {
            userBalance = userBalance - amount;
            return userBalance;
        }
        return userBalance;
    }
}
