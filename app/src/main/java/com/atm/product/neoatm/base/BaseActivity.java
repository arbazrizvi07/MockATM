package com.atm.product.neoatm.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.atm.product.neoatm.R;
import com.atm.product.neoatm.utils.Constants;

/**
 * Base Abstract Class Work as super class for all Activities
 * <p>
 * Class Inherit all methods of activity and Add few common methods for child classes
 *
 * @author Arbaz Rizvi
 * @created 24/8/2017
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView();
        initData();
    }

    protected void initData() {
        //Initialize other values
    }

    protected abstract void initView();

    protected abstract int getContentView();

    /**
     * Method to perform the "Enter" animation
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    /**
     * Method to perform the "Exit" animation
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    /**
     * Fragment transaction.
     *
     * @param container        the container
     * @param transactionType  the transaction type
     * @param fragment         the fragment
     * @param isAddToBackStack the is add to back stack
     * @param tag              the tag
     */
    public void fragmentTransaction(int container, int transactionType,
                                    Fragment fragment, boolean isAddToBackStack, String tag) {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        switch (transactionType) {
            case (Constants.ADD_FRAGMENT):
                trans.add(container, fragment, tag);
                if (isAddToBackStack)
                    trans.addToBackStack(tag);
                break;
            case (Constants.REPLACE_FRAGMENT):
                trans.replace(container, fragment, tag);
                if (isAddToBackStack)
                    trans.addToBackStack(tag);
                break;
            case (Constants.REMOVE_FRAGMENT):
                trans.remove(fragment);
                getSupportFragmentManager().popBackStack();
                break;
        }
        trans.commit();
    }

}
