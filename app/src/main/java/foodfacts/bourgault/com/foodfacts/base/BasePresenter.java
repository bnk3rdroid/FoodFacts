package foodfacts.bourgault.com.foodfacts.base;

import foodfacts.bourgault.com.rx.utils.RxSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends BaseViewContract, M extends BaseModel> {

    protected final CompositeDisposable mDisposables;
    protected final V mView;
    protected final M mModel;

    public BasePresenter(V view, M model) {
        mView = view;
        mModel = model;
        mDisposables = new CompositeDisposable();
    }

    protected abstract void onCreate();

    protected void onDestroy() {
        mDisposables.clear();
    }

    public RxSchedulers getSchedulers() {
        return mModel.getSchedulers();
    }
}
