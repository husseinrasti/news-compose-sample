package com.husseinrasti.data.favorite.repository

import androidx.paging.*
import com.husseinrasti.core.extensions.allowReads
import com.husseinrasti.core.utils.NETWORK_PAGE_SIZE
import com.husseinrasti.data.favorite.dao.FavoriteDao
import com.husseinrasti.domain.favorite.entity.FavoriteEntity
import com.husseinrasti.domain.favorite.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/27/22.
 */
class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao
) : FavoriteRepository {

    override suspend fun insert(entity: FavoriteEntity.Item) {
        dao.insert(entity)
    }

    override suspend fun update(entity: FavoriteEntity.Item) {
        dao.update(entity)
    }

    override suspend fun delete(entity: FavoriteEntity.Item) {
        dao.delete(entity)
    }

    override fun getFavorites(): Flow<PagingData<FavoriteEntity.Item>> {
        return allowReads {
            @OptIn(ExperimentalPagingApi::class)
            Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { dao.select() }
            ).flow
        }
    }

    override suspend fun clear() {
        dao.clear()
    }

}