package com.adkhambek.leo.recycler

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.adkhambek.leo.DefaultLeoItemBindPayloadListener
import com.adkhambek.leo.LeoAdapterDSL
import com.adkhambek.leo.LeoItemBindListener
import com.adkhambek.leo.LeoItemBindPayloadListener
import com.adkhambek.leo.LeoItemBinding
import com.adkhambek.leo.LeoPagingAdapter
import com.adkhambek.leo.core.LeoPagingDataAdapter

@JvmOverloads
@LeoAdapterDSL
public fun <T : Any, VB : ViewBinding> RecyclerView.createPagingAdapter(
    viewBinding: LeoItemBinding<VB>,
    diffCallback: DiffUtil.ItemCallback<T>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    onBindPayloads: LeoItemBindPayloadListener<T?, VB> = DefaultLeoItemBindPayloadListener(),
    onBind: LeoItemBindListener<T?, VB>,
): LeoPagingAdapter<T, VB> = LeoPagingDataAdapter(
    getViewBinding = viewBinding,
    diffCallback = diffCallback,
    inflater = inflater,
    onBind = onBind,
    onBindPayloads = onBindPayloads,
)
