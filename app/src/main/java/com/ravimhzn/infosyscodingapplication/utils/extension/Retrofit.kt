package com.ravimhzn.infosyscodingapplication.utils.extension

import okhttp3.Request
import retrofit2.Response

fun Request.Builder.addHeaders(map: Map<String, String>): Request.Builder =
    apply {
        map.forEach {
            addHeader(it.key, it.value)
        }
    }

fun Request.Builder.addAuthHeader(token: String?): Request.Builder =
    apply {
        addHeader("Authorization", "Bearer $token")
    }

fun Response<*>.fullErrorMessage() =
    "API response error. Error code: ${this.code()}. Error message: ${this.message()}\nFull error message: $this"