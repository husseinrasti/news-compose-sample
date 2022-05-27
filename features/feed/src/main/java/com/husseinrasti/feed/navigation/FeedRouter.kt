package com.husseinrasti.feed.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.husseinrasti.core.navigation.Router
import com.husseinrasti.feed.ui.Feed


/**
 * Created by Hussein Rasti on 5/27/22.
 */
object FeedRouter : Router {
    override val route: String = "feed_route"
    override val destination: String = "feed_destination"
}

fun NavGraphBuilder.feedGraph() {
    composable(route = FeedRouter.route) {
        Feed()
    }
}
