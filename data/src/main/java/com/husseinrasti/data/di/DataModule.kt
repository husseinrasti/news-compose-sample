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

package com.husseinrasti.data.di

import com.husseinrasti.data.favorite.dao.FavoriteDao
import com.husseinrasti.data.favorite.repository.FavoriteRepositoryImpl
import com.husseinrasti.data.remoteKeys.datasource.RemoteKeysDataSource
import com.husseinrasti.data.remoteKeys.repository.RemoteKeysRepositoryImpl
import com.husseinrasti.domain.favorite.repository.FavoriteRepository
import com.husseinrasti.domain.remotekeys.repository.RemoteKeysRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    @ViewModelScoped
    fun provideRemoteKeysRepository(dataSource: RemoteKeysDataSource): RemoteKeysRepository {
        return RemoteKeysRepositoryImpl(dataSource)
    }

    @Provides
    @ViewModelScoped
    fun provideFavoriteRepository(dao: FavoriteDao): FavoriteRepository {
        return FavoriteRepositoryImpl(dao)
    }

}