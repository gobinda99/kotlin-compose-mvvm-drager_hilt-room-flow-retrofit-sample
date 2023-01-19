package com.gobinda.sample.data.testData

import com.gobinda.sample.R

data class Sample(
    val id: Int,
    val text: String,
    val author: String,
    val handle: String,
    val time: String,
    val authorImageId: Int,
    val likesCount: Int,
    val commentsCount: Int,
    val retweetCount: Int,
    val source: String,
    val tweetImageId: Int = 0
)

val sample = Sample(
    1,
    "Jetpack compose is the next thing for andorid. Declarative UI is the way to go for all screens.",
    author = "Google",
    handle = "@google",
    "12m",
    R.drawable.ic_launcher_foreground,
    100,
    12,
    15,
    "Twitter for web"
)


val sampleList = listOf(
    sample,
    sample.copy(
        id = 2,
        author = "Google",
        handle = "@google",
        time = "11m"
    ),
    sample.copy(
        id = 3,
        author = "Amazon",
        handle = "@Amazon",
        time = "1h"
    ),
    sample.copy(
        id = 4,
        author = "Facebook",
        handle = "@Facebook",
        time = "1h"
    ),
    sample.copy(
        id = 5,
        author = "Instagram",
        handle = "@Instagram",
        time = "11m"
    ),
    sample.copy(
        id = 6,
        author = "Twitter",
        handle = "@Twitter",
        time = "11m"
    ),
    sample.copy(
        id = 7,
        author = "Netflix",
        handle = "@Netflix",
        time = "11m"
    ),
    sample.copy(
        id = 8,
        author = "TedX",
        handle = "@TedX",
        time = "1h"
    ),
    sample.copy(
        id = 9,
        author = "Microsoft",
        handle = "@Microsoft",
        time = "1h"
    ),
    sample.copy(
        id = 3,
        author = "Food",
        handle = "@Food",
        time = "1h"
    ),
    sample.copy(
        id = 4,
        author = "Snapchat",
        handle = "@Snapchat",
        time = "1h"
    ),
    sample.copy(
        id = 5,
        author = "Snapchat",
        handle = "@Snapchat",
        time = "11m"
    ),
    sample.copy(
        id = 6,
        author = "Tiktok",
        handle = "@Tiktok",
        time = "11m"
    ),
    sample.copy(
        id = 7,
        author = "Samsung",
        handle = "@Samsung",

        time = "11m"
    ),
    sample.copy(
        id = 8,
        author = "Youtube",
        handle = "@Youtube",
        time = "1h"
    ),
    sample.copy(
        id = 9,
        author = "Gmail",
        handle = "@Gmail",
        time = "1h"
    ),
    sample.copy(
        id = 3,
        author = "Android",
        handle = "@Android",
        time = "1h"
    ),
    sample.copy(
        id = 4,
        author = "Whatsapp",
        handle = "@Whatsapp",
        time = "1h"
    ),
    sample.copy(
        id = 5,
        author = "Telegram",
        handle = "@Telegram",
        time = "11m"
    ),
    sample.copy(
        id = 6,
        author = "Spotify",
        handle = "@Spotify",
        time = "11m"
    ),
    sample.copy(
        id = 7,
        author = "WeChat",
        handle = "@WeChat",
        time = "11m"
    ),
    sample.copy(
        id = 8,
        author = "Airbnb",
        handle = "@Airbnb",
        time = "1h"
    ),
    sample.copy(
        id = 9,
        author = "LinkedIn",
        handle = "@LinkedIn",
        time = "1h"
    ),
    sample.copy(
        id = 6,
        author = "Shazam",
        handle = "@Shazam",
        time = "11m"
    ),
    sample.copy(
        id = 7,
        author = "Chrome",
        handle = "@Chrome",
        time = "11m"
    ),
    sample.copy(
        id = 6,
        author = "Safari",
        handle = "@Safari",
        time = "11m"
    ),
    sample.copy(
        id = 7,
        author = "Disney",
        handle = "@Disney",
        time = "11m"
    )


)