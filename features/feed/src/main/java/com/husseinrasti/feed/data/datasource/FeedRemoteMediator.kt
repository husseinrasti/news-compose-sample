package com.husseinrasti.feed.data.datasource

import android.content.res.Resources
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.husseinrasti.core.exceptions.toFailure
import com.husseinrasti.core.network.errorResponse
import com.husseinrasti.core.utils.STARTING_PAGE_INDEX
import com.husseinrasti.domain.remotekeys.entity.RemoteKeysEntity
import com.husseinrasti.domain.remotekeys.usecase.DeleteRemoteKeysUseCase
import com.husseinrasti.domain.remotekeys.usecase.FetchKeysByTypeUseCase
import com.husseinrasti.domain.remotekeys.usecase.InsertRemoteKeysUseCase
import com.husseinrasti.core.R
import com.husseinrasti.feed.data.dao.FeedDao
import com.husseinrasti.feed.data.entity.FeedEntity
import com.husseinrasti.feed.data.remote.FeedApi
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
@OptIn(ExperimentalPagingApi::class)
class FeedRemoteMediator @Inject constructor(
    private val resources: Resources,
    private val dao: FeedDao,
    private val api: FeedApi,
    private val fetchKeysByTypeUseCase: FetchKeysByTypeUseCase,
    private val insertRemoteKeysUseCase: InsertRemoteKeysUseCase,
    private val deleteRemoteKeysUseCase: DeleteRemoteKeysUseCase
) : RemoteMediator<Int, FeedEntity.Item>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, FeedEntity.Item>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }
        try {
            val response = api.getNews(page = (page * state.config.pageSize))
            return if (response.isSuccessful) {
                val data = response.body()?.results!!
                val endOfPaginationReached = data.isEmpty()
                if (loadType == LoadType.REFRESH) deleteRemoteKeysUseCase(RemoteKeysEntity.Item(type = "News"))
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                insertRemoteKeysUseCase(RemoteKeysEntity.Item(type = "News", prevKey = prevKey, nextKey = nextKey))
                if (page == 0 || loadType == LoadType.REFRESH) dao.clear()
                dao.insert(data.map { it.toDomain() })
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } else {
                MediatorResult.Error(response.errorResponse(resources.getString(R.string.msg_error)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e.toFailure(resources.getString(R.string.msg_error)))
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, FeedEntity.Item>
    ): RemoteKeysEntity.Item? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { fetchKeysByTypeUseCase(FetchKeysByTypeUseCase.Params("News")) }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, FeedEntity.Item>
    ): RemoteKeysEntity.Item? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { fetchKeysByTypeUseCase(FetchKeysByTypeUseCase.Params("News")) }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, FeedEntity.Item>
    ): RemoteKeysEntity.Item? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.let {
                fetchKeysByTypeUseCase(FetchKeysByTypeUseCase.Params("News"))
            }
        }
    }

}