package com.husseinrasti.feed.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.husseinrasti.core.extensions.allowReads
import com.husseinrasti.core.utils.NETWORK_PAGE_SIZE
import com.husseinrasti.domain.news.entity.NewsEntity
import com.husseinrasti.domain.news.usecase.GetNewsPagingUseCase
import com.husseinrasti.feed.data.datasource.FeedRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class FeedRepositoryImpl @Inject constructor(
    private val feedRemoteMediator: FeedRemoteMediator,
    private val getNewsPagingUseCase: GetNewsPagingUseCase
) : FeedRepository {

    override fun getFeeds(): Flow<PagingData<NewsEntity.Item>> {
        return allowReads {
            @OptIn(ExperimentalPagingApi::class)
            Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                remoteMediator = feedRemoteMediator,
                pagingSourceFactory = { getNewsPagingUseCase(GetNewsPagingUseCase.Params("")) }
            ).flow
        }
    }

}