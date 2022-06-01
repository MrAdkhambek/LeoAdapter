package me.adkhambek.leo.core

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import me.adkhambek.leo.LeoItemBindListener


internal class BaseVH<T, VB : ViewBinding>(
    private val viewBinding: VB,
    private val listener: LeoItemBindListener<T, VB>
) : RecyclerView.ViewHolder(viewBinding.root) {

    operator fun invoke(position: Int, item: T) = listener.invoke(viewBinding, position, item)
}