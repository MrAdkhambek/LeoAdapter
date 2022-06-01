package me.adkhambek.leo

import android.view.LayoutInflater
import android.view.ViewGroup


@DslMarker
annotation class LeoAdapterDsl

interface LeoAdapter<T> {

    @LeoAdapterDsl
    fun setList(data: List<T>)
}

typealias LeoItemBinding<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB
typealias LeoItemBindListener<T, VB> = (binding: VB, position: Int, item: T) -> Unit
