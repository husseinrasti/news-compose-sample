package com.husseinrasti.favorite.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.husseinrasti.common_ui.component.ErrorNetwork
import com.husseinrasti.common_ui.component.ProgressBar
import com.husseinrasti.core.R
import com.husseinrasti.domain.news.entity.NewsEntity
import com.husseinrasti.favorite.component.CardNews


/**
 * Created by Hussein Rasti on 5/27/22.
 */

@Composable
fun Favorite(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    Favorite(
        modifier = modifier,
        uiState = uiState,
        onClickItem = { uriHandler.openUri(it.link) },
        onClickFavorite = viewModel::onDeleteFavorite
    )
}


@Composable
private fun Favorite(
    modifier: Modifier,
    uiState: FavoriteUiState,
    onClickItem: (NewsEntity.Item) -> Unit,
    onClickFavorite: (NewsEntity.Item) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        when (uiState) {
            is FavoriteUiState.Error -> {
                ErrorNetwork(
                    message = uiState.msg,
                    imageVector = Icons.Default.List
                )
            }
            FavoriteUiState.Loading -> {
                ProgressBar()
            }
            is FavoriteUiState.ListFavorites -> {
                if (uiState.data.isEmpty()) {
                    ErrorNetwork(
                        message = stringResource(id = R.string.msg_empty),
                        imageVector = Icons.Default.ViewList
                    )
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(uiState.data) {
                            CardNews(
                                item = it,
                                onClickItem = onClickItem,
                                onClickFavorite = onClickFavorite
                            )
                        }
                    }
                }
            }
        }
    }
}