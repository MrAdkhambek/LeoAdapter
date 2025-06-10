package com.adkhambek.leo

import android.view.LayoutInflater
import android.view.ViewGroup

public typealias LeoItemBinding<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB
public typealias LeoItemBindListener<T, VB> = (binding: VB, position: Int, item: T) -> Unit
public typealias LeoItemBindPayloadListener<T, VB> = (binding: VB, position: Int, item: T, payloads: MutableList<Any>) -> Unit
