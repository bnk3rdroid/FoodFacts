package foodfacts.bourgault.com.foodfacts.base;

import foodfacts.bourgault.com.rx.utils.RxSchedulers;

public abstract class BaseModel {

    protected final RxSchedulers mSchedulers;

    public BaseModel(RxSchedulers schedulers) {
        mSchedulers = schedulers;
    }

    public RxSchedulers getSchedulers() {
        return mSchedulers;
    }
}
