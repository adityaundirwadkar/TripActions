package com.tripactions.articles.data;

import android.util.Log;

import com.tripactions.articles.data.response.ArticleResponse;
import com.tripactions.articles.data.response.Doc;
import com.tripactions.base.data.TripActionsRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/**
 *
 */

@Singleton
public class ArticlesRepository extends TripActionsRepository {

    private final static String TAG = ArticlesRepository.class.getSimpleName();

    private final ArticlesNetworkProvider mArticlesNetworkProvider;

    private final PublishSubject<List<Doc>> mArticlesSubject;
    private final PublishSubject<SEARCH_STATUS> mArticlesSearchStatusSubject;


    public enum SEARCH_STATUS {
        UNKNOWN,
        LOADING,
        LOADED,
        FAILED
    }

    @Inject
    public ArticlesRepository(ArticlesNetworkProvider articlesNetworkProvider) {
        mArticlesNetworkProvider = articlesNetworkProvider;

        mArticlesSubject = PublishSubject.create();
        mArticlesSearchStatusSubject = PublishSubject.create();
    }


    public Observable<List<Doc>> getArticlesSubject() {
        return mArticlesSubject;
    }

    public Observable<SEARCH_STATUS> getArticlesSearchStatusSubject() {
        return mArticlesSearchStatusSubject.startWith(SEARCH_STATUS.UNKNOWN);
    }

    public Observable<Boolean> searchArticles(String searchKey) {
        mArticlesSearchStatusSubject.onNext(SEARCH_STATUS.LOADING);
        mArticlesNetworkProvider.searchArticles(searchKey, new Callback<ArticleResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if (response.body() != null) {
                    mArticlesSearchStatusSubject.onNext(SEARCH_STATUS.LOADED);
                    mArticlesSubject.onNext(response.body().getResponse().getDocs());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.d(TAG, "searchArticles onFailure : " + t);
                mArticlesSearchStatusSubject.onNext(SEARCH_STATUS.FAILED);
            }
        });
        return Observable.just(Boolean.TRUE);
    }
}
