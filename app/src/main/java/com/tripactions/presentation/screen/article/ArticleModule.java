package com.tripactions.presentation.screen.article;

import androidx.lifecycle.LifecycleOwner;

import com.tripactions.base.presentation.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 *
 */

@Module
public class ArticleModule {
    ArticleFragment mFragment;

    public ArticleModule(ArticleFragment articleFragment) {
        mFragment = articleFragment;
    }

    @Provides
    @PerFragment
    LifecycleOwner provideLifecycleOwner() {
        return mFragment;
    }
}
