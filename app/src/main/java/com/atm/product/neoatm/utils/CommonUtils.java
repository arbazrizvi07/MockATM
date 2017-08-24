package com.atm.product.neoatm.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Common Utility class to manage common methods
 *
 * @author Arbaz Rizvi
 * @created 24/08/2017
 */

public class CommonUtils {

    /**
     * Method to show short Toast message
     *
     * @param context
     * @param message
     */
    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to show long Toast message
     *
     * @param context
     * @param message
     */
    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
