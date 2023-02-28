@file:JvmSynthetic

package com.adkhambek.leo.core

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.adkhambek.leo.LeoAdapter
import com.adkhambek.leo.LeoItemBindListener
import com.adkhambek.leo.LeoItemBinding

internal class LeoAdapterSync<T, VB : ViewBinding>(
    private val inflater: LayoutInflater,
    private val getViewBinding: LeoItemBinding<VB>,
    private val onBind: LeoItemBindListener<T, VB>
) : RecyclerView.Adapter<BaseVH<VB>>(), LeoAdapter<T> {

    private var _data: List<T> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<VB> {
        val binding = getViewBinding(inflater, parent, false)
        return BaseVH(binding)
    }

    override fun getItemCount(): Int = _data.size

    override fun onBindViewHolder(holder: BaseVH<VB>, position: Int): Unit = onBind(
        holder.viewBinding,
        position,
        _data[position]
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun setList(data: List<T>) {
        _data = data
        notifyDataSetChanged()
    }
}

internal class LeoAdapterAsync<T, VB : ViewBinding>(
    private val inflater: LayoutInflater,
    diffUtil: DiffUtil.ItemCallback<T>,
    private val getViewBinding: LeoItemBinding<VB>,
    private val onBind: LeoItemBindListener<T, VB>
) : ListAdapter<T, BaseVH<VB>>(diffUtil), LeoAdapter<T> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<VB> {
        val binding = getViewBinding(inflater, parent, false)
        return BaseVH(binding)
    }

    override fun setList(data: List<T>): Unit = submitList(data)

    override fun onBindViewHolder(holder: BaseVH<VB>, position: Int): Unit = onBind(
        holder.viewBinding,
        position,
        getItem(position)
    )
}
