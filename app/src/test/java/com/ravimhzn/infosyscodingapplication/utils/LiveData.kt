package com.ravimhzn.infosyscodingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {},
    latchCount: Int = 1
): T? {
    var data: T? = null
    val latch = CountDownLatch(latchCount)
    val observer = Observer<T> { o ->
        data = o
        latch.countDown()
    }
    this.observeForever(observer)

    afterObserve.invoke()

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        this.removeObserver(observer)
        return null
    }

    this.removeObserver(observer)
    @Suppress("UNCHECKED_CAST")
    return data as T
}
