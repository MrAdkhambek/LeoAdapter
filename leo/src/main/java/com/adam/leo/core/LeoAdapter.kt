package com.adam.leo.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adam.leo.LeoAdapter
import com.adam.leo.LeoAdapterScope
import com.adam.leo.LeoItemBindListener


internal class LeoAdapterSync<T>(
    private val inflater: LayoutInflater,
    @LayoutRes private val layoutID: Int,
    private val holderDelegate: LeoAdapterScope<T>.() -> Unit
) : RecyclerView.Adapter<BaseVH>(), LeoAdapter<T> {

    private var _data: List<T> = emptyList()

    internal inner class DelegateVH(
        override val containerView: View
    ) : BaseVH(containerView), LeoAdapterScope<T> {

        private var listener: LeoItemBindListener<T>? = null

        override fun onBind() {
            listener?.invoke(containerView, adapterPosition, _data[adapterPosition])
        }

        override fun <V : View> findViewById(id: Int): V = containerView.findViewById(id)

        override fun bind(listener: LeoItemBindListener<T>) {
            this.listener = listener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DelegateVH {
        val view: View = inflater.inflate(layoutID, parent, false)
        return DelegateVH(view).apply(holderDelegate)
    }

    override fun getItemCount(): Int = _data.size
    override fun onBindViewHolder(holder: BaseVH, position: Int): Unit = holder.onBind()

    override fun setList(data: List<T>) {
        _data = data
        notifyDataSetChanged()
    }
}

internal class LeoAdapterAsync<T>(
    private val inflater: LayoutInflater,
    @LayoutRes private val layoutID: Int,
    diffUtil: DiffUtil.ItemCallback<T>,
    private val holderDelegate: LeoAdapterScope<T>.() -> Unit
) : ListAdapter<T, BaseVH>(diffUtil), LeoAdapter<T> {

    internal inner class LeoVH(
        override val containerView: View
    ) : BaseVH(containerView), LeoAdapterScope<T> {

        private var listener: LeoItemBindListener<T>? = null

        override fun onBind() {
            val position = adapterPosition
            listener?.invoke(containerView, position, getItem(position))
        }

        override fun <V : View> findViewById(id: Int): V = containerView.findViewById(id)

        override fun bind(listener: LeoItemBindListener<T>) {
            this.listener = listener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeoVH {
        val view: View = inflater.inflate(layoutID, parent, false)
        return LeoVH(view).apply(holderDelegate)
    }

    override fun setList(data: List<T>): Unit = submitList(data)
    override fun onBindViewHolder(holder: BaseVH, position: Int): Unit = holder.onBind()
}