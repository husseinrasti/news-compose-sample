package com.husseinrasti.feed.ui

import androidx.paging.compose.LazyPagingItems
import com.husseinrasti.feed.data.entity.FeedEntity


/**
 * Created by Hussein Rasti on 5/27/22.
 */
sealed interface FeedUiState {
    object Loading : FeedUiState
    data class Error(val msg: String?) : FeedUiState
    data class ListNews(val data: LazyPagingItems<FeedEntity.Item>) : FeedUiState
}