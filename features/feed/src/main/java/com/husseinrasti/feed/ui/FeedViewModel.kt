package com.husseinrasti.feed.ui

import androidx.lifecycle.ViewModel
import com.husseinrasti.feed.data.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 5/27/22.
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedRepository: FeedRepository
) : ViewModel() {


}