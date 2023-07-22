package com.adkhambek.leo.viewpager

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.Orientation
import com.adkhambek.leo.LeoAdapterDSL
import com.adkhambek.leo.LeoItemBindListener
import com.adkhambek.leo.LeoItemBinding
import com.adkhambek.leo.LeoPagingAdapter
import com.adkhambek.leo.core.LeoPagingDataAdapter

@JvmOverloads
@LeoAdapterDSL
public fun <T : Any, VB : ViewBinding> ViewPager2.setupPagingAdapter(
    viewBinding: LeoItemBinding<VB>,
    diffCallback: DiffUtil.ItemCallback<T>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T?, VB>
): LeoPagingAdapter<T, VB> {
    val leoAdapter: LeoPagingDataAdapter<T, VB> = LeoPagingDataAdapter(
        getViewBinding = viewBinding,
        diffCallback = diffCallback,
        inflater = inflater,
        onBind = listener
    )

    this.adapter = leoAdapter
    return leoAdapter
}

@JvmOverloads
@LeoAdapterDSL
public fun <T : Any, VB : ViewBinding> ViewPager2.setupPagingAdapter(
    @Orientation orientation: Int,
    viewBinding: LeoItemBinding<VB>,
    diffCallback: DiffUtil.ItemCallback<T>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T?, VB>
): LeoPagingAdapter<T, VB> {
    val leoAdapter: LeoPagingDataAdapter<T, VB> = LeoPagingDataAdapter(
        getViewBinding = viewBinding,
        diffCallback = diffCallback,
        inflater = inflater,
        onBind = listener
    )

    this.orientation = orientation
    this.adapter = leoAdapter
    return leoAdapter
}
