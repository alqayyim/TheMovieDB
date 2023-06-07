package com.asad.core.extension

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

/**
 * @author asadurrahman.qayyim
 */

fun navigateTo(fragment: Fragment, bundle: Bundle, @IdRes resId: Int){
    try {
        fragment.findNavController().navigate(resId, bundle)
    } catch (e: IllegalArgumentException) {
        // User probably tapping 2 navigation at once!
        Log.e(fragment::class.java.simpleName, "Can't open 2 links at once!")
    }
}

fun Fragment.navigateTo(direction: NavDirections){
    try {
        this.findNavController().navigate(direction)
    } catch (e: IllegalArgumentException) {
        // User probably tapping 2 navigation at once!
        Log.e(this::class.java.simpleName, "Can't open 2 links at once!")
    }
}

fun Fragment.navigateBack(){
    try {
        this.findNavController().popBackStack()
    } catch (e: IllegalArgumentException) {
        // User probably tapping 2 navigation at once!
        Log.e(this::class.java.simpleName, "Can't open 2 links at once!")
    }
}

fun navigateUp(fragment: Fragment){
    try {
        fragment.findNavController().popBackStack()
    } catch (e: IllegalArgumentException) {
        // User probably tapping 2 navigation at once!
        Log.e(fragment::class.java.simpleName, "Can't open 2 links at once!")
    }
}
