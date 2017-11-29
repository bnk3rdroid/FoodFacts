package foodfacts.bourgault.com.openfoodapi.di;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import foodfacts.bourgault.com.rx.utils.DefaultSchedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @ApiScope
    @Provides
    OkHttpClient provideHttpClient(HttpLoggingInterceptor logger, Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(logger);
        builder.cache(cache);
        return builder.build();
    }

    @ApiScope
    @Provides
    HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @ApiScope
    @Provides
    Cache provideCache(File file) {
        return new Cache(file, 10 * 10 * 1000);
    }

    @ApiScope
    @Provides
    File provideCacheFile(Context context) {
        return context.getFilesDir();
    }

    @ApiScope
    @Provides
    GsonConverterFactory provideGsonClient() {
        return GsonConverterFactory.create();
    }

    @ApiScope
    @Provides
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(DefaultSchedulers.INTERNET_SCHEDULERS);
    }
}
