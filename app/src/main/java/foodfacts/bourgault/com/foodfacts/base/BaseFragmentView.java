package foodfacts.bourgault.com.foodfacts.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragmentView<P extends BasePresenter>
        extends Fragment
        implements BaseViewContract<P> {

    protected P mPresenter;

    protected View mView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initInjection();
        mView = inflater.inflate(getLayout(), container, false);
        bindButterKnife(mView);
        mPresenter = getPresenter();
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
    }

    protected abstract void bindButterKnife(View view);
}
