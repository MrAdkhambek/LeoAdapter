package com.adam.leo.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adam.leo.LeoAdapter
import com.adam.leo.LeoAdapterDsl
import com.adam.leo.LeoItemBindListener
import kotlinx.android.extensions.LayoutContainer

internal class LeoVH(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer

internal class LeoAdapterSync<T>(
    private val inflater: LayoutInflater,
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
        val view: View = inflater.inflate(layoutID, parent, false)
        return LeoVH(view)
    }

    override fun getItemCount(): Int = _data.size

    override fun onBindViewHolder(holder: LeoVH, position: Int) {
        listener?.invoke(holder.itemView, position, _data[position])
    }
}


internal class LeoAdapterAsync<T>(
    private val inflater: LayoutInflater,
    @LayoutRes private val layoutID: Int,
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, LeoVH>(diffUtil), LeoAdapter<T> {

    private var listener: LeoItemBindListener<T>? = null

    override fun setList(data: List<T>) {
        submitList(ArrayList(data))
    }

    override fun bind(listener: LeoItemBindListener<T>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeoVH {
        val view = inflater.inflate(layoutID, parent, false)
        return LeoVH(view)
    }

    override fun onBindViewHolder(holder: LeoVH, position: Int) {
        val item = getItem(position) ?: return
        listener?.invoke(holder.itemView, position, item)
    }
}

@LeoAdapterDsl
fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    val leoAdapter = LeoAdapterSync<T>(inflater, layoutID)
    this.layoutManager = LinearLayoutManager(context)
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}

@LeoAdapterDsl
fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    layoutManager: RecyclerView.LayoutManager,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    val leoAdapter = LeoAdapterSync<T>(inflater, layoutID)
    this.layoutManager = layoutManager
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}

@LeoAdapterDsl
fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    diffUtil: DiffUtil.ItemCallback<T>,
    layoutManager: RecyclerView.LayoutManager,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    val leoAdapter = LeoAdapterAsync<T>(inflater, layoutID, diffUtil)
    this.layoutManager = layoutManager
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}

@LeoAdapterDsl
fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int,
    diffUtil: DiffUtil.ItemCallback<T>,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    val leoAdapter = LeoAdapterAsync<T>(inflater, layoutID, diffUtil)
    this.layoutManager = LinearLayoutManager(context)
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}