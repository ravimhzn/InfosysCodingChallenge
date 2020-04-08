package com.ravimhzn.infosyscodingapplication.utils.extension

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(
    @IdRes actionId: Int,
    bundle: Bundle? = null,
    navOptions: NavOptions? = null
) {
    try {
        findNavController().navigate(actionId, bundle, navOptions)
    } catch (e: Exception) {
        Log.d("Error::", "Navigating has not worked as expected due to ${e.message}")
    }
}
