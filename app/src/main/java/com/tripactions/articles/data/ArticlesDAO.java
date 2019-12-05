package com.tripactions.articles.data;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.tripactions.base.data.TripActionsDao;

import java.util.List;

import io.reactivex.Flowable;

/**
 *
 */

@Dao
public abstract class ArticlesDAO extends TripActionsDao<Article> {

    @Query("DELETE FROM " + Article.TABLE_NAME)
    public abstract void deleteAll();

    @Query("SELECT * FROM " + Article.TABLE_NAME + " WHERE _id = :id")
    public abstract Flowable<Article> getArticle(long id);

    @Query("SELECT * FROM " + Article.TABLE_NAME + " WHERE _id = :id")
    public abstract Article getArticleSynchronous(long id);

    @Query("SELECT * FROM " + Article.TABLE_NAME)
    public abstract Flowable<List<Article>> getArticles();

    @Query("SELECT * FROM " + Article.TABLE_NAME)
    public abstract List<Article> getArticlesSynchronous();

    @Query("SELECT max(apiIndex) FROM " + Article.TABLE_NAME)
    public abstract Long getMaxApiIndex();

    @Transaction
    public void clearAndInsertAll(List<Article> article) {
        deleteAll();
        insertAll(article);
    }
}
