package com.adam.leo.viewpager

import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.widget.ViewPager2
import com.adam.leo.LeoAdapter
import com.adam.leo.LeoAdapterDsl
import com.adam.leo.LeoAdapterScope
import com.adam.leo.core.LeoAdapterAsync
import com.adam.leo.core.LeoAdapterSync


@LeoAdapterDsl
fun <T> ViewPager2.setupAdapter(
    @LayoutRes layoutID: Int,
    body: LeoAdapterScope<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)

    val leoAdapter: LeoAdapterSync<T> = LeoAdapterSync(inflater, layoutID, body)
    this.adapter = leoAdapter

    return leoAdapter
}


@LeoAdapterDsl
fun <T> ViewPager2.setupAdapter(
    @LayoutRes layoutID: Int,
    @ViewPager2.Orientation orientation: Int,
    body: LeoAdapterScope<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.orientation = orientation

    val leoAdapter: LeoAdapterSync<T> = LeoAdapterSync(inflater, layoutID, body)
    this.adapter = leoAdapter

    return leoAdapter
}

@LeoAdapterDsl
fun <T> ViewPager2.setupAdapter(
    @LayoutRes layoutID: Int,
    diffUtil: DiffUtil.ItemCallback<T>,
    @ViewPager2.Orientation orientation: Int,
    body: LeoAdapterScope<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.orientation = orientation

    val leoAdapter: LeoAdapterAsync<T> = LeoAdapterAsync(inflater, layoutID, diffUtil, body)
    this.adapter = leoAdapter

    return leoAdapter
}

@LeoAdapterDsl
fun <T> ViewPager2.setupAdapter(
    @LayoutRes layoutID: Int,
    diffUtil: DiffUtil.ItemCallback<T>,
    body: LeoAdapterScope<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)

    val leoAdapter: LeoAdapterAsync<T> = LeoAdapterAsync(inflater, layoutID, diffUtil, body)
    this.adapter = leoAdapter

    return leoAdapter
}