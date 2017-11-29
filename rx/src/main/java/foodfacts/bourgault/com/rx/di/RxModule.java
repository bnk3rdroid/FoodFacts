package foodfacts.bourgault.com.rx.di;

import dagger.Module;
import dagger.Provides;
import foodfacts.bourgault.com.rx.utils.DefaultSchedulers;
import foodfacts.bourgault.com.rx.utils.RxSchedulers;

/**
 * DI Rx.
 */
@Module
class RxModule {

    @RxScope
    @Provides
    RxSchedulers provideRxSchedulers() {
        return new DefaultSchedulers();
    }

}
