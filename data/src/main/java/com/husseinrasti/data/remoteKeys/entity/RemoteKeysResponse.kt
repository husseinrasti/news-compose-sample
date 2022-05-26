/*
 * Copyright (C) 2022  The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.husseinrasti.data.remoteKeys.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.husseinrasti.core.mapper.ResponseObject
import com.husseinrasti.domain.remotekeys.entity.RemoteKeysEntity


/**
 * Created by Hussein Rasti on 2/23/22.
 */
@Entity(tableName = "remote_keys")
data class RemoteKeysResponse(
    @PrimaryKey
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "prev_key")
    val prevKey: Int? = null,
    @ColumnInfo(name = "next_key")
    val nextKey: Int? = null
) : ResponseObject<RemoteKeysEntity.Item> {
    override fun toDomain(): RemoteKeysEntity.Item {
        return RemoteKeysEntity.Item(
            type = type,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }
}


object RemoteKeysMapper {
    fun mapToResponse(entity: RemoteKeysEntity.Item) = RemoteKeysResponse(
        type = entity.type,
        prevKey = entity.prevKey,
        nextKey = entity.nextKey
    )
}