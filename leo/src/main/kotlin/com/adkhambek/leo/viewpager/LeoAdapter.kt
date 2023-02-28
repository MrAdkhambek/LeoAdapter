package com.adkhambek.leo.viewpager

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.Orientation
import com.adkhambek.leo.LeoAdapter
import com.adkhambek.leo.LeoAdapterDSL
import com.adkhambek.leo.LeoItemBindListener
import com.adkhambek.leo.LeoItemBinding
import com.adkhambek.leo.core.LeoAdapterAsync
import com.adkhambek.leo.core.LeoAdapterSync

@JvmOverloads
@LeoAdapterDSL
public fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
    getViewBinding: LeoItemBinding<VB>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val leoAdapter: LeoAdapterSync<T, VB> = LeoAdapterSync(
        inflater = inflater,
        getViewBinding = getViewBinding,
        onBind = listener
    )

    this.adapter = leoAdapter
    return leoAdapter
}

@JvmOverloads
@LeoAdapterDSL
public fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
    @Orientation orientation: Int,
    getViewBinding: LeoItemBinding<VB>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val leoAdapter: LeoAdapterSync<T, VB> = LeoAdapterSync(
        inflater = inflater,
        getViewBinding = getViewBinding,
        onBind = listener
    )

    this.orientation = orientation
    this.adapter = leoAdapter
    return leoAdapter
}

@JvmOverloads
@LeoAdapterDSL
public fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
    getViewBinding: LeoItemBinding<VB>,
    diffUtil: DiffUtil.ItemCallback<T>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(
        inflater = inflater,
        diffUtil = diffUtil,
        getViewBinding = getViewBinding,
        onBind = listener
    )

    this.adapter = leoAdapter
    return leoAdapter
}

@JvmOverloads
@LeoAdapterDSL
public fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
    @Orientation orientation: Int,
    getViewBinding: LeoItemBinding<VB>,
    diffUtil: DiffUtil.ItemCallback<T>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(
        inflater = inflater,
        diffUtil = diffUtil,
        getViewBinding = getViewBinding,
        onBind = listener
    )

    this.orientation = orientation
    this.adapter = leoAdapter
    return leoAdapter
}