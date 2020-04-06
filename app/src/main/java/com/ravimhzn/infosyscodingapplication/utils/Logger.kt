package com.ravimhzn.infosyscodingapplication.utils

import timber.log.Timber

object Logger {
    fun i(message: String) {
        try {
            Timber.i(message)
        } catch (e: Exception) {
            print("On reporting a crash, app crashes, not much you can do here. " + e.message)
        }
    }

    fun e(message: String?) {
        try {
            Timber.e(message ?: "")
        } catch (e: Exception) {
            print("On reporting a crash, app crashes, not much you can do here. " + e.message)
        }
    }

    fun e(e: Exception) {
        try {
            Timber.e(e)
        } catch (e: Exception) {
            print("On reporting a crash, app crashes, not much you can do here. " + e.message)
        }
    }

    fun wtf(e: Exception) {
        try {
            Timber.wtf(e)
        } catch (e: Exception) {
            print("On reporting a crash, app crashes, not much you can do here. " + e.message)
        }
    }
}