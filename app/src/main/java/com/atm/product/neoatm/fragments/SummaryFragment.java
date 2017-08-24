package com.atm.product.neoatm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.atm.product.neoatm.R;
import com.atm.product.neoatm.base.BaseFragment;
import com.atm.product.neoatm.managers.MoneyManager;
import com.atm.product.neoatm.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment show summary of transaction
 *
 * @author Arbaz Rizvi
 * @created 24/8/17
 */
public class SummaryFragment extends BaseFragment {

    @BindView(R.id.tv_balance)
    TextView mBalance;

    @BindView(R.id.tv_summary)
    TextView mSummary;

    private int withdrawAmount;

    public static SummaryFragment newInstance(int withdrawAmount) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.WITHDRAW_VALUE, withdrawAmount);
        SummaryFragment summaryFragment = new SummaryFragment();
        summaryFragment.setArguments(bundle);

        return summaryFragment;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        if (withdrawAmount != 0) {
            setSummary();
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_summary;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        withdrawAmount = getArguments().getInt(Constants.WITHDRAW_VALUE);
    }

    private void setSummary() {
        mBalance.append(String.valueOf(MoneyManager.getInstance().getUserBalance()));
        mSummary.setText(getNoteCount(withdrawAmount));
    }

    @OnClick(R.id.btn_another_transaction)
    public void onAnotherTransaction() {
        getActivity().finish();
    }


    /**
     * Method calculate note counts
     *
     * @param amount
     * @return Return note counts
     */
    private String getNoteCount(int amount) {
        int totalMinimumCurrencyCount = amount / Constants.MIN_CURRENCY;
        // Module will calculate the currency count witch cannot convert into 500 and 2000
        int hundredCount = totalMinimumCurrencyCount % Constants.FIVE_SEGMENT;
        //Calculate 500 Note count by subtracting 100 note count from total
        int fiveHundredCount = (totalMinimumCurrencyCount - hundredCount) / Constants.FIVE_SEGMENT;
        //Calculate if amount can convert into 2000 current and find its currency count
        int twoThousandCount = fiveHundredCount * Constants.CURRENCY_500 / Constants.CURRENCY_2000;
        //Condition to check if we found 2000 currency and remove 500 note count based on result
        if (twoThousandCount > 0) {
            fiveHundredCount = fiveHundredCount - twoThousandCount * 4;
        }
        //Generate final String
        String summary = "";
        if (twoThousandCount > 0) {
            summary += "2000 * " + twoThousandCount;
        }
        if (fiveHundredCount > 0) {
            summary += "\n\n 500 * " + fiveHundredCount;
        }
        if (hundredCount > 0) {
            summary += "\n\n 100 * " + hundredCount;
        }
        return summary;
    }
}
