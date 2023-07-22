package com.adkhambek.leo.viewpager

import android.view.LayoutInflater
import androidx.recyclerview.widget.AsyncDifferConfig
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
public fun <T : Any, VB : ViewBinding> ViewPager2.setupAdapter(
    viewBinding: LeoItemBinding<VB>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T, VB> {
    val leoAdapter: LeoAdapterSync<T, VB> = LeoAdapterSync(
        inflater = inflater,
        getViewBinding = viewBinding,
        onBind = listener
    )

    this.adapter = leoAdapter
    return leoAdapter
}

@JvmOverloads
@LeoAdapterDSL
public fun <T : Any, VB : ViewBinding> ViewPager2.setupAdapter(
    @Orientation orientation: Int,
    viewBinding: LeoItemBinding<VB>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T, VB> {
    val leoAdapter: LeoAdapterSync<T, VB> = LeoAdapterSync(
        inflater = inflater,
        getViewBinding = viewBinding,
        onBind = listener
    )

    this.orientation = orientation
    this.adapter = leoAdapter
    return leoAdapter
}

@JvmOverloads
@LeoAdapterDSL
public fun <T : Any, VB : ViewBinding> ViewPager2.setupAdapter(
    viewBinding: LeoItemBinding<VB>,
    diffCallback: DiffUtil.ItemCallback<T>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T, VB> {
    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(
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
public fun <T : Any, VB : ViewBinding> ViewPager2.setupAdapter(
    @Orientation orientation: Int,
    viewBinding: LeoItemBinding<VB>,
    diffCallback: DiffUtil.ItemCallback<T>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T, VB> {
    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(
        getViewBinding = viewBinding,
        diffCallback = diffCallback,
        inflater = inflater,
        onBind = listener
    )

    this.orientation = orientation
    this.adapter = leoAdapter
    return leoAdapter
}

@JvmOverloads
@LeoAdapterDSL
public fun <T : Any, VB : ViewBinding> ViewPager2.setupAdapter(
    viewBinding: LeoItemBinding<VB>,
    config: AsyncDifferConfig<T>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T, VB> {
    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(
        getViewBinding = viewBinding,
        inflater = inflater,
        onBind = listener,
        config = config,
    )

    this.adapter = leoAdapter
    return leoAdapter
}

@JvmOverloads
@LeoAdapterDSL
public fun <T : Any, VB : ViewBinding> ViewPager2.setupAdapter(
    @Orientation orientation: Int,
    viewBinding: LeoItemBinding<VB>,
    config: AsyncDifferConfig<T>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T, VB> {
    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(
        getViewBinding = viewBinding,
        inflater = inflater,
        onBind = listener,
        config = config,
    )

    this.orientation = orientation
    this.adapter = leoAdapter
    return leoAdapter
}
