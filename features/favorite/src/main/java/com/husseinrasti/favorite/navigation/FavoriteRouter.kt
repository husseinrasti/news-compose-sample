package com.husseinrasti.favorite.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.husseinrasti.core.navigation.Router
import com.husseinrasti.favorite.ui.Favorite

/**
 * Created by Hussein Rasti on 5/27/22.
 */
object FavoriteRouter : Router {
    override val route: String = "favorite_route"
    override val destination: String = "favorite_destination"
}

fun NavGraphBuilder.favoriteGraph() {
    composable(route = FavoriteRouter.route) {
        Favorite()
    }
}
