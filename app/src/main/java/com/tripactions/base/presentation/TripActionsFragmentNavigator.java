package com.tripactions.base.presentation;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;

import com.tripactions.TripActionsApplication;

import javax.inject.Inject;

/**
 *
 */
public class TripActionsFragmentNavigator extends TripActionsNavigator<TripActionsFragmentNavigator.Data> {

    @Inject
    protected TripActionsFragmentNavigator(TripActionsApplication application) {
        super(application);
    }

    @Override
    public void navigate(Data data) {
        TripActionsActivity activity = getActivity();
        if (activity != null && !activity.getSupportFragmentManager().isStateSaved()) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(data.mContainer, data.mFragment)
                    .addToBackStack(data.mTag)
                    .commit();
        }
    }

    // Navigate to a fragment with in/out pop in/out transition animation.
    public void execute(Data data,
                        @AnimatorRes @AnimRes int enter,
                        @AnimatorRes @AnimRes int exit,
                        @AnimatorRes @AnimRes int popEnter,
                        @AnimatorRes @AnimRes int popExit) {
        TripActionsActivity activity = getActivity();
        if (activity != null && !activity.getSupportFragmentManager().isStateSaved()) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            enter,
                            exit,
                            popEnter,
                            popExit)
                    .replace(data.mContainer, data.mFragment)
                    .addToBackStack(data.mTag)
                    .commit();
        }
    }

    public static class Data {
        private int mContainer;
        private TripActionsFragment mFragment;
        private String mTag;

        public Data(int container, TripActionsFragment fragment, String tag) {
            mContainer = container;
            mFragment = fragment;
            mTag = tag;
        }
    }
}
