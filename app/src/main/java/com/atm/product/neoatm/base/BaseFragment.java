package com.atm.product.neoatm.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Base Fragment contains common methods use in child fragments
 *
 * @author Arbaz Rizvi
 * @created 24/08/2017
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getContentView(), container, false);

        initViews(view);
        initData();

        return view;
    }

    /**
     * Initialize fragments view
     *
     * @param view
     */
    protected abstract void initViews(View view);

    /**
     * Initialize Common Values and Data
     */
    protected void initData() {

    }

    /**
     * Returns layout id for fragment.
     *
     * @return
     */
    protected abstract int getContentView();
}
