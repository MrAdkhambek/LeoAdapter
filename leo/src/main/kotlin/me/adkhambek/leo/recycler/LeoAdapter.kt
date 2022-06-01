package me.adkhambek.leo.recycler

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import me.adkhambek.leo.LeoAdapter
import me.adkhambek.leo.LeoAdapterDsl
import me.adkhambek.leo.LeoItemBindListener
import me.adkhambek.leo.LeoItemBinding
import me.adkhambek.leo.core.LeoAdapterAsync
import me.adkhambek.leo.core.LeoAdapterSync


@LeoAdapterDsl
fun <T, VB : ViewBinding> RecyclerView.setupAdapter(
    getViewBinding: LeoItemBinding<VB>,
    layoutManager: LinearLayoutManager = LinearLayoutManager(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.layoutManager = layoutManager

    val leoAdapter: LeoAdapterSync<T, VB> = LeoAdapterSync(inflater, getViewBinding, listener)
    this.adapter = leoAdapter

    return leoAdapter
}

@LeoAdapterDsl
fun <T, VB : ViewBinding> RecyclerView.setupAdapter(
    getViewBinding: LeoItemBinding<VB>,
    diffUtil: DiffUtil.ItemCallback<T>,
    layoutManager: LinearLayoutManager = LinearLayoutManager(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.layoutManager = layoutManager

    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(inflater, diffUtil, getViewBinding, listener)
    this.adapter = leoAdapter

    return leoAdapter
}