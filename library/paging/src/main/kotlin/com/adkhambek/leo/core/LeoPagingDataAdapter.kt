@file:JvmSynthetic

package com.adkhambek.leo.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.adkhambek.leo.LeoItemBindListener
import com.adkhambek.leo.LeoItemBinding
import com.adkhambek.leo.LeoPagingAdapter
import com.adkhambek.leo.LeoVH

internal class LeoPagingDataAdapter<T : Any, VB : ViewBinding>(
    private val inflater: LayoutInflater,
    diffCallback: DiffUtil.ItemCallback<T>,
    private val getViewBinding: LeoItemBinding<VB>,
    private val onBind: LeoItemBindListener<T?, VB>
) : LeoPagingAdapter<T, VB>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeoVH<VB> {
        val binding = getViewBinding(inflater, parent, false)
        return LeoVH(binding)
    }

    override fun onBindViewHolder(holder: LeoVH<VB>, position: Int): Unit = onBind(
        holder.viewBinding,
        position,
        getItem(position)
    )
}
