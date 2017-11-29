package foodfacts.bourgault.com.openfoodapi.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import foodfacts.bourgault.com.openfoodapi.BuildConfig;
import foodfacts.bourgault.com.openfoodapi.api.OpenFoodApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private final Context mContext;

    public ApiModule(Context mContext) {
        this.mContext = mContext;
    }

    @ApiScope
    @Provides
    Context provideApiContext() {
        return mContext;
    }

    @ApiScope
    @Provides
    OpenFoodApi provideApi(OkHttpClient client, GsonConverterFactory gson,
                               RxJava2CallAdapterFactory factory) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(gson)
                .addCallAdapterFactory(factory)
                .build().create(OpenFoodApi.class);
    }
}
