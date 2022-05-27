package com.husseinrasti.feed.data.remote

import com.google.gson.annotations.SerializedName
import com.husseinrasti.core.mapper.ResponseObject
import com.husseinrasti.domain.news.entity.NewsEntity

data class FeedResponse(
    @SerializedName("results")
    val results: List<Item>
) {
    data class Item(
        @SerializedName("creator")
        val creator: List<String>?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("image_url")
        val imageUrl: String?,
        @SerializedName("link")
        val link: String?,
        @SerializedName("pubDate")
        val date: String?,
        @SerializedName("title")
        val title: String?
    ) : ResponseObject<NewsEntity.Item> {
        override fun toDomain(): NewsEntity.Item {
            return NewsEntity.Item(
                creator = creator ?: listOf(),
                description = description ?: "",
                imageUrl = imageUrl ?: "",
                link = link ?: "",
                date = date ?: "",
                title = title ?: ""
            )
        }
    }
}