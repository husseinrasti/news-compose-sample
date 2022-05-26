package com.husseinrasti.domain.remotekeys.usecase

import com.husseinrasti.core.usecase.CompletableUseCase
import com.husseinrasti.domain.remotekeys.repository.RemoteKeysRepository
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class ClearRemoteKeysUseCase @Inject constructor(
    private val repository: RemoteKeysRepository
) : CompletableUseCase<ClearRemoteKeysUseCase.Params> {

    override suspend fun invoke(params: Params) {
        repository.clear()
    }

    @JvmInline
    value class Params(val nothing: String)
}


