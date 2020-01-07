package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import kotlin.math.roundToInt

fun Activity.hideKeyboard() {
    val focus = this.currentFocus
    focus?.let {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
            focus.windowToken,
            0
        )
    }
}

fun Activity.isKeyboardOpen(): Boolean {
    fun Context.convertDpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            this.resources.displayMetrics
        )
    }

    val rootView = this.window.decorView.rootView
    val visibleFrame = Rect()
    rootView.getWindowVisibleDisplayFrame(visibleFrame)
    val heightDiff = rootView.height - visibleFrame.height()
    val marginOfError = this.convertDpToPx(50F).roundToInt()
    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed(): Boolean {
    return !isKeyboardOpen()
}
