package com.husseinrasti.feed.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.husseinrasti.core.extensions.allowReads
import com.husseinrasti.core.utils.NETWORK_PAGE_SIZE
import com.husseinrasti.feed.data.dao.FeedDao
import com.husseinrasti.feed.data.datasource.FeedRemoteMediator
import com.husseinrasti.feed.data.entity.FeedEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class FeedRepositoryImpl @Inject constructor(
    private val feedRemoteMediator: FeedRemoteMediator,
    private val dao: FeedDao
) : FeedRepository {

    override fun getFeeds(): Flow<PagingData<FeedEntity.Item>> {
        return allowReads {
            @OptIn(ExperimentalPagingApi::class)
            Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                remoteMediator = feedRemoteMediator,
                pagingSourceFactory = { dao.select() }
            ).flow
        }
    }

}