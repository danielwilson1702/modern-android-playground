@file:JvmName("DateUtils")

//Outputs to DateUtils.java for the java peeps
package com.example.danie.notes.extensions

import android.database.Cursor
import android.os.Build
import android.support.v4.view.ViewCompat
import android.view.View
import java.util.*

/**
 * Created by danie on 17/07/2017.
 * Jake on Kotlin https://www.youtube.com/watch?v=A2LukgT2mKc
 */

fun Date.isTuesday(): Boolean {
    return day == 2
}

//Alternatively:
fun Date.isWednesday() = day == 3

//Compatibility shim example:
fun View.isAttachedToWindowCompat() =
        if (Build.VERSION.SDK_INT < 19)
            ViewCompat.isAttachedToWindow(this)
        else
            isAttachedToWindow

//Nullable extension function for retrieving a string from a cursor:
fun Cursor.getStringOrNull(columnName: String): String? {     //? = nullable
    val index = getColumnIndexOrThrow(columnName)
    return if (isNull(index)) null else getString(index)
}

fun Cursor.getString(columnName: String): String =
        getStringOrNull(columnName)!! //!! = This value will never be null, so unwrap into non-null return type (String instead of String?)

//Sample usage:
//val firstName = cursor.getStringOrNull("first_name") //The type here = String?
//firstNameView.setText(firstName ?: "Jake") //Elvis op handles the null case. Not required when !! is used