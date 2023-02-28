package com.adkhambek.leo

import android.view.LayoutInflater
import android.view.ViewGroup

@DslMarker
public annotation class LeoAdapterDSL

public interface LeoAdapter<T> {

    @LeoAdapterDSL
    public fun setList(data: List<T>)
}

public typealias LeoItemBinding<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB
public typealias LeoItemBindListener<T, VB> = (binding: VB, position: Int, item: T) -> Unit
