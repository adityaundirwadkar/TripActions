package com.tripactions.base.presentation;

import android.content.Context;

import androidx.annotation.CallSuper;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

/**
 * Base class for Fragments in this project.
 */
public class TripActionsFragment<T extends ViewModel> extends Fragment {

    public static final String TAG = TripActionsFragment.class.getSimpleName();

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    protected T mViewModel;

    protected T getViewModel() {
        return mViewModel;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mViewModel = getViewModel();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @CallSuper
    public void onEnter() {

    }

    @CallSuper
    public void onLeave() {

    }

    public boolean shouldDestroyActivity() {
        return false;
    }
}
