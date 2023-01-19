package com.gobinda.sample.utils

import java.util.regex.Pattern


private val EMAIL_PATTERN = Pattern.compile(
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

private val  PASSWORD_PATTERN = Pattern.compile(
"((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,8})$")


val String?.isValidEmail get() = this != null && EMAIL_PATTERN.matcher(this).matches()

val String?.isValidPassword get() = this !=null && PASSWORD_PATTERN.matcher(this).matches()
