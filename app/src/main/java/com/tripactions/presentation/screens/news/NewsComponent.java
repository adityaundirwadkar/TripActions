package com.tripactions.presentation.screens.news;

import com.tripactions.base.presentation.PerFragment;

import dagger.Subcomponent;

/**
 *
 */
@PerFragment
@Subcomponent(modules = {
        NewsModule.class
})
public interface NewsComponent {
    void inject(NewsFragment newsFragment);
}
