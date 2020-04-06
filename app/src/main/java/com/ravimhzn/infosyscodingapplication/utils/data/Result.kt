/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ravimhzn.infosyscodingapplication.utils.data

import androidx.annotation.StringRes
import com.ravimhzn.infosyscodingapplication.utils.data.Result.Success

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable?, @StringRes private val messageId: Int? = null) :
        Result<Nothing>() {
        fun messageId() = messageId ?: throwable?.toErrorMessageId()
        fun throwableMessage() = throwable?.message
    }

    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[throwable=$throwable]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Success && data != null

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Success<T>)?.data ?: fallback
}

fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> =
    when (this) {
        is Success -> {
            Success(transform(data))
        }
        is Result.Error -> {
            this
        }
        is Result.Loading -> {
            this
        }
    }