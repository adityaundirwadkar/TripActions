package com.tripactions.articles.data;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.tripactions.BuildConfig;
import com.tripactions.TripActionsModule;
import com.tripactions.articles.data.response.ArticleResponse;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */


public class ArticlesNetworkProvider {

    private final static String TAG = ArticlesNetworkProvider.class.getSimpleName();

    private static final String BASE_URL = "https://api.nytimes.com/svc/search/v2/";
    private static final String API_KEY = "OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj";
    public static final String IMAGE_BASE_URL = "https://www.nytimes.com/";


    private static final int CONNECT_TIMEOUT = 30 * 1000; /* milliseconds */

    private final Retrofit mRetrofit;
    private final ArticlesService mService;

    @Inject
    public ArticlesNetworkProvider(@Named(TripActionsModule.APPLICATION_CONTEXT) Context appContext) {

        // Customise Gson instance
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        mRetrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpClient(appContext))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mService = mRetrofit.create(ArticlesService.class);
    }

    private OkHttpClient getOkHttpClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        // Add network interceptor.
        if (BuildConfig.DEBUG) {
            ChuckInterceptor chuckInterceptor = new ChuckInterceptor(context);
            chuckInterceptor.showNotification(true);
            builder.addNetworkInterceptor(chuckInterceptor);
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        // Append api-key parameter to every query
        Interceptor apiKeyInterceptor = chain -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().addQueryParameter("api-key", API_KEY).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        };
        builder.addInterceptor(apiKeyInterceptor);

        // Timeouts for read and write.
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.writeTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        return builder.build();
    }

    public void searchArticles(String searchKey, Callback<ArticleResponse> callback) {
        Call<ArticleResponse> call = mService.searchArticles(searchKey);
        call.enqueue(callback);
    }
}
