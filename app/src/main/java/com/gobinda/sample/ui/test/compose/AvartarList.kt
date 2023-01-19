package com.gobinda.sample.ui.test.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gobinda.sample.data.testData.Sample

@Composable
fun AvartarList(
    profiles: List<Sample>,
    onProfileClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.padding(top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(profiles) {
            AvatarItem(
                profileImageId = it.authorImageId,
                profileName = it.author,
                isMe = it.id == 1,
                onClick = onProfileClicked
            )
        }
    }
}
