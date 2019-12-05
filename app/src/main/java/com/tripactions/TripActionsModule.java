package com.tripactions;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * App module that provides application context.
 */

@Module
public class TripActionsModule {
    public static final String APPLICATION_CONTEXT = "application_context";

    private final TripActionsApplication mApplication;

    public TripActionsModule(TripActionsApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public TripActionsApplication getTripActionsApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    @Named(APPLICATION_CONTEXT)
    public Context getApplicationContext() {
        return mApplication.getApplicationContext();
    }
}
