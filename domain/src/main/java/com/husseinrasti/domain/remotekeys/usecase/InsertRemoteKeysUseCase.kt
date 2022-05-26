package com.husseinrasti.domain.remotekeys.usecase

import com.husseinrasti.core.usecase.CompletableUseCase
import com.husseinrasti.domain.remotekeys.entity.RemoteKeysEntity
import com.husseinrasti.domain.remotekeys.repository.RemoteKeysRepository
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class InsertRemoteKeysUseCase @Inject constructor(
    private val repository: RemoteKeysRepository
) : CompletableUseCase<RemoteKeysEntity.Item> {

    override suspend fun invoke(params: RemoteKeysEntity.Item) {
        repository.insert(params)
    }

}


