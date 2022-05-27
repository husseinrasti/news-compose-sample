package com.husseinrasti.domain.news.usecase

import com.husseinrasti.core.usecase.CompletableUseCase
import com.husseinrasti.domain.news.repository.NewsRepository
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class ClearNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) : CompletableUseCase<ClearNewsUseCase.Params> {

    override suspend fun invoke(params: Params) {
        repository.clear()
    }

    @JvmInline
    value class Params(val nothing: String)

}


