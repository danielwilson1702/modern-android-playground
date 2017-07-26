package com.example.danie.notes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.danie.notes.extensions.isTuesday
import java.util.*

/**
 * Derek Banas Kotlin in one video https://www.youtube.com/watch?v=H_oGi8uuDpA
 * Simple syntax
 */
class DerekBanasOnKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Basic extension function
        val myDate: Date = Date()
        val isTuesday = myDate.isTuesday()

        //String interpolation
        var bigInt: Int = Int.MAX_VALUE
        var smallInt: Int = Int.MIN_VALUE
        Log.d("Main", "Smallest int: $smallInt")

        if (true is Boolean) {/*Check if a var is of type Boolean*/
        }

        var letterGrade: Char = 'A'
        Log.d("Main", "A is a Char: ${letterGrade is Char}")

        //Basic casting
        Log.d("Main", "3.14 to Int: " + (3.14.toInt()))
        //Smart casting, is and when
        val x = "string"
        if (x !is String || x.length == 0) println("X is not a string or it's length is 0")

        /*when (x) {
            is Int -> print(x + 1)
            is String -> print(x.length + 1)
            is IntArray -> print(x.sum())
        }*/

        //Nullable safe cast
        val x2 = "0"
        val x1: String? = x2 as? String

        //Strings
        val myName = "Dan Wilson"
        var fName: String = "Dan"
        var lName: String = "Wilson"

        fName = "Sally"
        var fullName = fName + " " + lName
        Log.d("Main", "Name: $fullName")

        var str1 = "A random string"
        var str2 = "a random string"
        Log.d("Main", "Strings Equal : ${str1.equals(str2)}")

        Log.d("Main", "2nd Index: ${str1[2]}")
        Log.d("Main", "Contains random: ${str1.contains("random")}")

        //Arrays
        var myArray = arrayOf(1, 1.23, "Dan")
        Log.d("Main", "Array index: ${myArray[0]}")
        Log.d("Main", "Array size: ${myArray.size}")
        Log.d("Main", "Dan in array: ${myArray.contains("Dan")}")

        var partArray = myArray.copyOfRange(0, 1)
        Log.d("Main", "First: ${partArray.first()}")
        Log.d("Main", "Dan index: ${partArray.indexOf("Dan")}")

        var sqArray = Array(5, {x -> x * x})
        Log.d("Main", sqArray[2].toString()) //prints 4

        var arr2: Array<Int> = arrayOf(1, 2, 3)

        //Ranges
        val oneToTen = 1..10
        val alpha = "A".."Z"

        Log.d("Main", "R in Alpha: ${"R" in alpha}")

        val tenToOne = 10.downTo(1)
        val twoToTwenty = 2.rangeTo(20)

        val range3 = oneToTen.step(3)

        for(x in range3) Log.d("Main", "range3: $x")
        for(x in tenToOne.reversed()) Log.d("Main", "Reverse: $x")

        //Conditional operators - no more if elses / switches
        var age = 8
        when(age){
            0,1,2,3,4 -> Log.d("Main", "Go to preschool")

            5 -> Log.d("Main", "Go to grade")

            in 6..17 ->{
                val grade = age-5
                Log.d("Main", "Go to grade $grade")
            }

            else -> Log.d("Main", "Go to college")
        }

        //Looping
        for(x in 1..10){
            Log.d("Main", "Loop: $x")
        }

        val rand = Random()
        val magicNum = rand.nextInt(50) + 1

        var guess = 0
        while(magicNum != guess){
            guess += 1
        }

        Log.d("Main", "Magic number was $guess")

        for(x in 1..20){
            if(x % 2 == 0){
                continue
            }

            Log.d("Main", "Odd : $x")

            if(x == 15) break
        }

        var arr3: Array<Int> = arrayOf(3, 6, 9)
        for(i in arr3.indices){
            Log.d("Main", "Multiples of 3: ${arr3[i]}")
        }

        for((index, value) in arr3.withIndex()){
            Log.d("Main", "Index: $index Value: $value")
        }

        //Functions
        fun add(num1: Int, num2: Int) : Int = num1 + num2
        Log.d("Main", "5 + 4 = ${add(5, 4)}")

        fun subtract(num1: Int = 1, num2: Int = 1) = num1 - num2
        Log.d("Main", "5 - 4 = ${subtract(5, 4)}")

        //Named params
        Log.d("Main", "4 - 5 = ${subtract(num2 = 5, num1 = 4)}")

        fun sayHello(name: String) : Unit = println("Hello $name")
        sayHello("Jim")

        //Variable length params
        fun nextTwo(num: Int): Pair<Int, Int>{
            return Pair(num+1, num+2)
        }

        val (two, three) = nextTwo(1)
        Log.d("Main", "1 $two $three")

        fun getSum(vararg nums: Int): Int
        {
            var sum = 0
            nums.forEach { n -> sum += n }

            return sum
        }

        Log.d("Main", "Sum = ${getSum(1, 2, 3, 4, 5)}")

        val multiply = {num1: Int, num2: Int -> num1 * num2}
        Log.d("Main", "5*3 = ${multiply(5, 3)}")

        //Recursion
        fun fact(x: Int): Int{
            tailrec fun factTail(y: Int, z: Int): Int{
                if(y == 0) return z
                else return factTail(y - 1, y * z)
            }
            return factTail(x, 1)
        }

        Log.d("Main", "5! = ${fact(5)}")

        //Higher order functions
        val numList = 1..20
        val evenList = numList.filter { it % 2 == 0 }
        evenList.forEach{ n -> println(n) }

        val mult3 = makeMathFunc(3)
        println("5 * 3 = ${mult3(5)}")

        val multiply2 = {num1: Int -> num1 * 2}
        val numList2 = arrayOf(1, 2, 3, 4, 5)
        mathOnList(numList2, multiply2)

        //
        //Collection ops
        val numList3 = 1..20
        val listSum = numList3.reduce{x, y -> x + y}
        println("Reduce Sum: $listSum")

        val listSum2 = numList3.fold(5){x, y -> x + y}
        println("Fold Sum: $listSum2")

        println("Contains Evens: ${numList3.any{ it % 2 == 0}}")
        println("All Evens: ${numList3.all{ it % 2 == 0}}")

        val big3 = numList2.filter { it > 3 }
        big3.forEach{n -> println(">3 : $n")}

        val times7 = numList3.map{ it * 7}
        times7.forEach{n -> println("*7 : $n")}

        //Exceptions
        val divisor = 2
        try{
            if(divisor == 0){
                throw IllegalArgumentException("Can't divide by zero")
            }else{
                println("5 / $divisor = ${5/divisor}")
            }
        }catch(e: IllegalArgumentException){
            println("${e.message}")
        }

        //Lists
        var list1: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5)
        var list2: List<Int> = listOf(1, 2, 3, 4, 5)

        list1.add(6)
        println("1st : ${list1.first()}")
        println("2nd : ${list1[2]}")

        var list3 = list1.subList(0, 3)
        println("Length : ${list1.size}")
        list3.clear()

        list1.remove(0)
        list1.removeAt(1)

        list1.forEach{ n -> println("Mutable List: $n")}

        //Maps
        var map = mutableMapOf<Int, Any?>()
        val map2 = mutableMapOf(1 to "Doug", 2 to 25) //What :D

        map[1] = "Derek"
        map[2] = 42

        println("Map Size: ${map.size}")
        map.put(3, "Pittsburgh")

        map.remove(2)

        for((x, y) in map){
            println("Key : $x Value: $y")
        }

        //Classes
        //Open signifies inheritance is possible
        open class Animal(val name: String, var height: Double, var weight: Double){
            init{
                val regex = Regex(".*\\d+.*")

                require(!name.matches(regex)){"Animal name can't contain numbers"}
                require(height > 0){"Height must be greater than 0"}
                require(weight > 0){"Weight must be greater than 0"}
            }

            open fun getInfo(): Unit
            {
                println("$name is $height tall and weight $weight")
            }
        }

        val bowser = Animal("Boweser", 20.0, 13.5)
        bowser.getInfo()

        //Inheritance
        class Dog(name: String,
                  height: Double,
                  weight: Double,
                  var owner: String) : Animal(name, height, weight){

            override fun getInfo() {
                println("$name is $height tall and weight $weight, and is owned by $owner")
            }
        }

        val spot = Dog("Spot", 20.0, 14.5, "Paul Smith")
        spot.getInfo()

        //Interfaces
        class Bird constructor(val name: String, override var flies: Boolean = true): Flyable{

            override fun fly(distMile: Double) {
                if(flies){
                    println("$name flies $distMile miles")
                }
            }
        }

        val tweety = Bird("Tweety", true)
        tweety.fly(10.0)

        //Null safety
        var nullVal: String? = null
        fun returnNull(): String?{
            return null
        }

        var nullVal3 = returnNull()
        var nullVal4: String = returnNull() ?: "No Name"
    }

    interface Flyable{
        var flies: Boolean
        fun fly(distMile: Double): Unit

    }

    fun makeMathFunc(num1: Int): (Int) -> Int = {num2 -> num1 * num2}

    fun mathOnList(numList: Array<Int>, myFunc: (num: Int) -> Int){
        for(num in numList){
            println("MathOnList ${myFunc(num)}")
        }
    }
}
