package com.adam.leoadapterapp.data

import java.util.*

data class Person(
    val id: Int,
    val age: Int,
    val name: String
)

val data: ArrayList<Person> = arrayListOf(
    Person(1, 21, "Adam"),
    Person(6, 21, "Brus"),
    Person(2, 53, "Peter"),
    Person(3, 54, "John"),
    Person(4, 34, "Artur"),
    Person(5, 3, "Joseph")
)

val colors: Array<Int> = arrayOf(
    android.R.color.holo_blue_bright,
    android.R.color.holo_blue_dark,
    android.R.color.holo_green_light,
    android.R.color.holo_red_light,
    android.R.color.holo_purple
)