package com.tripactions.base.presentation;

import androidx.annotation.Nullable;

import com.tripactions.TripActionsApplication;

import java.lang.ref.WeakReference;

/**
 * Base class used to show a piece of UI, generally an activity or fragment. Navigators
 * will generally be injected, so the activity is generally provided through the
 * OwlApplication.
 *
 * @param <Data> Any initial data needed to complete the navigation
 */

public abstract class TripActionsNavigator<Data> {
    Data data;

    public abstract void navigate(Data data);

    private WeakReference<TripActionsActivity> mActivity;

    public TripActionsNavigator(TripActionsApplication application) {
        mActivity = new WeakReference<>(application.getCurrentActivity());
    }

    @Nullable
    protected TripActionsActivity getActivity() {
        return mActivity.get();
    }
}
