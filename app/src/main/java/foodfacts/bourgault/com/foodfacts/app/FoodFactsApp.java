package foodfacts.bourgault.com.foodfacts.app;

import android.app.Application;

import foodfacts.bourgault.com.foodfacts.db.AppDatabase;
import foodfacts.bourgault.com.openfoodapi.di.ApiComponent;
import foodfacts.bourgault.com.openfoodapi.di.ApiModule;
import foodfacts.bourgault.com.openfoodapi.di.DaggerApiComponent;
import foodfacts.bourgault.com.openfoodapi.di.NetworkModule;
import foodfacts.bourgault.com.rx.di.DaggerRxComponent;
import foodfacts.bourgault.com.rx.di.RxComponent;

public class FoodFactsApp extends Application {

    private static RxComponent mRxComponent;
    private static ApiComponent mApiComponent;
    private static AppDatabase mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mRxComponent = DaggerRxComponent.create();
        mApiComponent = DaggerApiComponent.builder()
                .rxComponent(mRxComponent)
                .apiModule(new ApiModule(this))
                .networkModule(new NetworkModule())
                .build();
        mDatabase = AppDatabase.getAppDatabase(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppDatabase.destroyInstance();
    }

    public static RxComponent getRxComponent() {
        return mRxComponent;
    }

    public static ApiComponent getApiComponent() {
        return mApiComponent;
    }

    public static AppDatabase getDatabase() {
        return mDatabase;
    }

}
