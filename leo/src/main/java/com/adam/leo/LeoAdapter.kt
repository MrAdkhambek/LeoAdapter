package com.adam.leo

import android.view.View

@DslMarker
annotation class LeoAdapterDsl

interface LeoAdapter<T> {

    fun setList(data: List<T>)

    fun bind(listener: LeoItemBindListener<T>)
}

typealias LeoItemBindListener<T> = (view: View, position: Int, item: T) -> Unit
