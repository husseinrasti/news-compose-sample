package com.husseinrasti.domain.favorite.repository

import androidx.paging.PagingData
import com.husseinrasti.domain.favorite.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun insert(entity: FavoriteEntity.Item)

    suspend fun update(entity: FavoriteEntity.Item)

    suspend fun delete(entity: FavoriteEntity.Item)

    fun getFavorites(): Flow<PagingData<FavoriteEntity.Item>>

    suspend fun clear()

}