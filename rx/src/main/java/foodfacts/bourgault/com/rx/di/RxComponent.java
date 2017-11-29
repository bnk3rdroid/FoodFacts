package foodfacts.bourgault.com.rx.di;


import dagger.Component;
import foodfacts.bourgault.com.rx.utils.RxSchedulers;

@RxScope
@Component(modules = RxModule.class)
public interface RxComponent {
    RxSchedulers rxSchedulers();
}
