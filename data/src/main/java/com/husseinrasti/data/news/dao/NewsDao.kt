package com.husseinrasti.data.news.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.husseinrasti.domain.news.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Hussein Rasti on 5/26/22.
 */

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<NewsEntity.Item>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: NewsEntity.Item)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: NewsEntity.Item)

    @Delete
    suspend fun delete(entity: NewsEntity.Item)

    @Query("SELECT * FROM tbl_news WHERE isFavorite=1")
    fun selectFavorite(): Flow<List<NewsEntity.Item>>

    @Query("SELECT * FROM tbl_news")
    fun selectPaging(): PagingSource<Int, NewsEntity.Item>

    @Query("DELETE FROM tbl_news")
    suspend fun clear()

}