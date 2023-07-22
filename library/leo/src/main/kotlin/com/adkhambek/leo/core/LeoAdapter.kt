@file:JvmSynthetic

package com.adkhambek.leo.core

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.adkhambek.leo.LeoAdapter
import com.adkhambek.leo.LeoItemBindListener
import com.adkhambek.leo.LeoItemBinding
import com.adkhambek.leo.LeoVH

internal class LeoAdapterSync<T : Any, VB : ViewBinding>(
    private val inflater: LayoutInflater,
    private val getViewBinding: LeoItemBinding<VB>,
    private val onBind: LeoItemBindListener<T, VB>
) : LeoAdapter<T, VB>() {

    private var mData: List<T> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeoVH<VB> {
        val binding = getViewBinding(inflater, parent, false)
        return LeoVH(binding)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: LeoVH<VB>, position: Int): Unit = onBind(
        holder.viewBinding,
        position,
        mData[position]
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun setList(data: List<T>) {
        mData = data
        notifyDataSetChanged()
    }
}

internal class LeoAdapterAsync<T : Any, VB : ViewBinding> : LeoAdapter<T, VB> {

    private val inflater: LayoutInflater
    private val mDiffer: AsyncListDiffer<T>
    private val getViewBinding: LeoItemBinding<VB>
    private val onBind: LeoItemBindListener<T, VB>

    constructor(
        inflater: LayoutInflater,
        diffCallback: DiffUtil.ItemCallback<T>,
        getViewBinding: LeoItemBinding<VB>,
        onBind: LeoItemBindListener<T, VB>
    ) {
        this.getViewBinding = getViewBinding
        this.inflater = inflater
        this.onBind = onBind

        this.mDiffer = AsyncListDiffer<T>(
            AdapterListUpdateCallback(this),
            AsyncDifferConfig.Builder<T>(diffCallback).build()
        )
    }

    constructor(
        inflater: LayoutInflater,
        config: AsyncDifferConfig<T>,
        getViewBinding: LeoItemBinding<VB>,
        onBind: LeoItemBindListener<T, VB>
    ) {
        this.getViewBinding = getViewBinding
        this.inflater = inflater
        this.onBind = onBind

        this.mDiffer = AsyncListDiffer<T>(
            AdapterListUpdateCallback(this),
            config
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeoVH<VB> {
        val binding = getViewBinding(inflater, parent, false)
        return LeoVH(binding)
    }

    override fun getItemCount(): Int = mDiffer.currentList.size
    override fun setList(data: List<T>): Unit = mDiffer.submitList(data)
    private fun getItem(position: Int): T = mDiffer.currentList[position]

    override fun onBindViewHolder(holder: LeoVH<VB>, position: Int): Unit = onBind(
        holder.viewBinding,
        position,
        getItem(position)
    )
}
