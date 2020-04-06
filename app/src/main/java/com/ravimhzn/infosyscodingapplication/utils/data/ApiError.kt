package com.ravimhzn.infosyscodingapplication.utils.data

import com.google.gson.JsonSyntaxException
import com.ravimhzn.infosyscodingapplication.R
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.toErrorMessageId(): Int =
    when (this) {
        //Common Error Handling
        is UnknownHostException -> R.string.no_connection
        is SocketTimeoutException -> R.string.no_connection
        is JsonSyntaxException -> R.string.unknown_error_message
        else -> R.string.unknown_error_message
    }