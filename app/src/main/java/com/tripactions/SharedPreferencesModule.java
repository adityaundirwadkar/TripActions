package com.tripactions;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides injectable shared preferences
 */

@Module
public class SharedPreferencesModule {

    public static final String TRIP_ACTIONS_SHARED_PREF = "trip_actions_shared_pref";

    @Provides
    @Singleton
    @Named(TRIP_ACTIONS_SHARED_PREF)
    public SharedPreferences getAppSharedPref(@Named(TripActionsModule.APPLICATION_CONTEXT) Context context) {
        return context.getSharedPreferences(TRIP_ACTIONS_SHARED_PREF, Context.MODE_PRIVATE);
    }
}
