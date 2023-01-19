package com.gobinda.sample.ui.test.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gobinda.sample.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreenTopBar(onOpenDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = onOpenDrawer) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "menu"
                )
            }

        },
        actions = {
            val expanded = remember { mutableStateOf(false) }

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Favorite, contentDescription = null)
            }
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Send, contentDescription = null)
            }
            IconButton(onClick = { expanded.value = !expanded.value }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
            }
            MoreActionPopupMenu(expanded.value, onDisMiss = {
                expanded.value = false
            })
        }
    )

}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
 fun PagerTopAppBar(onBackClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name )) },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "back"
                )
            }

        })
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
 fun DetailsTopAppBar(onBack: () -> Unit) {
    TopAppBar(title = { },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Share, contentDescription = null)
            }
        }
    )
}


@Preview
@Composable
fun ListScreenTopBarPreview() {
    ListScreenTopBar({})
}

@Preview
@Composable
fun PagerTopAppBarPreview() {
    PagerTopAppBar({})
}

@Preview
@Composable
fun DetailsTopAppBarPreview() {
    DetailsTopAppBar({})
}





