package com.adam.leo

import android.view.View
import androidx.annotation.IdRes

@DslMarker
annotation class LeoAdapterDsl


interface LeoAdapter<T> {

    @LeoAdapterDsl
    fun setList(data: List<T>)
}

interface LeoAdapterScope<T> {

    @LeoAdapterDsl
    fun bind(listener: LeoItemBindListener<T>)

    @LeoAdapterDsl
    fun <V : View> findViewById(@IdRes id: Int): V
}

typealias LeoItemBindListener<T> = (view: View, position: Int, item: T) -> Unit
