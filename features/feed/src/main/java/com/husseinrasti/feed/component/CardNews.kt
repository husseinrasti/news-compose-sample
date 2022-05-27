package com.husseinrasti.feed.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.husseinrasti.feed.data.entity.FeedEntity


/**
 * Created by Hussein Rasti on 5/27/22.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardNews(
    item: FeedEntity.Item,
    onClickItem: (FeedEntity.Item) -> Unit,
    onClickFavorite: (FeedEntity.Item) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClickItem(item) },
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            item.imageUrl.takeIf { it.isNotEmpty() }?.let {
                AsyncImage(
                    model = it,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(96.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { onClickFavorite(item) }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            tint = if (item.isFavorite) MaterialTheme.colorScheme.onError
                            else MaterialTheme.colorScheme.outline,
                            contentDescription = null
                        )
                    }
                }
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    color = MaterialTheme.colorScheme.secondary
                )
                if (item.date.isNotEmpty()) {
                    Text(
                        text = "Create at ${item.date}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                item.creator.takeIf { it.isNotEmpty() }?.first()?.let {
                    Text(
                        text = "Author: $it",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}