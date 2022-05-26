package com.husseinrasti.rss.startup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.husseinrasti.rss.startup.ui.RssApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent { RssApp() }
    }
}