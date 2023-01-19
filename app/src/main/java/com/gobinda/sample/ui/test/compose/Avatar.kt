package com.gobinda.sample.ui.test.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.gobinda.sample.data.testData.sampleList

@Composable
fun Avatar(
    imageId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: Dp = AvatarSize.medium,
) {
    val painter = rememberAsyncImagePainter("https://example.com/image.jpg")

    Image(
       // painter = painterResource(id = imageId),
        painter = painter,
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}

object AvatarSize {
    val small = 20.dp
    val medium = 32.dp
    val large = 64.dp
}

@Preview
@Composable
fun AvatarPreview() {
    Column {
        Avatar(
            imageId = sampleList.first().authorImageId,
            contentDescription = null,
            size = AvatarSize.small
        )

        Avatar(
            imageId = sampleList.first().authorImageId,
            contentDescription = null,
            size = AvatarSize.large
        )
    }
}
