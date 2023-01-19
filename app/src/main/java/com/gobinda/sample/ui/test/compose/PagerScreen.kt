package com.gobinda.sample.ui.test.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gobinda.sample.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabPagerScreen( onBackClicked : () -> Unit) {

    Scaffold(
        topBar = {
            PagerTopAppBar(onBackClicked)

        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
         TabWithPager()
        }

    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabWithPager() {
    val tabs = listOf(
        stringResource(id = R.string.test_home) to Icons.Filled.Home,
        stringResource(id = R.string.test_add) to Icons.Filled.AccountBox,
        stringResource(id = R.string.test_help) to Icons.Filled.ShoppingCart,
        stringResource(id = R.string.test_settings) to Icons.Filled.Settings,
    )
    val pagerState = rememberPagerState(
        initialPage = 1,
    )
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[tabIndex])
                )
            }
        ) {
            tabs.forEachIndexed { index, pair ->
                Tab(selected = tabIndex == index, onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = {
                    Text(text = pair.first)
                }, icon = {
                    Icon(imageVector = pair.second, contentDescription = null)
                })
            }
        }
        HorizontalPager(
            pageCount = tabs.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if(index== 1){
                    LazyRowLazyColumnScreen()
                } else {
                    Text(
                        text = tabs[index].first,
                    )
                }

            }
        }

    }
}

@Preview
@Composable
fun TabWithPagerPreview() {
    TabWithPager()
}