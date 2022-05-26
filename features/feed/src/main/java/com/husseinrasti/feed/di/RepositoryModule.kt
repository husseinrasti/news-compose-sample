package com.husseinrasti.feed.di

import com.husseinrasti.feed.data.dao.FeedDao
import com.husseinrasti.feed.data.datasource.FeedRemoteMediator
import com.husseinrasti.feed.data.repository.FeedRepository
import com.husseinrasti.feed.data.repository.FeedRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


/**
 * Created by Hussein Rasti on 5/27/22.
 */

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun feedRepository(
        feedRemoteMediator: FeedRemoteMediator,
        dao: FeedDao
    ): FeedRepository = FeedRepositoryImpl(
        feedRemoteMediator = feedRemoteMediator,
        dao = dao
    )

}