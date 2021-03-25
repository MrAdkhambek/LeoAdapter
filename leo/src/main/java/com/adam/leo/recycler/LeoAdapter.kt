package com.adam.leo.recycler

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.adam.leo.LeoAdapter
import com.adam.leo.LeoAdapterDsl
import com.adam.leo.LeoItemBindListener
import com.adam.leo.LeoItemBinding
import com.adam.leo.core.LeoAdapterAsync
import com.adam.leo.core.LeoAdapterSync


@LeoAdapterDsl
fun <T, VB : ViewBinding> RecyclerView.setupAdapter(
    getViewBinding: LeoItemBinding<VB>,
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.layoutManager = LinearLayoutManager(context)

    val leoAdapter: LeoAdapterSync<T, VB> = LeoAdapterSync(inflater, getViewBinding, listener)
    this.adapter = leoAdapter

    return leoAdapter
}

@LeoAdapterDsl
fun <T, VB : ViewBinding> RecyclerView.setupAdapter(
    getViewBinding: LeoItemBinding<VB>,
    diffUtil: DiffUtil.ItemCallback<T>,
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.layoutManager = LinearLayoutManager(context)

    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(inflater, diffUtil, getViewBinding, listener)
    this.adapter = leoAdapter

    return leoAdapter
}