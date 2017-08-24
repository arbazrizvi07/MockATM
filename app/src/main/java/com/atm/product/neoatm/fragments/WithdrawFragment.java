package com.atm.product.neoatm.fragments;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.atm.product.neoatm.R;
import com.atm.product.neoatm.base.BaseFragment;
import com.atm.product.neoatm.listeners.OnWithdrawListener;
import com.atm.product.neoatm.managers.MoneyManager;
import com.atm.product.neoatm.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment to handle Withdraw operations
 *
 * @author Arbaz Rizvi
 * @created 24/8/17.
 */

public class WithdrawFragment extends BaseFragment {

    @BindView(R.id.et_amount)
    EditText mEtAmount;

    private String txtAmount;
    private Integer amount;
    private OnWithdrawListener mCallback;

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_withdraw;
    }

    @OnClick(R.id.btn_continue)
    public void onContinue() {
        if (validateUserInput()) {
            MoneyManager.getInstance().withdrawMoney(amount);
            mCallback.onWithdrawSuccess(amount);
        }
    }

    /**
     * Method to validate user input values
     *
     * @return
     */
    private boolean validateUserInput() {
        txtAmount = mEtAmount.getText().toString().trim();
        if (!txtAmount.isEmpty()) {
            amount = Integer.valueOf(txtAmount);
        } else {
            CommonUtils.showShortToast(getContext(), getString(R.string.msg_invalid_amount));
            return false;
        }
        if (!isValidAmount(amount)) {
            CommonUtils.showShortToast(getContext(), getString(R.string.msg_invalid_amount));
            return false;
        } else if (amount > MoneyManager.getInstance().getUserBalance()) {
            CommonUtils.showShortToast(getContext(), getString(R.string.msg_insufficient_bal));
            return false;
        }
        return true;
    }

    /**
     * Method check for valid amount value
     *
     * @param amount
     * @return
     */
    private boolean isValidAmount(int amount) {
        if (amount % 100 != 0) {
            return false;
        } else if (String.valueOf(amount).matches("[0]+")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (OnWithdrawListener) context;
    }
}
