package com.husseinrasti.feed.data.remote

import com.husseinrasti.core.BuildConfig
import com.husseinrasti.core.network.Urls
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Hussein Rasti on 5/26/22.
 */
interface FeedApi {

    @GET(Urls.NEWS)
    suspend fun getNews(
        @Query("apikey") apikey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en",
        @Query("category") category: String = "sports",
        @Query("country") country: String = "gb",
        @Query("page") page: Int
    ): Response<FeedResponse>

}