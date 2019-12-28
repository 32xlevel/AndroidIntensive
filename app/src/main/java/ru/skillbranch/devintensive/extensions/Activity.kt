package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val content = findViewById<View>(android.R.id.content)
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(content.windowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    val rect = Rect()
    window.decorView.getWindowVisibleDisplayFrame(rect)
    val screenHeight = window.decorView.rootView.height
    val keyboardHeight = screenHeight - rect.bottom
    return keyboardHeight > screenHeight * 0.15
}

fun Activity.isKeyboardClosed(): Boolean {
    return !isKeyboardOpen()
}