package com.adkhambek.leo.recycler

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.adkhambek.leo.LeoAdapterDSL
import com.adkhambek.leo.LeoItemBindListener
import com.adkhambek.leo.LeoItemBinding
import com.adkhambek.leo.LeoPagingAdapter
import com.adkhambek.leo.core.LeoPagingDataAdapter

@JvmOverloads
@LeoAdapterDSL
public fun <T : Any, VB : ViewBinding> RecyclerView.setupPagingAdapter(
    viewBinding: LeoItemBinding<VB>,
    diffCallback: DiffUtil.ItemCallback<T>,
    layoutManager: LinearLayoutManager = LinearLayoutManager(context),
    inflater: LayoutInflater = LayoutInflater.from(context),
    onBind: LeoItemBindListener<T?, VB>
): LeoPagingAdapter<T, VB> {
    val leoAdapter: LeoPagingDataAdapter<T, VB> = LeoPagingDataAdapter(
        getViewBinding = viewBinding,
        diffCallback = diffCallback,
        inflater = inflater,
        onBind = onBind
    )

    this.layoutManager = layoutManager
    this.adapter = leoAdapter
    return leoAdapter
}
