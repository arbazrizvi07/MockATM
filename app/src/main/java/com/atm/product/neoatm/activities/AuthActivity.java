package com.atm.product.neoatm.activities;

import android.content.Intent;
import android.widget.EditText;

import com.atm.product.neoatm.R;
import com.atm.product.neoatm.base.BaseActivity;
import com.atm.product.neoatm.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity to handle user pin authentication
 *
 * @author Arbaz Rizvi
 * @created 24/08/2017
 */
public class AuthActivity extends BaseActivity {

    @BindView(R.id.et_pin)
    EditText mEtPin;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_auth;
    }

    @OnClick(R.id.btn_continue)
    public void onContinue() {
        if (validateUserInput()) {
            Intent menuIntent = new Intent(AuthActivity.this, MenuActivity.class);
            startActivity(menuIntent);
            overridePendingTransitionEnter();
            finish();
        }
    }

    /**
     * Method to validate user input values
     *
     * @return Returns true if pin value is 4 else return false
     */
    private boolean validateUserInput() {
        String txtPin = mEtPin.getText().toString().trim();
        if (txtPin.isEmpty() || txtPin.length() < 4) {
            CommonUtils.showShortToast(this, getString(R.string.msg_invalid_pin));
            return false;
        }
        return true;
    }
}
