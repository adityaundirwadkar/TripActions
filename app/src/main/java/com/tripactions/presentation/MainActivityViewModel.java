package com.tripactions.presentation;

import androidx.lifecycle.ViewModel;

import com.tripactions.articles.data.Article;
import com.tripactions.articles.domain.SearchArticlesUseCase;
import com.tripactions.presentation.screens.news.NewsNavigator;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 *
 */
public class MainActivityViewModel extends ViewModel {

    // Use cases
    private final SearchArticlesUseCase mSearchArticlesUseCase;

    // Navigators
    private final NewsNavigator mNewsNavigator;

    @Inject
    public MainActivityViewModel(SearchArticlesUseCase searchArticlesUseCase,
                                 NewsNavigator newsNavigator) {

        mSearchArticlesUseCase = searchArticlesUseCase;
        mNewsNavigator = newsNavigator;
    }

    public void navigateToNews() {
        mNewsNavigator.navigate(null);
    }

    public void searchNewsArticles(String keywords) {
        mSearchArticlesUseCase.clear();
        mSearchArticlesUseCase.execute(keywords, new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        mSearchArticlesUseCase.dispose();
    }
}
