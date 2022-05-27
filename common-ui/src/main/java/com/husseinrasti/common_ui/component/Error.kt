package com.husseinrasti.common_ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifiStatusbarConnectedNoInternet4
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.husseinrasti.common_ui.theme.AppTheme


/**
 * Created by Hussein Rasti on 5/27/22.
 */

@Composable
fun ErrorNetwork(
    modifier: Modifier = Modifier,
    message: String?,
    imageVector: ImageVector = Icons.Filled.SignalWifiStatusbarConnectedNoInternet4
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(72.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = message ?: "Something went wrong.")
    }
}


@Preview(
    name = "Loading Wheel Light Preview",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Loading Wheel Dark Preview",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun ErrorPreview() {
    AppTheme {
        ErrorNetwork(message = "Something went wrong.")
    }
}