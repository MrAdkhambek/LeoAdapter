package com.adkhambek.leo.recycler

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.adkhambek.leo.LeoAdapter
import com.adkhambek.leo.LeoAdapterDSL
import com.adkhambek.leo.LeoItemBindListener
import com.adkhambek.leo.LeoItemBinding
import com.adkhambek.leo.core.LeoAdapterAsync
import com.adkhambek.leo.core.LeoAdapterSync

@JvmOverloads
@LeoAdapterDSL
public fun <T, VB : ViewBinding> RecyclerView.setupAdapter(
    getViewBinding: LeoItemBinding<VB>,
    layoutManager: LinearLayoutManager = LinearLayoutManager(context),
    inflater: LayoutInflater = LayoutInflater.from(context),
    onBind: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val leoAdapter: LeoAdapterSync<T, VB> = LeoAdapterSync(
        inflater = inflater,
        getViewBinding = getViewBinding,
        onBind = onBind
    )

    this.layoutManager = layoutManager
    this.adapter = leoAdapter
    return leoAdapter
}

@JvmOverloads
@LeoAdapterDSL
public fun <T, VB : ViewBinding> RecyclerView.setupAdapter(
    getViewBinding: LeoItemBinding<VB>,
    diffUtil: DiffUtil.ItemCallback<T>,
    layoutManager: LinearLayoutManager = LinearLayoutManager(context),
    inflater: LayoutInflater = LayoutInflater.from(context),
    onBind: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(
        inflater = inflater,
        diffUtil = diffUtil,
        getViewBinding = getViewBinding,
        onBind = onBind
    )

    this.layoutManager = layoutManager
    this.adapter = leoAdapter
    return leoAdapter
}