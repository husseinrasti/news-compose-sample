package com.husseinrasti.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husseinrasti.domain.news.entity.NewsEntity
import com.husseinrasti.domain.favorite.usecase.GetFavoritesUseCase
import com.husseinrasti.domain.favorite.usecase.UpdateFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/27/22.
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val updateFavoritesUseCase: UpdateFavoritesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getFavoritesUseCase(GetFavoritesUseCase.Params(""))
                .catch {
                    _uiState.value = FavoriteUiState.Error(it.localizedMessage)
                }.collect {
                    _uiState.value = FavoriteUiState.ListFavorites(data = it)
                }
        }
    }

    fun onDeleteFavorite(entity: NewsEntity.Item) {
        viewModelScope.launch {
            updateFavoritesUseCase.invoke(entity.copy(isFavorite = !entity.isFavorite))
        }
    }

}