package com.husseinrasti.rss.startup.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.husseinrasti.common_ui.ClearRippleTheme
import com.husseinrasti.common_ui.theme.AppTheme
import com.husseinrasti.rss.startup.navigation.RssNavHost
import com.husseinrasti.rss.startup.navigation.RssTopLevelNavigation
import com.husseinrasti.rss.startup.navigation.TOP_LEVEL_DESTINATIONS
import com.husseinrasti.rss.startup.navigation.TopLevelDestination


/**
 * Created by Hussein Rasti on 5/27/22.
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RssApp() {
    AppTheme {
        val navController = rememberNavController()
        val rssTopLevelNavigation = remember(navController) {
            RssTopLevelNavigation(navController)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Scaffold(
            modifier = Modifier,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            bottomBar = {
                RssBottomBar(
                    onNavigateToTopLevelDestination = rssTopLevelNavigation::navigateTo,
                    currentDestination = currentDestination
                )
            }
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
            ) {
                Spacer(Modifier.windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top)))
                RssNavHost(
                    navController = navController,
                    modifier = Modifier
                        .padding(padding)
                        .consumedWindowInsets(padding)
                )
                Spacer(Modifier.windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom)))
            }
        }

    }
}


@Composable
private fun RssBottomBar(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    // Wrap the navigation bar in a surface so the color behind the system
    // navigation is equal to the container color of the navigation bar.
    Surface(color = MaterialTheme.colorScheme.surface) {
        CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
            NavigationBar(
                modifier = Modifier.windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                    )
                ),
                tonalElevation = 0.dp
            ) {

                TOP_LEVEL_DESTINATIONS.forEach { destination ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == destination.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = { onNavigateToTopLevelDestination(destination) },
                        icon = {
                            Icon(
                                if (selected) {
                                    destination.selectedIcon
                                } else {
                                    destination.unselectedIcon
                                },
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(destination.iconTextId)) }
                    )
                }
            }
        }
    }
}
