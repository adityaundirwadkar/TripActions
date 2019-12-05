package com.tripactions.presentation.screens.news;

import androidx.lifecycle.LifecycleOwner;

import com.tripactions.base.presentation.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 *
 */

@Module
public class NewsModule {

    NewsFragment mFragment;

    public NewsModule(NewsFragment newsFragment) {
        mFragment = newsFragment;
    }

    @Provides
    @PerFragment
    LifecycleOwner provideLifecycleOwner() {
        return mFragment;
    }
}
