package com.atm.product.neoatm.activities;

import com.atm.product.neoatm.R;
import com.atm.product.neoatm.base.BaseActivity;
import com.atm.product.neoatm.fragments.SummaryFragment;
import com.atm.product.neoatm.fragments.WithdrawFragment;
import com.atm.product.neoatm.listeners.OnWithdrawListener;
import com.atm.product.neoatm.utils.CommonUtils;
import com.atm.product.neoatm.utils.Constants;

import butterknife.ButterKnife;

/**
 * Activity to handle money transaction
 * -User can enter amount to be withdrawn
 * -User can check balance amount in account summary
 *
 * @author Arbaz Rizvi
 * @created 24/8/2017
 */
public class OperationsActivity extends BaseActivity implements OnWithdrawListener {

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        // Attach Default Fragment
        fragmentTransaction(R.id.fl_container, Constants.ADD_FRAGMENT,
                new WithdrawFragment(), false, null);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_operations;
    }

    @Override
    public void onWithdrawSuccess(int amountWithdraw) {
        CommonUtils.showLongToast(this, getString(R.string.txt_collect_cash));

        fragmentTransaction(R.id.fl_container, Constants.REPLACE_FRAGMENT,
                SummaryFragment.newInstance(amountWithdraw), false, null);
    }
}
