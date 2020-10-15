package com.adam.leo.viewpager

import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.widget.ViewPager2
import com.adam.leo.LeoAdapter
import com.adam.leo.LeoAdapterDsl
import com.adam.leo.recycler.LeoAdapterAsync
import com.adam.leo.recycler.LeoAdapterSync


@LeoAdapterDsl
fun <T> ViewPager2.setupAdapter(
    @LayoutRes layoutID: Int,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    val leoAdapter = LeoAdapterSync<T>(inflater, layoutID)
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}

@LeoAdapterDsl
fun <T> ViewPager2.setupAdapter(
    @LayoutRes layoutID: Int,
    @ViewPager2.Orientation orientation: Int,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    val leoAdapter = LeoAdapterSync<T>(inflater, layoutID)
    this.orientation = orientation
    body.invoke(leoAdapter)
    return leoAdapter
}

@LeoAdapterDsl
fun <T> ViewPager2.setupAdapter(
    @LayoutRes layoutID: Int,
    diffUtil: DiffUtil.ItemCallback<T>,
    @ViewPager2.Orientation orientation: Int,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    val leoAdapter = LeoAdapterAsync<T>(inflater, layoutID, diffUtil)
    this.orientation = orientation
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}

@LeoAdapterDsl
fun <T> ViewPager2.setupAdapter(
    @LayoutRes layoutID: Int,
    diffUtil: DiffUtil.ItemCallback<T>,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    val leoAdapter = LeoAdapterAsync<T>(inflater, layoutID, diffUtil)
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}