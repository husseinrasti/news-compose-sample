package com.husseinrasti.data.remoteKeys.repository

import com.husseinrasti.data.remoteKeys.datasource.RemoteKeysDataSource
import com.husseinrasti.data.remoteKeys.entity.RemoteKeysMapper
import com.husseinrasti.domain.remotekeys.entity.RemoteKeysEntity
import com.husseinrasti.domain.remotekeys.repository.RemoteKeysRepository
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/26/22.
 */
class RemoteKeysRepositoryImpl @Inject constructor(
    private val remoteKeysDataSource: RemoteKeysDataSource
) : RemoteKeysRepository {

    override suspend fun insert(remoteKey: RemoteKeysEntity.Item) {
        remoteKeysDataSource.insert(RemoteKeysMapper.mapToResponse(remoteKey))
    }

    override suspend fun delete(remoteKey: RemoteKeysEntity.Item) {
        remoteKeysDataSource.delete(RemoteKeysMapper.mapToResponse(remoteKey))
    }

    override suspend fun fetchKeysByType(type: String): RemoteKeysEntity.Item? {
        return remoteKeysDataSource.fetchKeysByType(type)?.toDomain()
    }

    override suspend fun clear() {
        remoteKeysDataSource.clear()
    }

}