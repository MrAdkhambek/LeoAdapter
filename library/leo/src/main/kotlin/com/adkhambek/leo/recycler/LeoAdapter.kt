package com.adkhambek.leo.recycler

import android.view.LayoutInflater
import androidx.recyclerview.widget.AsyncDifferConfig
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
public fun <T : Any, VB : ViewBinding> RecyclerView.setupAdapter(
    viewBinding: LeoItemBinding<VB>,
    layoutManager: LinearLayoutManager = LinearLayoutManager(context),
    inflater: LayoutInflater = LayoutInflater.from(context),
    onBind: LeoItemBindListener<T, VB>
): LeoAdapter<T, VB> {
    val leoAdapter: LeoAdapterSync<T, VB> = LeoAdapterSync(
        inflater = inflater,
        getViewBinding = viewBinding,
        onBind = onBind
    )

    this.layoutManager = layoutManager
    this.adapter = leoAdapter
    return leoAdapter
}

@JvmOverloads
@LeoAdapterDSL
public fun <T : Any, VB : ViewBinding> RecyclerView.setupAdapter(
    viewBinding: LeoItemBinding<VB>,
    diffCallback: DiffUtil.ItemCallback<T>,
    layoutManager: LinearLayoutManager = LinearLayoutManager(context),
    inflater: LayoutInflater = LayoutInflater.from(context),
    onBind: LeoItemBindListener<T, VB>
): LeoAdapter<T, VB> {
    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(
        getViewBinding = viewBinding,
        diffCallback = diffCallback,
        inflater = inflater,
        onBind = onBind
    )

    this.layoutManager = layoutManager
    this.adapter = leoAdapter
    return leoAdapter
}

@JvmOverloads
@LeoAdapterDSL
public fun <T : Any, VB : ViewBinding> RecyclerView.setupAdapter(
    viewBinding: LeoItemBinding<VB>,
    config: AsyncDifferConfig<T>,
    layoutManager: LinearLayoutManager = LinearLayoutManager(context),
    inflater: LayoutInflater = LayoutInflater.from(context),
    onBind: LeoItemBindListener<T, VB>
): LeoAdapter<T, VB> {
    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(
        getViewBinding = viewBinding,
        inflater = inflater,
        config = config,
        onBind = onBind
    )

    this.layoutManager = layoutManager
    this.adapter = leoAdapter
    return leoAdapter
}
