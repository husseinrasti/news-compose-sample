package com.husseinrasti.data.favorite.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.husseinrasti.domain.favorite.entity.FavoriteEntity

/**
 * Created by Hussein Rasti on 5/26/22.
 */

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<FavoriteEntity.Item>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavoriteEntity.Item)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: FavoriteEntity.Item)

    @Delete
    suspend fun delete(entity: FavoriteEntity.Item)

    @Query("SELECT * FROM tbl_favorite")
    fun select(): PagingSource<Int, FavoriteEntity.Item>

    @Query("DELETE FROM tbl_favorite")
    suspend fun clear()

}