package com.tripactions;

import androidx.lifecycle.ViewModel;

import com.alexfacciorusso.daggerviewmodel.ViewModelKey;
import com.tripactions.presentation.MainActivityViewModel;
import com.tripactions.presentation.screen.article.ArticleViewModel;
import com.tripactions.presentation.screens.news.NewsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Module that provides injectable ViewModels.
 */

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    abstract ViewModel bindMainActivityViewModel(MainActivityViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel.class)
    abstract ViewModel bindNewsViewModel(NewsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel.class)
    abstract ViewModel bindArticleViewModel(ArticleViewModel viewModel);
}
