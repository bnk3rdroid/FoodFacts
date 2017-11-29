package foodfacts.bourgault.com.openfoodapi.di;

import dagger.Component;
import foodfacts.bourgault.com.openfoodapi.api.OpenFoodApi;
import foodfacts.bourgault.com.rx.di.RxComponent;
import foodfacts.bourgault.com.rx.utils.RxSchedulers;

@ApiScope
@Component(
        dependencies = RxComponent.class,
        modules = {
                NetworkModule.class,
                ApiModule.class
        }
)
public interface ApiComponent {

    RxSchedulers rxSchedulers();
    OpenFoodApi openFoodApi();

}
