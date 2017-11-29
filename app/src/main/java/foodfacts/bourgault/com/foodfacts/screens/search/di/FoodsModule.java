package foodfacts.bourgault.com.foodfacts.screens.search.di;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import foodfacts.bourgault.com.foodfacts.screens.search.core.FoodsActivity;
import foodfacts.bourgault.com.foodfacts.screens.search.core.FoodsModel;
import foodfacts.bourgault.com.foodfacts.screens.search.core.FoodsPresenter;
import foodfacts.bourgault.com.foodfacts.screens.search.history.HistoryAdapter;
import foodfacts.bourgault.com.openfoodapi.api.OpenFoodApi;
import foodfacts.bourgault.com.rx.utils.RxSchedulers;

@Module
public class FoodsModule {

    private final FoodsActivity mContext;

    public FoodsModule(FoodsActivity context) {
        mContext = context;
    }

    @FoodsScope
    @Provides
    FoodsPresenter providePresenter(FoodsModel model) {
        return new FoodsPresenter(mContext, model);
    }

    @FoodsScope
    @Provides
    FoodsModel provideModel(OpenFoodApi api, RxSchedulers schedulers) {
        return new FoodsModel(api, schedulers);
    }

    @FoodsScope
    @Provides
    FoodsActivity provideContext() {
        return mContext;
    }

    @FoodsScope
    @Provides
    HistoryAdapter provideHistoryAdapter() {
        return new HistoryAdapter(new ArrayList<>());
    }

}
