package com.husseinrasti.feed.data.mapper

import com.husseinrasti.domain.favorite.entity.FavoriteEntity
import com.husseinrasti.feed.data.entity.FeedEntity

object FeedMapper {
    fun mapToFavorite(entity: FeedEntity.Item) = FavoriteEntity.Item(
        id = entity.id ?: 0,
        creator = entity.creator,
        description = entity.description,
        imageUrl = entity.imageUrl,
        link = entity.link,
        date = entity.date,
        title = entity.title,
        isFavorite = entity.isFavorite
    )
}