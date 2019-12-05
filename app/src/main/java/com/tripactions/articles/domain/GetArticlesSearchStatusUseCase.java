package com.tripactions.articles.domain;

import com.tripactions.SchedulersModule;
import com.tripactions.articles.data.ArticlesRepository;
import com.tripactions.base.data.UseCase;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 *
 */
public class GetArticlesSearchStatusUseCase extends UseCase<ArticlesRepository.SEARCH_STATUS, Void> {

    private final ArticlesRepository mArticlesRepository;

    @Inject
    public GetArticlesSearchStatusUseCase(@Named(SchedulersModule.IO) Scheduler subscribeOn,
                                          @Named(SchedulersModule.UI_THREAD) Scheduler observeOn,
                                          ArticlesRepository articlesRepository) {
        super(subscribeOn, observeOn);
        mArticlesRepository = articlesRepository;
    }

    @Override
    public Observable<ArticlesRepository.SEARCH_STATUS> buildUseCaseObservable(Void data) {
        return Observable.defer(mArticlesRepository::getArticlesSearchStatusSubject);
    }
}
