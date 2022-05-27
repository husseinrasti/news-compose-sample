package com.husseinrasti.feed.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.husseinrasti.feed.data.entity.FeedEntity
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
    private val feedRepository: FeedRepository
) : ViewModel() {

    val feeds: Flow<PagingData<FeedEntity.Item>> = feedRepository.getFeeds()

    fun onClickFavorite(item: FeedEntity.Item) {
        viewModelScope.launch {
            if (item.isFavorite) {

            } else {

            }
        }
    }

}