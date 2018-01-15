package com.gigigoandroidmx.people.common;

import com.gigigoandroidmx.kmvp.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Omar Bacilio - January 15, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class ExampleBaseActivity extends BaseActivity {

    private Unbinder unbinder;

    @Override
    protected void onBindView() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onUnbindView() {
        if (unbinder != null) unbinder.unbind();
    }
}
