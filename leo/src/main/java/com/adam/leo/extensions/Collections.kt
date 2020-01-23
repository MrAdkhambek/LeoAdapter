package com.adam.leo.extensions

fun <T> arrayListOf(vararg collection: Collection<T>): ArrayList<T> {
    val arr = ArrayList<T>()
    collection.forEach {
        arr.addAll(it)
    }
    return arr
}