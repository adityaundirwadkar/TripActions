package com.tripactions;

import com.alexfacciorusso.daggerviewmodel.DaggerViewModelInjectionModule;
import com.tripactions.image.ImageLoader;
import com.tripactions.presentation.MainActivity;
import com.tripactions.presentation.screen.article.ArticleComponent;
import com.tripactions.presentation.screen.article.ArticleModule;
import com.tripactions.presentation.screens.news.NewsComponent;
import com.tripactions.presentation.screens.news.NewsModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * App component that provides dependencies.
 */

@Singleton
@Component(modules = {
        TripActionsModule.class,
        SchedulersModule.class,
        SharedPreferencesModule.class,
        AndroidInjectionModule.class,
        DaggerViewModelInjectionModule.class,
        ViewModelModule.class,
        AndroidSupportInjectionModule.class,
})
public interface TripActionsComponent {
    // for inverse injection.
    // All the dependencies mentioned here will become injectable to DaggerApplication.
    void inject(TripActionsApplication daggerApplication);

    // Inject Activities or Fragments
    void inject(MainActivity mainActivity);

    NewsComponent provideNewsComponent(NewsModule newsModule);

    ArticleComponent provideArticleComponent(ArticleModule articleModule);

    ImageLoader provideImageLoader();
}
