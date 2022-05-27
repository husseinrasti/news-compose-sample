package com.husseinrasti.feed.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.husseinrasti.common_ui.component.ErrorNetwork
import com.husseinrasti.common_ui.component.ProgressBar
import com.husseinrasti.feed.component.CardNews
import com.husseinrasti.feed.data.entity.FeedEntity


/**
 * Created by Hussein Rasti on 5/27/22.
 */

@Composable
fun Feed(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel()
) {
    val data: LazyPagingItems<FeedEntity.Item> = viewModel.feeds.collectAsLazyPagingItems()
    val uriHandler = LocalUriHandler.current

    Feed(
        modifier = modifier,
        data = data,
        onClickItem = { uriHandler.openUri(it.link) },
        onClickFavorite = viewModel::onClickFavorite
    )
}

@Composable
private fun Feed(
    modifier: Modifier,
    data: LazyPagingItems<FeedEntity.Item>,
    onClickItem: (FeedEntity.Item) -> Unit,
    onClickFavorite: (FeedEntity.Item) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        data.apply {
            when (loadState.refresh) {
                is LoadState.Loading -> {
                    ProgressBar()
                }
                is LoadState.Error -> {
                    val e = data.loadState.refresh as LoadState.Error
                    ErrorNetwork(e.error.localizedMessage)
                }
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Feed(
                data = data,
                onClickItem = onClickItem,
                onClickFavorite = onClickFavorite
            )
            data.apply {
                when (loadState.append) {
                    is LoadState.Loading -> {
                        item { ProgressBar() }
                    }
                    is LoadState.Error -> {
                        val e = data.loadState.append as LoadState.Error
                        item { ErrorNetwork(e.error.localizedMessage) }
                    }
                }
            }
        }
    }
}


private fun LazyListScope.Feed(
    data: LazyPagingItems<FeedEntity.Item>,
    onClickItem: (FeedEntity.Item) -> Unit,
    onClickFavorite: (FeedEntity.Item) -> Unit
) {
    items(data) {
        it?.let { item ->
            CardNews(
                item = item,
                onClickItem = onClickItem,
                onClickFavorite = onClickFavorite
            )
        }
    }
}