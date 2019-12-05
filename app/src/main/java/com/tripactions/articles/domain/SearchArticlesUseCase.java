package com.tripactions.articles.domain;

import com.tripactions.SchedulersModule;
import com.tripactions.articles.data.ArticlesRepository;
import com.tripactions.base.data.UseCase;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Search the articles based on the keyword
 */
public class SearchArticlesUseCase extends UseCase<Boolean, String> {

    private final ArticlesRepository mArticlesRepository;

    @Inject
    public SearchArticlesUseCase(@Named(SchedulersModule.IO) Scheduler subscribeOn,
                                 @Named(SchedulersModule.UI_THREAD) Scheduler observeOn,
                                 ArticlesRepository articlesRepository) {
        super(subscribeOn, observeOn);
        mArticlesRepository = articlesRepository;
    }

    @Override
    public Observable<Boolean> buildUseCaseObservable(String data) {
        return Observable.defer(() -> mArticlesRepository.searchArticles(data));
    }
}
