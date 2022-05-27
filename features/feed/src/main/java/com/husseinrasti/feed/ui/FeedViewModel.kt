package com.husseinrasti.feed.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.husseinrasti.domain.favorite.usecase.UpdateFavoritesUseCase
import com.husseinrasti.domain.news.entity.NewsEntity
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
    private val updateFavoritesUseCase: UpdateFavoritesUseCase
) : ViewModel() {

    val feeds: Flow<PagingData<NewsEntity.Item>> = feedRepository.getFeeds()

    fun onClickFavorite(item: NewsEntity.Item) {
        viewModelScope.launch {
            updateFavoritesUseCase.invoke(item.copy(isFavorite = !item.isFavorite))
        }
    }

}