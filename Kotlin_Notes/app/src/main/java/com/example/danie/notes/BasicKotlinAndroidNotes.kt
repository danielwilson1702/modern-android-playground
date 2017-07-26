
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.inputmethod.InputMethodManager


//<editor-fold desc="Retrofit">

/*https://savvyapps.com/blog/kotlin-tips-android-development
Lazy loading, e.g.

val purchasingApi: PurchasingApi by lazy {
    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    retrofit.create(PurchasingApi::class.java)
}*/
//</editor-fold>

//<editor-fold desc="Basic Lambdas">
/*button.setOnClickListener { view ->
    startDetailActivity()
}

toolbar.setOnLongClickListener {
    showContextMenu()
    true
}

val sumAlternative = { x: Int, y: Int -> x + y }
val v = sumAlternative(5, 4)

val notEmpty: (String) -> Boolean = { !it.isEmpty() }
val atLeastFour: (String) -> Boolean = { it.length > 4 }
val fourDigits: (String) -> Boolean = { it.matches(Regex("\\d{4}")) }
val validCreditCard: (String) -> Boolean = { luhnCheck(it) }*/
//</editor-fold>

//https://blog.jetbrains.com/kotlin/2015/05/advanced-features-of-anko/
//<editor-fold desc="Anko - alert dialog">
/*val flowers = listOf("Chrysanthemum", "Rose", "Hyacinth")
selector("What is your favorite flower?", flowers) { i ->
    toast("So your favorite flower is ${flowers[i]}, right?")
}*/
//</editor-fold>

//<editor-fold desc="Anko - progress dialog">
/*progressDialog("Please wait a minute.", "Downloading…")
indeterminateProgressDialog("Fetching the data…")*/
//</editor-fold>

//<editor-fold desc="Anko - service access">
/*if (!wifiManager.isWifiEnabled()) {
    vibrator.vibrate(200)
    toast("Wifi is disabled. Please turn on!")
}*/
//</editor-fold>

//<editor-fold desc="Anko - non-leaking async task">
/*async {
    uiThread {
        *//* Safe version. This code won't be executed
            if the underlying Context is gone. *//*
    }
    ctx.uiThread {
    *//* Here we are calling the `uiThread`
        extension function for Context directly,
        so we are holding a reference to it. *//*
    }
}*/
//</editor-fold>

//<editor-fold desc="Anko - Logcat">
/*class SomeActivity : Activity(), AnkoLogger {
    fun someMethod() {
        info("Info message")
        debug(42) // .toString() method will be called automatically
    }
}*/
//</editor-fold>

//<editor-fold desc="Extension - Hide Keyboard ">
fun Activity.hideKeyboard(): Boolean {
    val view = currentFocus
    view?.let {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
    }
    return false
}
//</editor-fold>

//Jake on Kotlin https://www.youtube.com/watch?v=A2LukgT2mKc

//<editor-fold desc="Extension Function Expression - Shared preferences ">
/*inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
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
}*/
//</editor-fold/>

//<editor-fold desc="Extension Function Expression - Notification Builder">
/*
inline fun notification(context: Context,
                        func: Notification.Builder.() -> Unit): Notification {
    val builder = Notification.Builder(context)
    builder.func()
    return builder.build()
}

val n = notification(context){
    setContentTitle("Hi")
    setSubText("Hello")
}*/
//</editor-fold/>

//<editor-fold desc="Extension Function Expression - DB transaction">
//For example this will take a function and sandwich it between begin, success and end calls
//inline is an allocation optimization, almost always they should be inline
/*inline fun SQLiteDatabase.inTransaction(func: SQLiteDatabase.() -> Unit) {
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
}*/
//</editor-fold/>