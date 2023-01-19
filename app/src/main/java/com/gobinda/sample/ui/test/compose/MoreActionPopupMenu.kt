package com.gobinda.sample.ui.test.compose


import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.gobinda.sample.R

@Composable
fun MoreActionPopupMenu(expanded: Boolean, onDisMiss: () -> Unit) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDisMiss,
        offset = DpOffset((32).dp, (-32).dp),
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        DropdownMenuItem(text = {
            Text(text = stringResource(id = R.string.menu_test_this))
        }, onClick = {}
        )

        DropdownMenuItem(
            text = { Text(text = stringResource(id = R.string.menu_test_that)) },
            onClick = { /* Handle send feedback! */ },
            enabled = false
        )

        DropdownMenuItem(
            text = { Text(text = stringResource(id = R.string.menu_test_there)) },
            onClick = { },
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
            }
        )

        DropdownMenuItem(
            text = { Text(text = stringResource(id = R.string.menu_test_here)) },
            onClick = { },
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = null)
            }
        )
    }
}

@Preview
@Composable
fun MoreActionPopupMenuPreview() {
    MoreActionPopupMenu(true, {})
}