package com.gobinda.sample.utils

import com.gobinda.sample.R

data class TripleState<T>(var value : T, var isError: Boolean = false, var message : Int = R.string.error)
