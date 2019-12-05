package com.tripactions;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.facebook.stetho.Stetho;
import com.tripactions.base.presentation.TripActionsActivity;
import com.tripactions.image.ImageLoader;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 *
 */
public class TripActionsApplication extends Application implements HasAndroidInjector, LifecycleObserver {

    private static final String TAG = TripActionsApplication.class.getSimpleName();

    private static TripActionsApplication sApplication;
    private WeakReference<TripActionsActivity> mCurrentActivity;
    private TripActionsComponent mComponent;

    @Inject
    volatile DispatchingAndroidInjector<Object> mAndroidInjector;

    @Inject
    ImageLoader mImageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return mAndroidInjector;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mImageLoader.onLowMemory();
    }

    // Lifecycle related events to manager app state.
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppBackgrounded() {
        Log.d(TAG, "App in background");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onAppDestroyed() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onAppForegrounded() {
        Log.d(TAG, "App in foreground");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onActivityForegrounded() {
        Log.d(TAG, "Activity resumed.");
    }

    private void initApplication() {
        sApplication = this;
        // init App Component
        mComponent = DaggerTripActionsComponent
                .builder()
                .tripActionsModule(new TripActionsModule(this))
                .build();
        mComponent.inject(this);
        ComponentProvider.set(mComponent);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
        //init database.
        // TripActionsDatabase.initDatabase(this);
    }

    public static TripActionsApplication getApplication() {
        return sApplication;
    }

    public void setCurrentActivity(@NonNull TripActionsActivity activity) {
        mCurrentActivity = new WeakReference<>(activity);
    }

    @Nullable
    public TripActionsActivity getCurrentActivity() {
        return mCurrentActivity != null ? mCurrentActivity.get() : null;
    }
}
