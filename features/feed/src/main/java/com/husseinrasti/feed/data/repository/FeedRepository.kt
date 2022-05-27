package com.husseinrasti.feed.data.repository

import androidx.paging.PagingData
import com.husseinrasti.domain.news.entity.NewsEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Hussein Rasti on 5/26/22.
 */
interface FeedRepository {

    fun getFeeds(): Flow<PagingData<NewsEntity.Item>>

}