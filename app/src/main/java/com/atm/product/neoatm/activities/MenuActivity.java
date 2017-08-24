package com.atm.product.neoatm.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.atm.product.neoatm.R;
import com.atm.product.neoatm.base.BaseActivity;
import com.atm.product.neoatm.managers.MoneyManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity to show available menu's
 * Menu includes :
 * -Balance check
 * -Withdraw money
 *
 * @author Arbaz Rizvi
 * @created 24/8/2017
 */
public class MenuActivity extends BaseActivity {

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_menu;
    }

    @OnClick(R.id.btn_check_balance)
    public void onCheckBalance() {
        showBalanceDialog(String.valueOf(MoneyManager.getInstance().getUserBalance()));
    }

    @OnClick(R.id.btn_withdraw_money)
    public void onWithdrawMoney() {
        //Redirect to Operation Activity
        Intent menuIntent = new Intent(MenuActivity.this, OperationsActivity.class);
        startActivity(menuIntent);
        overridePendingTransitionEnter();
    }

    /**
     * Dialog alert to display user Balance
     *
     * @param amount
     */
    public void showBalanceDialog(String amount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.txt_balance) + amount);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.txt_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
