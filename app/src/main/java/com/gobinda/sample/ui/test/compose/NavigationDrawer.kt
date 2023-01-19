package com.gobinda.sample.ui.test.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gobinda.sample.R
import com.gobinda.sample.ui.theme.spacing
import com.gobinda.sample.utils.MenuItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawerScreen(
    drawerState: DrawerState,
    onItemClick: (String) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(drawerContent = {
        NavDrawerContent( onItemClick = onItemClick)
    }, drawerState = drawerState) {
        content()
    }
}

@Composable
fun NavDrawerContent(onItemClick: (String) -> Unit) {

    Surface(modifier = Modifier.background(Color.White)
        .fillMaxWidth(.8f)
        .fillMaxHeight()) {
        DrawerBody(modifier = Modifier, onItemClick = onItemClick )
    }



}

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier.padding(MaterialTheme.spacing.large)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive
        )
    }
}


@Composable
fun DrawerBody(
    items: List<MenuItem>, modifier: Modifier = Modifier, onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) {
            Row(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable { onItemClick(it) }) {
                Icon(
                    imageVector = it.icon!!, contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = it.title!!, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun DrawerBody(modifier: Modifier, onItemClick : (String) -> Unit) {
    LazyColumn() {

        item { DrawerHeader() }

        item { Divider(thickness = 0.3.dp) }
        item { Spacer(modifier.padding(top = 8.dp)) }
        item {
            DrawerItem(
                "home",
                icon = Icons.Default.Home,
                title = stringResource(id = R.string.test_home),
                onClick = onItemClick
            )
        }
        item { Spacer(modifier.padding(top = 8.dp)) }
        item { Divider(thickness = 0.3.dp) }

        item { Spacer(modifier.padding(top = 8.dp)) }

        item { DrawerCategory(title = stringResource(id = R.string.test_others)) }

        item {
            DrawerItem(
                id = "settings",
                icon = Icons.Default.Settings,
                title = stringResource(id = R.string.test_settings),
                onClick = onItemClick
            )
        }
        item {
            DrawerItem(
                id = "help",
                icon = Icons.Default.Call,
                title = stringResource(id = R.string.test_help),
                onClick = onItemClick
            )
        }
        item {
            DrawerItem(
                id = "logout",
                icon = Icons.Default.ExitToApp,
                title = stringResource(id = R.string.test_logout),
                onClick = onItemClick
            )
        }


    }
}

@Composable
fun DrawerCategory(title: String) {

    Text(
        text = title,
        letterSpacing = 0.7.sp,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 12.sp,
        modifier = Modifier.padding(16.dp)
    )

}

@Composable
fun DrawerItem(id: String, icon: ImageVector, title: String, msgCount: String = "", onClick: (String) -> Unit) {

    Row(modifier = Modifier.clickable {
        onClick.invoke(id)
    }) {
       Icon(
            imageVector = icon,
            modifier = Modifier.padding(16.dp),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp),
            text = title,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )

        if (msgCount.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp),
                text = msgCount,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start
            )
        }

    }

}


@Preview
@Composable
fun NavDrawerPreview() {
    Surface() {
        DrawerBody(modifier = Modifier){}
    }
}