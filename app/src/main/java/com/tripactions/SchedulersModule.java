package com.tripactions;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Provides Android schedulers.
 */

@Module
public class SchedulersModule {

    public static final String IO = "io";
    public static final String COMPUTE = "compute";
    public static final String UI_THREAD = "ui";
    public static final String NEW_THREAD = "new_thread";

    @Provides
    @Named(IO)
    public Scheduler getIOScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named(COMPUTE)
    public Scheduler getComputeScheduler() {
        return Schedulers.computation();
    }

    @Provides
    @Named(UI_THREAD)
    public Scheduler getUIScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named(NEW_THREAD)
    public Scheduler getNewThreadScheduler() {
        return Schedulers.newThread();
    }
}
