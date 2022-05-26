package com.husseinrasti.feed.data.remote

import com.google.gson.annotations.SerializedName
import com.husseinrasti.core.mapper.ResponseObject
import com.husseinrasti.feed.data.entity.FeedEntity

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
    ) : ResponseObject<FeedEntity.Item> {
        override fun toDomain(): FeedEntity.Item {
            return FeedEntity.Item(
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