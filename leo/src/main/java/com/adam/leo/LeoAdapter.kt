package com.adam.leo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adam.leo.extensions.arrayListOf
import kotlinx.android.extensions.LayoutContainer
import java.util.*


private class LeoAdapterImpl<T>(
    @LayoutRes private val layoutID: Int
) : RecyclerView.Adapter<LiVH>(), LeoAdapter<T> {

    private var _data: List<T> = emptyList()
    private var listener: LeoItemBindListener<T>? = null

    override fun submitList(data: List<T>) {
        if (itemCallback == null) {
            _data = data
            notifyDataSetChanged()
        } else {

            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                override fun getOldListSize(): Int = _data.size

                override fun getNewListSize(): Int = data.size

                override fun areItemsTheSame(op: Int, np: Int): Boolean =
                    itemCallback!!.areItemsTheSame(_data[op], data[np])

                override fun areContentsTheSame(op: Int, np: Int): Boolean =
                    itemCallback!!.areContentsTheSame(_data[op], data[np])
            })

            result.dispatchUpdatesTo(this)
        }
    }

    override fun addList(data: List<T>) {
        val temp: ArrayList<T> = arrayListOf(_data, data)

        if (itemCallback == null) {
            _data = temp
            notifyDataSetChanged()
        } else {

            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                override fun getOldListSize(): Int = _data.size

                override fun getNewListSize(): Int = temp.size

                override fun areItemsTheSame(op: Int, np: Int): Boolean =
                    itemCallback?.areItemsTheSame(_data[op], temp[np]) ?: true

                override fun areContentsTheSame(op: Int, np: Int): Boolean =
                    itemCallback?.areContentsTheSame(_data[op], temp[np]) ?: true
            })

            result.dispatchUpdatesTo(this)
        }
    }

    private var itemCallback: DiffUtil.ItemCallback<T>? = null
    override fun enableDiffUtil(itemCallback: DiffUtil.ItemCallback<T>?) {
        this.itemCallback = itemCallback
    }

    override fun bind(listener: LeoItemBindListener<T>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiVH {
        val view = LayoutInflater.from(parent.context).inflate(layoutID, parent, false)
        return LiVH(view)
    }

    override fun getItemCount(): Int = _data.size

    override fun onBindViewHolder(holder: LiVH, position: Int) {
        listener?.invoke(holder.itemView, position, _data[position])
    }
}

private class LiVH(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer

interface LeoAdapter<T> {

    fun addList(data: List<T>)

    fun submitList(data: List<T>)

    fun bind(listener: LeoItemBindListener<T>)

    fun enableDiffUtil(itemCallback: DiffUtil.ItemCallback<T>?)
}

fun <T> RecyclerView.setupAdapter(@LayoutRes layoutID: Int, body: LeoAdapter<T>.() -> Unit): LeoAdapter<T> {
    val leoAdapter = LeoAdapterImpl<T>(layoutID)
    this.layoutManager = LinearLayoutManager(context)
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}

fun <T> RecyclerView.setupAdapter(
    @LayoutRes layoutID: Int, layoutManager: RecyclerView.LayoutManager,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val leoAdapter = LeoAdapterImpl<T>(layoutID)
    this.layoutManager = layoutManager
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}

typealias LeoItemBindListener<T> = (view: View, position: Int, item: T) -> Unit