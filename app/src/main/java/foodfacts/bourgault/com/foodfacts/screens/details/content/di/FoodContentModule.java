package foodfacts.bourgault.com.foodfacts.screens.details.content.di;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import foodfacts.bourgault.com.foodfacts.screens.details.content.adapters.TagsAdapter;
import foodfacts.bourgault.com.foodfacts.screens.details.content.core.FoodContentFragment;
import foodfacts.bourgault.com.foodfacts.screens.details.content.core.FoodContentModel;
import foodfacts.bourgault.com.foodfacts.screens.details.content.core.FoodContentPresenter;
import foodfacts.bourgault.com.rx.utils.RxSchedulers;

@Module
public class FoodContentModule {

    private FoodContentFragment mContext;

    public FoodContentModule(FoodContentFragment context) {
        mContext = context;
    }

    @FoodContentScope
    @Provides
    FoodContentPresenter providePresenter(FoodContentModel model) {
        return new FoodContentPresenter(mContext, model);
    }

    @FoodContentScope
    @Provides
    FoodContentModel provideModel(RxSchedulers schedulers) {
        return new FoodContentModel(schedulers);
    }

    @FoodContentScope
    @Provides
    FoodContentFragment provideContext() {
        return mContext;
    }

    @FoodContentScope
    @Provides
    TagsAdapter provideTagsAdapter() {
        return new TagsAdapter(new ArrayList<>());
    }
}
