package com.adam.leo.recycler

import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adam.leo.LeoAdapter
import com.adam.leo.LeoAdapterDsl
import com.adam.leo.LeoAdapterScope
import com.adam.leo.core.LeoAdapterAsync
import com.adam.leo.core.LeoAdapterSync


@LeoAdapterDsl
fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    body: LeoAdapterScope<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.layoutManager = LinearLayoutManager(context)

    val leoAdapter: LeoAdapterSync<T> = LeoAdapterSync(inflater, layoutID, body)
    this.adapter = leoAdapter

    return leoAdapter
}

@LeoAdapterDsl
fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    layoutManager: RecyclerView.LayoutManager,
    body: LeoAdapterScope<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.layoutManager = layoutManager

    val leoAdapter: LeoAdapterSync<T> = LeoAdapterSync(inflater, layoutID, body)
    this.adapter = leoAdapter

    return leoAdapter
}

@LeoAdapterDsl
fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    diffUtil: DiffUtil.ItemCallback<T>,
    layoutManager: RecyclerView.LayoutManager,
    body: LeoAdapterScope<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.layoutManager = layoutManager

    val leoAdapter: LeoAdapterAsync<T> = LeoAdapterAsync(inflater, layoutID, diffUtil, body)
    this.adapter = leoAdapter

    return leoAdapter
}

@LeoAdapterDsl
fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    diffUtil: DiffUtil.ItemCallback<T>,
    body: LeoAdapterScope<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.layoutManager = LinearLayoutManager(context)

    val leoAdapter: LeoAdapterAsync<T> = LeoAdapterAsync(inflater, layoutID, diffUtil, body)
    this.adapter = leoAdapter

    return leoAdapter
}