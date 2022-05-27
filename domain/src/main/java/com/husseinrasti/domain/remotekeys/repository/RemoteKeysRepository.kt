package com.husseinrasti.domain.remotekeys.repository

import com.husseinrasti.domain.remotekeys.entity.RemoteKeysEntity


/**
 * Created by Hussein Rasti on 5/26/22.
 */
interface RemoteKeysRepository {

    suspend fun insert(remoteKey: RemoteKeysEntity.Item)

    suspend fun delete(remoteKey: RemoteKeysEntity.Item)

    suspend fun fetchKeysByType(type: String): RemoteKeysEntity.Item?

    suspend fun clear()

}