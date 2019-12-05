package com.tripactions.presentation.screens.news;

import com.tripactions.R;
import com.tripactions.TripActionsApplication;
import com.tripactions.base.presentation.TripActionsFragmentNavigator;
import com.tripactions.base.presentation.TripActionsNavigator;

import javax.inject.Inject;

/**
 * Navigate to news fragment.
 */
public class NewsNavigator extends TripActionsNavigator<Void> {

    private final TripActionsFragmentNavigator mTripActionsFragmentNavigator;

    @Inject
    public NewsNavigator(TripActionsApplication application,
                         TripActionsFragmentNavigator tripActionsFragmentNavigator) {
        super(application);
        mTripActionsFragmentNavigator = tripActionsFragmentNavigator;
    }

    @Override
    public void navigate(Void aVoid) {
        final TripActionsFragmentNavigator.Data data = new TripActionsFragmentNavigator.Data(
                R.id.fragment_container,
                NewsFragment.create(),
                NewsFragment.TAG);
        mTripActionsFragmentNavigator.navigate(data);
    }
}
