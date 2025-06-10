package com.adkhambek.leo

import androidx.annotation.RestrictTo

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class DefaultLeoItemBindPayloadListener<T, VB> : (VB, Int, T?, MutableList<Any>) -> Unit {
    override fun invoke(binding: VB, position: Int, item: T?, payloads: MutableList<Any>): Unit = Unit
}