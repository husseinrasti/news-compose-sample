package com.husseinrasti.data.news.repository

import androidx.paging.PagingSource
import com.husseinrasti.data.news.dao.NewsDao
import com.husseinrasti.domain.news.entity.NewsEntity
import com.husseinrasti.domain.news.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/27/22.
 */
class NewsRepositoryImpl @Inject constructor(
    private val dao: NewsDao
) : NewsRepository {

    override suspend fun insert(entity: List<NewsEntity.Item>) {
        dao.insert(entity)
    }

    override suspend fun update(entity: NewsEntity.Item) {
        dao.update(entity)
    }

    override suspend fun delete(entity: NewsEntity.Item) {
        dao.delete(entity)
    }

    override suspend fun getFavorites(): Flow<List<NewsEntity.Item>> {
        return dao.selectFavorite()
    }

    override fun getNews(): PagingSource<Int, NewsEntity.Item> {
        return dao.selectPaging()
    }

    override suspend fun clear() {
        dao.clear()
    }

}