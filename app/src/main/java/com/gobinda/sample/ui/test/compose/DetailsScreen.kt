package com.gobinda.sample.ui.test.compose

import android.webkit.WebView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(onBack: () -> Unit) {

    Scaffold(topBar = {
        DetailsTopAppBar(onBack)
    }) {
        DetailsScreenContent(modifier = Modifier.padding(it))
    }
}


@Composable
fun DetailsScreenContent(modifier: Modifier) {

    LazyColumn() {
        item {
            AsyncImage(
                model = "https://developer.android.com/static/images/jetpack/compose/m3-sampleapp.png",
                contentDescription = null
            )
        }
        item {
            AndroidView(
                factory = {
                    WebView(it).apply {
                        loadUrl("https://developer.android.com/jetpack/compose/documentation")
                    }
                },
                modifier = Modifier.wrapContentHeight()
            )
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    Surface {
        DetailsScreen() {}
    }
}