package com.husseinrasti.domain.news.repository

import androidx.paging.PagingSource
import com.husseinrasti.domain.news.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun insert(entity: List<NewsEntity.Item>)

    suspend fun update(entity: NewsEntity.Item)

    suspend fun delete(entity: NewsEntity.Item)
    suspend fun clear()

    fun getNews(): PagingSource<Int, NewsEntity.Item>

    suspend fun getFavorites(): Flow<List<NewsEntity.Item>>

}