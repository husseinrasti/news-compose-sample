package com.husseinrasti.domain.favorite.usecase

import com.husseinrasti.core.usecase.CompletableUseCase
import com.husseinrasti.domain.favorite.repository.FavoriteRepository
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class ClearFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) : CompletableUseCase<ClearFavoritesUseCase.Params> {

    override suspend fun invoke(params: Params) {
        repository.clear()
    }

    @JvmInline
    value class Params(val nothing: String)

}


