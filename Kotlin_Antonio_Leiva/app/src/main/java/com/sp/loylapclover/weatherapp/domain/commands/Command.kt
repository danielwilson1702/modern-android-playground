package com.sp.loylapclover.weatherapp.domain.commands

/**
 * Created by Daniel on 15/07/2017.
 */
interface Command<T> {
    fun execute(): T
}