package com.husseinrasti.domain.favorite.usecase

import com.husseinrasti.core.usecase.CompletableUseCase
import com.husseinrasti.domain.favorite.entity.FavoriteEntity
import com.husseinrasti.domain.favorite.repository.FavoriteRepository
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class UpdateFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) : CompletableUseCase<FavoriteEntity.Item> {

    override suspend fun invoke(params: FavoriteEntity.Item) {
        repository.update(params)
    }

}


