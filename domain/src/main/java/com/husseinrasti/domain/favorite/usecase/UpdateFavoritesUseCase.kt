package com.husseinrasti.domain.favorite.usecase

import com.husseinrasti.core.usecase.CompletableUseCase
import com.husseinrasti.domain.news.entity.NewsEntity
import com.husseinrasti.domain.news.repository.NewsRepository
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class UpdateFavoritesUseCase @Inject constructor(
    private val repository: NewsRepository
) : CompletableUseCase<NewsEntity.Item> {

    override suspend fun invoke(params: NewsEntity.Item) {
        repository.update(params)
    }

}


