package com.tripactions.presentation.screen.article;

import com.tripactions.R;
import com.tripactions.TripActionsApplication;
import com.tripactions.articles.data.response.Doc;
import com.tripactions.base.presentation.TripActionsFragmentNavigator;
import com.tripactions.base.presentation.TripActionsNavigator;

import javax.inject.Inject;

/**
 *
 */
public class ArticleNavigator extends TripActionsNavigator<ArticleNavigator.Data> {

    private final TripActionsFragmentNavigator mTripActionsFragmentNavigator;

    @Inject
    public ArticleNavigator(TripActionsApplication application,
                            TripActionsFragmentNavigator tripActionsFragmentNavigator) {
        super(application);
        mTripActionsFragmentNavigator = tripActionsFragmentNavigator;
    }

    @Override
    public void navigate(Data aData) {
        ArticleFragment articleFragment = ArticleFragment.create(aData.doc);
        mTripActionsFragmentNavigator.execute(new TripActionsFragmentNavigator.Data(R.id.fragment_container, articleFragment, ArticleFragment.TAG),
                R.anim.slide_in_right,/* enter */
                R.anim.slide_out_right,/* exit */
                R.anim.slide_in_right,/* pop enter */
                R.anim.slide_out_right /* pop exit */);
    }

    public static class Data {
        Doc doc;

        public static Data create(Doc doc) {
            Data data = new Data();
            data.doc = doc;
            return data;
        }
    }
}
