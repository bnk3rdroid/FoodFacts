package foodfacts.bourgault.com.foodfacts.screens.search.di;

import dagger.Component;
import foodfacts.bourgault.com.foodfacts.screens.search.core.FoodsActivity;
import foodfacts.bourgault.com.openfoodapi.di.ApiComponent;

@FoodsScope
@Component(
        dependencies = ApiComponent.class,
        modules = {
                FoodsModule.class
        }
)
public interface FoodsComponent {

    void inject(FoodsActivity foodsActivity);

}
