package foodfacts.bourgault.com.foodfacts.screens.search.content.di;

import dagger.Component;
import foodfacts.bourgault.com.foodfacts.screens.search.content.core.FoodsContentFragment;
import foodfacts.bourgault.com.openfoodapi.di.ApiComponent;

@FoodsContentScope
@Component(
        dependencies = ApiComponent.class,
        modules = {
                FoodsContentModule.class
        }
)
public interface FoodsContentComponent {

    void inject(FoodsContentFragment foodsContentFragment);

}
