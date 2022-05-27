package com.husseinrasti.domain.favorite.usecase

import com.husseinrasti.core.usecase.FlowUseCase
import com.husseinrasti.domain.news.entity.NewsEntity
import com.husseinrasti.domain.news.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class GetFavoritesUseCase @Inject constructor(
    private val repository: NewsRepository
) : FlowUseCase<GetFavoritesUseCase.Params, List<NewsEntity.Item>> {

    override suspend fun invoke(params: Params): Flow<List<NewsEntity.Item>> {
        return repository.getFavorites()
    }

    @JvmInline
    value class Params(val nothing: String)
}


