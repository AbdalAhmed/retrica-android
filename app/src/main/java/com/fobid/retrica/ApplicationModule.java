package com.fobid.retrica;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.fobid.retrica.libs.Environment;
import com.fobid.retrica.services.ApiClient;
import com.fobid.retrica.services.ApiClientType;
import com.fobid.retrica.services.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by android01 on 2017. 8. 17..
 */

@Module
public final class ApplicationModule {

    private final Application application;

    public ApplicationModule(final @NonNull Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Environment provideEnvironment(final @NonNull ApiClientType apiClient) {
        Environment environment = new Environment();
        environment.apiClient = apiClient;
        return environment;
    }

    @Provides
    @Singleton
    @NonNull
    ApiClientType provideApiClientType(final @NonNull ApiService apiService) {
        return new ApiClient(apiService);
    }

    @Provides
    @Singleton
    @NonNull
    OkHttpClient provideOkHttpClient(final @NonNull HttpLoggingInterceptor httpLoggingInterceptor) {

        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // Only log in debug mode to avoid leaking sensitive information.
        if (BuildConfig.FLAVOR.equals("dev") || BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    @NonNull
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(Timber::d);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    @NonNull
    ApiService provideApiService(final @NonNull Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    @NonNull
    Retrofit provideApiRetrofit(final @NonNull OkHttpClient okHttpClient) {
        return createRetrofit(okHttpClient);
    }

    private
    @NonNull
    Retrofit createRetrofit(final @NonNull OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }
}
