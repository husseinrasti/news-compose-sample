package com.husseinrasti.favorite.ui

import com.husseinrasti.domain.news.entity.NewsEntity


/**
 * Created by Hussein Rasti on 5/27/22.
 */
sealed interface FavoriteUiState {
    object Loading : FavoriteUiState
    data class Error(val msg: String?) : FavoriteUiState
    data class ListFavorites(val data: List<NewsEntity.Item>) : FavoriteUiState
}