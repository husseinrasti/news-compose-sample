package com.husseinrasti.domain.remotekeys.usecase

import com.husseinrasti.core.usecase.BaseUseCase
import com.husseinrasti.domain.remotekeys.entity.RemoteKeysEntity
import com.husseinrasti.domain.remotekeys.repository.RemoteKeysRepository
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class FetchKeysByTypeUseCase @Inject constructor(
    private val repository: RemoteKeysRepository
) : BaseUseCase<FetchKeysByTypeUseCase.Params, RemoteKeysEntity.Item?> {

    override suspend fun invoke(params: Params): RemoteKeysEntity.Item? {
        return repository.fetchKeysByType(params.key)
    }

    @JvmInline
    value class Params(val key: String)
}


