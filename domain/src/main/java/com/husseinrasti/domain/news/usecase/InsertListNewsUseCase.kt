package com.husseinrasti.domain.news.usecase

import com.husseinrasti.core.usecase.CompletableUseCase
import com.husseinrasti.domain.news.entity.NewsEntity
import com.husseinrasti.domain.news.repository.NewsRepository
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class InsertListNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) : CompletableUseCase<List<NewsEntity.Item>> {

    override suspend fun invoke(params: List<NewsEntity.Item>) {
        repository.insert(params)
    }

}


