package com.tripactions.articles.data;

import com.tripactions.articles.data.response.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 */
public interface ArticlesService {

    @GET("articlesearch.json")
    Call<ArticleResponse> searchArticles(@Query("q") String query);
}
