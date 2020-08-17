package com.adam.leo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.android.extensions.LayoutContainer


internal class LeoAdapterSync<T>(
    @LayoutRes private val layoutID: Int
) : RecyclerView.Adapter<LeoVH>(), LeoAdapter<T> {

    private var _data: List<T> = emptyList()
    private var listener: LeoItemBindListener<T>? = null

    override fun setList(data: List<T>) {
        _data = data
        notifyDataSetChanged()
    }

    override fun bind(listener: LeoItemBindListener<T>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeoVH {
        val view = LayoutInflater.from(parent.context).inflate(layoutID, parent, false)
        return LeoVH(view)
    }

    override fun getItemCount(): Int = _data.size

    override fun onBindViewHolder(holder: LeoVH, position: Int) {
        listener?.invoke(holder.itemView, position, _data[position])
    }
}


internal class LeoAdapterAsync<T>(
    @LayoutRes private val layoutID: Int,
    diffUtil: ItemCallback<T>
) : ListAdapter<T, LeoVH>(diffUtil), LeoAdapter<T> {

    private var _data: List<T> = emptyList()
    private var listener: LeoItemBindListener<T>? = null

    override fun setList(data: List<T>) {
        submitList(data)
    }

    override fun bind(listener: LeoItemBindListener<T>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeoVH {
        val view = LayoutInflater.from(parent.context).inflate(layoutID, parent, false)
        return LeoVH(view)
    }

    override fun getItemCount(): Int = _data.size

    override fun onBindViewHolder(holder: LeoVH, position: Int) {
        listener?.invoke(holder.itemView, position, _data[position])
    }
}


interface LeoAdapter<T> {

    fun setList(data: List<T>)

    fun bind(listener: LeoItemBindListener<T>)
}

fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    this.layoutManager = LinearLayoutManager(context)
    val leoAdapter = LeoAdapterSync<T>(layoutID)
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}

fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    layoutManager: LayoutManager,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val leoAdapter = LeoAdapterSync<T>(layoutID)
    this.layoutManager = layoutManager
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}

fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    diffUtil: ItemCallback<T>,
    layoutManager: LayoutManager,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val leoAdapter = LeoAdapterAsync<T>(layoutID, diffUtil)
    this.layoutManager = layoutManager
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}

fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    diffUtil: ItemCallback<T>,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    this.layoutManager = LinearLayoutManager(context)
    val leoAdapter = LeoAdapterAsync<T>(layoutID, diffUtil)
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}

typealias LeoItemBindListener<T> = (view: View, position: Int, item: T) -> Unit

internal class LeoVH(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
