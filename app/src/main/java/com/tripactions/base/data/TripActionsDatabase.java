package com.tripactions.base.data;

/**
 * Base class to access Room database.
 */

//@Database(entities = {
//        // TODO: Add entities here.
//}, version = 1, exportSchema = false)
//
//public abstract class TripActionsDatabase extends RoomDatabase {
//    private static TripActionsDatabase sDatabase;
//    private static final String DATABASE_TAG = "trip.actions.sqlite";
//    private static final int NUMBER_OF_THREADS = 4;
//    private static final ExecutorService sDatabaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//
//    public static TripActionsDatabase getDatabase() {
//        return sDatabase;
//    }
//
//    public static void initDatabase(final Context context) {
//        synchronized (TripActionsDatabase.class) {
//            if (sDatabase == null) {
//                sDatabase = Room
//                        .databaseBuilder(context.getApplicationContext(), TripActionsDatabase.class, DATABASE_TAG)
//                        .fallbackToDestructiveMigration()
//                        .allowMainThreadQueries()
//                        .build();
//            }
//        }
//    }
//
//    public static void runTaskInBackground(final Runnable runnable) {
//        sDatabaseWriteExecutor.execute(runnable);
//    }
//}