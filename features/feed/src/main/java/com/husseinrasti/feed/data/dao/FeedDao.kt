package com.husseinrasti.feed.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.husseinrasti.feed.data.entity.FeedEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Hussein Rasti on 5/26/22.
 */

@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<FeedEntity.Item>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: FeedEntity.Item)

    @Delete
    suspend fun delete(entity: FeedEntity.Item)

    @Query("SELECT * FROM tbl_news")
    fun select(): PagingSource<Int, FeedEntity.Item>

    @Query("DELETE FROM tbl_news")
    suspend fun clear()

}