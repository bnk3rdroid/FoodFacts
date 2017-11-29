package foodfacts.bourgault.com.foodfacts.screens.details.di;

import dagger.Component;
import foodfacts.bourgault.com.foodfacts.screens.details.core.FoodActivity;
import foodfacts.bourgault.com.openfoodapi.di.ApiComponent;

@FoodScope
@Component(
        dependencies = ApiComponent.class,
        modules = {
                FoodModule.class
        }
)
public interface FoodComponent {

    void inject(FoodActivity foodActivity);

}
