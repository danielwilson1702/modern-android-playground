package com.example.danie.notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.danie.notes.extensions.isTuesday
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDate: Date = Date()
        val isTuesday = myDate.isTuesday()
    }
}
