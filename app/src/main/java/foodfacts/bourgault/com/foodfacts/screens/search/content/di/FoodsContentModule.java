package foodfacts.bourgault.com.foodfacts.screens.search.content.di;

import dagger.Module;
import dagger.Provides;
import foodfacts.bourgault.com.foodfacts.screens.search.content.core.FoodsContentFragment;
import foodfacts.bourgault.com.foodfacts.screens.search.content.core.FoodsContentModel;
import foodfacts.bourgault.com.foodfacts.screens.search.content.core.FoodsContentPresenter;
import foodfacts.bourgault.com.rx.utils.RxSchedulers;

@Module
public class FoodsContentModule {

    private final FoodsContentFragment mContext;

    public FoodsContentModule(FoodsContentFragment context) {
        mContext = context;
    }

    @FoodsContentScope
    @Provides
    public FoodsContentPresenter providePresenter(FoodsContentModel model) {
        return new FoodsContentPresenter(mContext, model);
    }

    @FoodsContentScope
    @Provides
    public FoodsContentModel provideModel(RxSchedulers schedulers) {
        return new FoodsContentModel(schedulers);
    }

}
