package com.gobinda.sample.ui.test.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gobinda.sample.ui.test.*
import com.gobinda.sample.data.testData.sampleList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LazyRowLazyColumnScreen() {
    LazyRowLazyColumnScreenContent()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowLazyColumnScreenContent() {
    val list = remember { sampleList }
    val lazyListState = rememberLazyListState()

    var refreshing by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    fun refresh() = scope.launch {
        refreshing = true
        delay(1500)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    Surface {
        Column {
            AvartarList(profiles = list, onProfileClicked = { })

            Divider()

            Box(modifier = Modifier.pullRefresh(state)) {

                LazyColumn(state = lazyListState) {
                    items(list) {
                        ListItem(it) {
                        }
                    }
                }
                PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
            }
        }
    }


}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LazyRowLazyColumnPreview() {
    LazyRowLazyColumnScreen()
}
