package com.tripactions.presentation.screens.news;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tripactions.articles.data.Article;
import com.tripactions.articles.data.ArticlesRepository;
import com.tripactions.articles.data.response.Doc;
import com.tripactions.articles.domain.GetArticlesSearchStatusUseCase;
import com.tripactions.articles.domain.GetArticlesUseCase;
import com.tripactions.presentation.screen.article.ArticleNavigator;
import com.tripactions.presentation.screens.news.article.ArticleViewModel;
import com.tripactions.presentation.screens.news.article.ArticleViewModelFactory;
import com.tripactions.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 *
 */
public class NewsViewModel extends ViewModel {

    private final GetArticlesSearchStatusUseCase mGetArticlesSearchStatusUseCase;
    private final GetArticlesUseCase mGetArticlesUseCase;

    private final ArticleViewModelFactory mArticleViewModelFactory;
    private final ArticleNavigator mArticleNavigator;

    private final MutableLiveData<List<ArticleViewModel>> mArticlesLiveData;
    private final MutableLiveData<ArticlesRepository.SEARCH_STATUS> mArticlesSearchStatusLiveData;

    private final List<ArticleViewModel> mArticleViewModels;

    @Inject
    public NewsViewModel(GetArticlesSearchStatusUseCase getArticlesSearchStatusUseCase,
                         GetArticlesUseCase getArticlesUseCase,
                         ArticleViewModelFactory articleViewModelFactory,
                         ArticleNavigator articleNavigator) {
        mGetArticlesSearchStatusUseCase = getArticlesSearchStatusUseCase;
        mGetArticlesUseCase = getArticlesUseCase;

        mArticleViewModelFactory = articleViewModelFactory;
        mArticleNavigator = articleNavigator;

        mArticlesLiveData = new MutableLiveData<>();
        mArticlesSearchStatusLiveData = new MutableLiveData<>();

        mArticleViewModels = new ArrayList<>();

        getArticlesSearchStatusUpdates();
        getArticlesUpdates();
    }


    private void getArticlesSearchStatusUpdates() {
        mGetArticlesSearchStatusUseCase.execute(null, new DisposableObserver<ArticlesRepository.SEARCH_STATUS>() {
            @Override
            public void onNext(ArticlesRepository.SEARCH_STATUS search_status) {
                if (search_status != null) {
                    mArticlesSearchStatusLiveData.setValue(search_status);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void getArticlesUpdates() {
        mGetArticlesUseCase.execute(null, new DisposableObserver<List<Doc>>() {
            @Override
            public void onNext(List<Doc> docs) {
                if (ListUtils.isEmpty(docs)) {
                    return;
                }
                mArticleViewModels.clear();
                for (Doc doc : docs) {
                    ArticleViewModel articleViewModel = mArticleViewModelFactory.create(doc);
                    mArticleViewModels.add(articleViewModel);
                }
                mArticlesLiveData.setValue(mArticleViewModels);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void navigateToArticle(Doc doc) {
        mArticleNavigator.navigate(ArticleNavigator.Data.create(doc));
    }


    public MutableLiveData<List<ArticleViewModel>> getArticlesLiveData() {
        return mArticlesLiveData;
    }

    public MutableLiveData<ArticlesRepository.SEARCH_STATUS> getArticlesSearchStatusLiveData() {
        return mArticlesSearchStatusLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        mGetArticlesSearchStatusUseCase.dispose();
        mGetArticlesUseCase.dispose();
    }
}
