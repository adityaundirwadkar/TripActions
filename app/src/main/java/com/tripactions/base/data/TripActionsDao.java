package com.tripactions.base.data;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

/**
 * Base class for Room DAOs.
 */

interface BaseDaoInterface<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(T obj);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<T> items);

    @Update
    void update(T obj);

    @Delete
    void delete(T obj);
}

public abstract class TripActionsDao<T> implements BaseDaoInterface<T> {

}
