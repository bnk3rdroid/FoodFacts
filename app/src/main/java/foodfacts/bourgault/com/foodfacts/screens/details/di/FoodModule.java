package foodfacts.bourgault.com.foodfacts.screens.details.di;

import dagger.Module;
import dagger.Provides;
import foodfacts.bourgault.com.foodfacts.screens.details.core.FoodActivity;
import foodfacts.bourgault.com.foodfacts.screens.details.core.FoodModel;
import foodfacts.bourgault.com.foodfacts.screens.details.core.FoodPresenter;
import foodfacts.bourgault.com.openfoodapi.api.OpenFoodApi;
import foodfacts.bourgault.com.rx.utils.RxSchedulers;

@Module
public class FoodModule {

    private FoodActivity mContext;

    public FoodModule(FoodActivity context) {
        mContext = context;
    }

    @FoodScope
    @Provides
    FoodPresenter providePresenter(FoodModel model) {
        return new FoodPresenter(mContext, model);
    }

    @FoodScope
    @Provides
    FoodModel provideModel(OpenFoodApi api, RxSchedulers schedulers) {
        return new FoodModel(api, schedulers);
    }

    @FoodScope
    @Provides
    FoodActivity provideContext() {
        return mContext;
    }
}
