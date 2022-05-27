package com.husseinrasti.feed.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.husseinrasti.domain.favorite.usecase.DeleteFavoritesUseCase
import com.husseinrasti.domain.favorite.usecase.InsertFavoritesUseCase
import com.husseinrasti.feed.data.entity.FeedEntity
import com.husseinrasti.feed.data.mapper.FeedMapper
import com.husseinrasti.feed.data.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/27/22.
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
    private val insertFavoritesUseCase: InsertFavoritesUseCase,
    private val deleteFavoritesUseCase: DeleteFavoritesUseCase
) : ViewModel() {

    val feeds: Flow<PagingData<FeedEntity.Item>> = feedRepository.getFeeds()

    fun onClickFavorite(item: FeedEntity.Item) {
        viewModelScope.launch {
            if (item.isFavorite) {
                deleteFavoritesUseCase.invoke(FeedMapper.mapToFavorite(item.copy(isFavorite = false)))
                feedRepository.update(item.copy(isFavorite = false))
            } else {
                insertFavoritesUseCase.invoke(FeedMapper.mapToFavorite(item.copy(isFavorite = true)))
                feedRepository.update(item.copy(isFavorite = true))
            }
        }
    }

}