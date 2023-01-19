package com.gobinda.sample.ui.test.compose

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gobinda.sample.R
import com.gobinda.sample.data.testData.sampleList
import com.gobinda.sample.ui.theme.graySurface
import com.gobinda.sample.utils.SimmerList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeListScreen(
    onOpenDrawer: () -> Unit,
    onItemClick: () -> Unit,
    onButtonItemClick: (key: String) -> Unit
) {
    Scaffold(
        topBar = {
            ListScreenTopBar(onOpenDrawer = onOpenDrawer)
        },
        floatingActionButton = { FloatingActButton() },
        bottomBar = {/* BottomBar({})*/ ButtonNavigation(onItemClick = onButtonItemClick) }
    ) {
        Surface(Modifier.padding(it)) {
            LazyColumnListContent(onItemClick)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnListContent(onItemClick: () -> Unit) {
    val tweets = remember { sampleList }
    val lazyListState = rememberLazyListState()

    var refreshing by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    fun refresh() = scope.launch {
        refreshing = true
        delay(1500)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)


    var initialLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(initialLoading) {
        if (initialLoading) {
            delay(2000)
            initialLoading = false
        }
    }

    SimmerList(isLoading = initialLoading) {

        Box(modifier = Modifier.pullRefresh(state)) {

            LazyColumn(state = lazyListState) {

                items(tweets) {
                    val visible by remember(it.id) { mutableStateOf(true) }

                    AnimatedVisibility(visible = visible) {
                        Box(modifier = Modifier.background(Color.Green)) {
                            ListActionItems(modifier = Modifier.align(Alignment.CenterEnd))
                            ListItem(it) {
                                onItemClick.invoke()
                            }
                        }
                    }
                }

            }

            PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
        }
    }

}


@Composable
fun BottomBar(onBack: () -> Unit) {
    BottomAppBar(
    ) {
        IconWithBadge(badge = 1, icon = Icons.Filled.Home)
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
        }

    }
}

/*@Composable
fun ButtonNavigation(onItemClick: (key: String) -> Unit) {
    val background = if (isSystemInDarkTheme()) graySurface else Color.White
    BottomNavigation(
        backgroundColor = background
    ) {
        BottomNavigationItem(
            icon = {
                IconWithBadge(badge = 0, icon = Icons.Outlined.Home)
            },
            onClick = {
                onItemClick.invoke("email")
            },
            selected = true,
            label = { Text(stringResource(id = R.string.test_home)) },
        )

        BottomNavigationItem(
            icon = {
                IconWithBadge(badge = 3, icon = Icons.Outlined.Notifications)
            },
            onClick = {
            },
            selected = true,
            label = { Text(stringResource(id = R.string.test_notification)) },
        )


    }
}*/

@Composable
fun ButtonNavigation(onItemClick: (key: String) -> Unit) {
    val background = if (isSystemInDarkTheme()) graySurface else Color.White
    NavigationBar(
        containerColor = background
    ) {
        NavigationBarItem(
            icon = {
                IconWithBadge(badge = 0, icon = Icons.Outlined.Home)
            },
            onClick = {
                onItemClick.invoke("email")
            },
            selected = true,
            label = { Text(stringResource(id = R.string.test_home)) },
        )

        NavigationBarItem(
            icon = {
                IconWithBadge(badge = 3, icon = Icons.Outlined.Notifications)
            },
            onClick = {
            },
            selected = false,
            label = { Text(stringResource(id = R.string.test_notification)) },
        )
    }
}


@Composable
fun IconWithBadge(badge: Int, icon: ImageVector, modifier: Modifier = Modifier) {

    Box(modifier = Modifier.size(32.dp)) {
        Icon(
            imageVector = icon,
            modifier = modifier.align(
                Alignment.BottomCenter
            ),
            contentDescription = null
        )

        if (badge != 0) {
            Text(
                text = "$badge",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .align(Alignment.TopEnd)
                    .size(16.dp)
            )
        }
    }

}


@Composable
fun FloatingActButton() {
    var pressed by remember { mutableStateOf(false) }
    ExtendedFloatingActionButton(
        icon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
        text = { Text(text = stringResource(id = R.string.test_add)) },
        onClick = { pressed = !pressed },
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.width(animateDpAsState(if (pressed) 120.dp else 100.dp).value)
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    HomeListScreen({}, {}, {})
}
