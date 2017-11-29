package foodfacts.bourgault.com.foodfacts.screens.details.content.di;

import dagger.Component;
import foodfacts.bourgault.com.foodfacts.screens.details.content.core.FoodContentFragment;
import foodfacts.bourgault.com.rx.di.RxComponent;

@FoodContentScope
@Component(
        dependencies = RxComponent.class,
        modules = {
                FoodContentModule.class
        }
)
public interface FoodContentComponent {

    void inject(FoodContentFragment foodContentFragment);

}
