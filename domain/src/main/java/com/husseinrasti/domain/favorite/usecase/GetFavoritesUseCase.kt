package com.husseinrasti.domain.favorite.usecase

import androidx.paging.PagingData
import com.husseinrasti.core.usecase.FlowUseCase
import com.husseinrasti.domain.favorite.entity.FavoriteEntity
import com.husseinrasti.domain.favorite.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) : FlowUseCase<GetFavoritesUseCase.Params, PagingData<FavoriteEntity.Item>> {

    override suspend fun invoke(params: Params): Flow<PagingData<FavoriteEntity.Item>> {
        return repository.getFavorites()
    }

    @JvmInline
    value class Params(val nothing: String)
}


