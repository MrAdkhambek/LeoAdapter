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


@JvmOverloads
@LeoAdapterDsl
fun <T, VB : ViewBinding> RecyclerView.setupAdapter(
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
@LeoAdapterDsl
fun <T, VB : ViewBinding> RecyclerView.setupAdapter(
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