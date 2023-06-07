package com.asad.core.extension

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
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

fun navigateTo(fragment: Fragment, @IdRes resId: Int){
    try {
        fragment.findNavController().navigate(resId)
    } catch (e: IllegalArgumentException) {
        // User probably tapping 2 navigation at once!
        Log.e(fragment::class.java.simpleName, "Can't open 2 links at once!")
    }
}

fun navigateBack(fragment: Fragment){
    try {
        fragment.findNavController().popBackStack()
    } catch (e: IllegalArgumentException) {
        // User probably tapping 2 navigation at once!
        Log.e(fragment::class.java.simpleName, "Can't open 2 links at once!")
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

fun <T : Any> Fragment.setBackStackData(key: String, data : T, doBack : Boolean = true) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, data)
    if(doBack) findNavController().popBackStack()
}

fun <T : Any> Fragment.removeBackstackData(key: String) {
    findNavController().currentBackStackEntry?.savedStateHandle?.remove<T>(key)
}

fun Fragment.clearBackstateData(key: String) {
    findNavController().previousBackStackEntry?.savedStateHandle?.clearSavedStateProvider(key)
}


fun <T : Any> Fragment.getBackStackData(key: String, result: (T) -> (Unit)) {
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)?.observe(viewLifecycleOwner) {
        result(it)
    }
}