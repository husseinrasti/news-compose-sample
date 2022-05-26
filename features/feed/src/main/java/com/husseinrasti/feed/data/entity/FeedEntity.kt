package com.husseinrasti.feed.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class FeedEntity {

    @Entity(tableName = "tbl_news")
    data class Item(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long? = null,
        @ColumnInfo(name = "creator")
        val creator: List<String>,
        @ColumnInfo(name = "desc")
        val description: String,
        @ColumnInfo(name = "image_url")
        val imageUrl: String,
        @ColumnInfo(name = "link")
        val link: String,
        @ColumnInfo(name = "date")
        val date: String,
        @ColumnInfo(name = "title")
        val title: String
    )

}