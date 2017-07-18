package com.example.danie.notes.functionexpressions

import android.app.Notification
import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.util.JsonReader
import android.view.ContextMenu
import java.nio.file.Files.delete

/**
 * Created by danie on 17/07/2017.
 */

/*
//Feed 'sum' two ints, and it will give us back one
val sum: (Int, Int) -> Int = { x, y -> x + y }
val w = sum(5, 4)
//Alternatively
val sumAlternative = { x: Int, y: Int -> x + y }
val v = sumAlternative(5, 4)

val notEmpty: (String) -> Boolean = { !it.isEmpty() }
val atLeastFour: (String) -> Boolean = { it.length > 4 }
val fourDigits: (String) -> Boolean = { it.matches(Regex("\\d{4}")) }
val validCreditCard: (String) -> Boolean = { luhnCheck(it) }

//Higher-Order Functions
fun <T> List<T>.filter(predicate: (T) -> Boolean): List<T> {
    val newList = ArrayList<T>()
    for (item in this) {
        if (predicate(item)) {
            newList.add(item)
        }
    }
    return newList
}

val names = listOf("Jake", "Jesse", "Matt", "Alec")
val jakes = names.filter { it == "Jake" }

//
//data class is shorthand for creating equals/hashcode/toString and getters/setters
data class Lock<T>(private val obj: T) {
    public fun acquire(func: (T) -> Unit) { //Takes in a function, returns void (Unit)
        synchronized(obj) {
            func(obj)
        }
    }
}

val readerLock = Lock(JsonReader(stream))

readerLock.acquire{
    //blah blah it.readString()
}


//Extension Function Expressions
//For example this will take a function and sandwich it between begin, success and end calls
//inline is an allocation optimization, almost always they should be inline
inline fun SQLiteDatabase.inTransaction(func: SQLiteDatabase.() -> Unit) {
    beginTransaction()
    try {
        func() //(this = the SQLiteDatabase)
        setTransactionSuccessful()
    } finally {
        endTransaction()
    }
}

db.inTransaction{
    delete("users", "first_name = ?", arrayOf("Jake"))
}

//
inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.func()
    editor.apply()
}

fun SharedPreferences.Editor.set(pair: Pair<String, String>) =
        putString(pair.first, pair.second)

preferences.edit{
    putString("foo" to "bar") //'to' is an infix method to create a Pair
    putString("fizz" to "buzz")
    remove("username")
}

//
inline fun notification(context: Context,
                        func: Notification.Builder.() -> Unit): Notification {
    val builder = Notification.Builder(context)
    builder.func()
    return builder.build()
}

val n = notification(context){
    setContentTitle("Hi")
    setSubText("Hello")
}

//

*/
