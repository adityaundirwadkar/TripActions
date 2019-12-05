package com.tripactions.articles.domain;

import com.tripactions.SchedulersModule;
import com.tripactions.articles.data.ArticlesRepository;
import com.tripactions.articles.data.response.Doc;
import com.tripactions.base.data.UseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 *
 */
public class GetArticlesUseCase extends UseCase<List<Doc>, Void> {

    private final ArticlesRepository mArticlesRepository;
    @Inject
    public GetArticlesUseCase(@Named(SchedulersModule.IO) Scheduler subscribeOn,
                              @Named(SchedulersModule.UI_THREAD)Scheduler observeOn,
                              ArticlesRepository articlesRepository) {
        super(subscribeOn, observeOn);
        mArticlesRepository = articlesRepository;
    }

    @Override
    public Observable<List<Doc>> buildUseCaseObservable(Void data) {
        return Observable.defer(mArticlesRepository::getArticlesSubject);
    }
}
