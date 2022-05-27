package com.husseinrasti.domain.news.usecase

import androidx.paging.PagingSource
import com.husseinrasti.domain.news.entity.NewsEntity
import com.husseinrasti.domain.news.repository.NewsRepository
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class GetNewsPagingUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(params: Params): PagingSource<Int, NewsEntity.Item> {
        return repository.getNews()
    }

    @JvmInline
    value class Params(val nothing: String)
}


