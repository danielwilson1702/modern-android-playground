package com.example.danie.notes

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import java.io.File
import java.math.BigDecimal
import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Created by Daniel on 21/07/2017.
 */

typealias Name = String
typealias EmailData = String

class HadiOnKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        //Infix extension function example
        data class Money(var amount: BigDecimal, var currency: String)

        infix fun Int.percentOf(money: Money) = money.amount.multiply(this.bd).divide(100.bd)

        val popcorn = Money(200.bd, "$")
        7 percentOf popcorn

        //Extension property
        val longExample = 100L
        val bd = 100.bd

        //Jake https://www.youtube.com/watch?v=fPzxfeDJDzY
        //delegate override - interesting ability to kill unused interface methods
        class MyListener : Transition.TransitionListener by EmptyTransitionListener {
            override fun onTransitionStart(transaction: Transition) {
            }
        }

        //Object expression
        ThreadUtil.onMainThread(Runnable { /*Do sumfing*/ })

        //More extension magic:
        val file = File("filename.txt")
        with(file) {
            if (isAbsolute) {
            }

        }

        val string: String? = "Some value"
        string?.let {
            //If not null do some magic
        }


        //Loops
        for (b in 100 downTo 1 step 5) {

        }

        val capitals = listOf("Dublin", "London", "Paris")
        for (capital in capitals) {

        }

        capitals.forEach {
            capital ->
            if (capital == "Dublin") println("Wow look I found Dublin - $capital")
        }
        //V2
        capitals.forEach {
            if (it == "Dublin") println("Wow look I found Dublin - $it")
        }
        //V3 - Dayym
        capitals.filter { it == "Dublin" }.forEach { println("Wow look I found Dublin - $it") }
        //Map returns results based on map function
        val dublins = capitals.filter { it == "Dublin" }.map { it }
        dublins.forEach { println(it) }

        //Lazy evaluation - dodgy performance without asSequence
        val elements = 1..1000000
        //val output = elements.asSequence().filter { it < 30 }.map{ Pair("Yes", it)}
        //output.forEach { println(it) }
        //Taking only 30 stops execution completely after 30
        val output = elements.asSequence().take(30).filter { it < 30 }.map { Pair("Yes", it) }

        //Perform an op on a sequence
        val numbers = generateSequence(1) {
            x ->
            x + 10
        }
        println(numbers.take(30).sum())

        //Lazy init
        val lazyinit: Int by lazy { 10 }


        var i = 100
        while (i > 0) {
            i--
        }

        //Conditional statements
        var myString = "Not empty"
        val result = if (myString != "") {
            println("Not empty")
            "A string"
        } else {
            println("Empty")
            "Another String"
        }

        val whenResult = when (result) {
            is String -> println("Excellent")
            "Another String" -> println("Another String")
            else -> println("Else")
        }
        println(whenResult)

        //Classes
        class Customer(val id: Int, var name: String = "", val yearOfBirth: Int = 0) {
            init {
                name = name.toUpperCase()
            }

            constructor(email: String) : this(0, name = "") {
                //Similar to init block on primary constructor
            }

            val age: Int
                get() = Calendar.getInstance().get(Calendar.YEAR) - yearOfBirth

            var socialSecurityNumber: String = ""
                set(value) {
                    if (value.startsWith("XX")) {
                        throw IllegalArgumentException("Social security number invalid")
                    }
                    field = value //NB Required backing field actually set
                }
        }

        val customer = Customer(10, "Dan", 1973)
        println(customer.age)
        val customer2 = Customer(11)
        val customer3 = Customer("hello@mail.com")

        //Enums
        val priority = Priority.NORMAL
        println(priority) //NORMAL
        println(priority.ordinal) //1
        println(priority.value) //0
        println(Priority.CRITICAL.name) //CRITICAL

        for (pri in Priority.values()) {

        }

        Priority.valueOf("MAJOR")

        //Safe casting
        var inputInt: Any = 10

        val str = inputInt as? String //Attempt to cast

        println(str?.length)

        //Deconstructing values
        val (captial, country, population) = countryInformation("Madrid")

        //Exceptions
        fun checkIsNumber(obj: Any) {
            when (obj) {
                !is Int, Long, Float, Double -> throw NotANumberException("Not a number")
            }
        }

        val tryResult = try {
            checkIsNumber("A")
        } catch (e: IllegalArgumentException) {
            //Print
            -2 //(<-- Last line is the return element)
        } catch(e: NotANumberException) {

        }

        //----------Advanced Kotlin with Hadi
        //Inline sample - takes a higher order function with a lambda and optimizes it by copy pasting
        operation { println("This is the actual op") }

        //Sealed classes
        fun getUrlPage(url: String): PageResult {
            val wasValidCall = false
            if (wasValidCall) {
                return Success("The Content")
            } else {
                return Error(404, "Not Found")
            }
        }

        val pageResult = getUrlPage("/")
        when(pageResult){
            is Success -> println(pageResult.content)
            is Error -> println(pageResult.code)
        }

        //Class Delegation //1 or 2 of these max
        class Controller(repository: Repository): Repository by repository{

            fun index(): String{
                getById(5)
                return ""
            }
        }

        //Delegating properties, calls a getter via another class
        val service = Service()
        println(service.someProperty)

        //Built in delegate - observable. Called when 'department' is set
        data class Employee(val name: Name, val email: EmailData){
            var department: String by Delegates.observable("", {property, oldValue, newValue ->
                println("Property ${property.name} has changed from $oldValue to $newValue")
            })
        }

        //Vetoable delegate
        class Veto{
            var value: String by Delegates.vetoable("String") {prop, old, new ->
                new.startsWith("S")
            }
        }

        val veto = Veto()
        veto.value = "London"
        veto.value = "Spain" //Only set here because veto.value starts with S

        //Extension property
        println("Hello".hasAmpersand)
    }


    val String.hasAmpersand: Boolean
        get() = this.contains("&")

    class Service{
        var someProperty: String by ExternalFunctionality()
    }

    class ExternalFunctionality {
        var backingField = "Default"
        operator fun getValue(any: Service, property: KProperty<*>): String {
            println("getValue called with params: \n")
            return backingField
        }

        operator fun setValue(any: Service, property: KProperty<*>, value: String) {
            backingField = value
        }
    }


    class Customer
    interface Repository{
        fun getById(id: Int): Customer
        fun getAll(): List<Customer>
    }

    sealed class PageResult

    class Success(val content: String) : PageResult() //Requires main class to be PageResult
    class Error(val code: Int, val message: String) : PageResult()

    inline fun operation(op: () -> Unit) {
        println("Before op")
        op()
        println("After op")
    }

    class NotANumberException(message: String) : Throwable(message) {}

    private fun countryInformation(capital: String): Triple<String, String, Long> {
        return Triple(capital, "Spain", 230000)

    }

    //Object (also a singleton)
    object Global {
        val PI = "3.14"
    }


    enum class Priority(val value: Int) {
        MINOR(-1) {
            override fun someMethod(): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun toString(): String {
                return "Minor priority"
            }
        },
        NORMAL(0) {
            override fun someMethod(): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        },
        MAJOR(10) {
            override fun someMethod(): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        },
        CRITICAL(100) {
            override fun someMethod(): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        };

        abstract fun someMethod(): String
    }

    private val Int.bd: BigDecimal
        get() {
            return BigDecimal(this)
        }

    object EmptyTransitionListener : Transition.TransitionListener {
        override fun onTransitionEnd(transition: Transition?) {}
        override fun onTransitionResume(transition: Transition?) {}
        override fun onTransitionPause(transition: Transition?) {}
        override fun onTransitionCancel(transition: Transition?) {}
        override fun onTransitionStart(transition: Transition?) {}
    }


    object ThreadUtil {

        fun onMainThread(runnable: Runnable) {
            val mainHandler = Handler(Looper.getMainLooper())
            mainHandler.post(runnable)
        }
    }
}
