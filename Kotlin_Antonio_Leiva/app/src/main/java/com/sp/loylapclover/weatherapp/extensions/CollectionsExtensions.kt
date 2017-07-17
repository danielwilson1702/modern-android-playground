package com.sp.loylapclover.weatherapp.extensions

/**
 * Created by danie on 17/07/2017.
 */
fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()