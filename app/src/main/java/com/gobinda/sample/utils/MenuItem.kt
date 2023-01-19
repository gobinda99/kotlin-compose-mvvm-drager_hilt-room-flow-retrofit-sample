package com.gobinda.sample.utils

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: String,
    val title: String? = null,
    val icon:  ImageVector? = null
)