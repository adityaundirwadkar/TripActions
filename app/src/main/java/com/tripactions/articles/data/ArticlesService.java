package com.tripactions.articles.data;

import com.tripactions.articles.data.response.ArticleResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 *
 */
public interface ArticlesService {

    @GET("articlesearch.json?")
    Call<List<Article>> searchArticles(@QueryMap Map<String, String> params);

    @GET("articlesearch.json")
    Call<ArticleResponse> searchArticles(@Query("q") String query);
}
