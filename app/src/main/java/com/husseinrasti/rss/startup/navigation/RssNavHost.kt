/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.husseinrasti.rss.startup.navigation

import android.app.Activity
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.doOnPreDraw
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.husseinrasti.favorite.navigation.favoriteGraph
import com.husseinrasti.feed.navigation.FeedRouter
import com.husseinrasti.feed.navigation.feedGraph

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun RssNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = FeedRouter.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        feedGraph()
        favoriteGraph()
    }
// Reporting the app fully drawn to get accurate TTFD readings for the baseline profile.
// https://developer.android.com/topic/performance/vitals/launch-time#retrieve-TTFD
    ReportFullyDrawn(FeedRouter.route)
}

/**
 * Calling [Activity#reportFullyDrawn] in compose UI.
 */
@Composable
private fun ReportFullyDrawn(destination: String) {
    // Holding on to the local view and calling `reportFullyDrawn` in an `onPreDraw` listener.
    // Compose currently doesn't offer a way to otherwise report fully drawn,
    // so this is a viable approach.
    val localView: View = LocalView.current
    (localView.context as? Activity)?.run {
        localView.doOnPreDraw {
            reportFullyDrawn()
        }
    }
}
