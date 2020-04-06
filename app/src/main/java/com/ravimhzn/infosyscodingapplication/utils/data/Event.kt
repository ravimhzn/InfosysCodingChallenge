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

import androidx.lifecycle.Observer

/**
 * Navigating from inside the ViewModel would mean you need an instance of
 * the view which goes against the concept of MVVM. Instead, use a LiveData
 * to indicate to your fragment that it needs to navigate to the next destination.
 * you can use the following Event class (from one of Google's architecture-samples)
 * to make sure the navigation is only fired once.
 */

open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? =
        if (hasBeenHandled)
            null
        else {
            hasBeenHandled = true
            content
        }

    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(t: Event<T>?) {
        t?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}