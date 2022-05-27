package com.husseinrasti.feed.data.repository

import androidx.paging.PagingData
import com.husseinrasti.feed.data.entity.FeedEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Hussein Rasti on 5/26/22.
 */
interface FeedRepository {

    fun getFeeds(): Flow<PagingData<FeedEntity.Item>>

    suspend fun update(entity: FeedEntity.Item)

}