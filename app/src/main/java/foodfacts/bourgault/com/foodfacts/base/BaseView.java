package foodfacts.bourgault.com.foodfacts.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseView<P extends BasePresenter>
        extends AppCompatActivity
        implements BaseViewContract<P> {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjection();
        setContentView(getLayout());
        bindButterKnife();
        mPresenter = getPresenter();
        mPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    protected abstract void bindButterKnife();

}
