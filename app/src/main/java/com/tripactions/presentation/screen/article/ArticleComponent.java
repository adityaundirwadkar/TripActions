package com.tripactions.presentation.screen.article;

import com.tripactions.base.presentation.PerFragment;

import dagger.Subcomponent;

/**
 *
 */

@PerFragment
@Subcomponent(modules = {
        ArticleModule.class
})
public interface ArticleComponent {
    void inject(ArticleFragment fragment);
}
