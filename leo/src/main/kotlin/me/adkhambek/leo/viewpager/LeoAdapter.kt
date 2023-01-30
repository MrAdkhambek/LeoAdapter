package me.adkhambek.leo.viewpager

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.Orientation
import me.adkhambek.leo.LeoAdapter
import me.adkhambek.leo.LeoAdapterDsl
import me.adkhambek.leo.LeoItemBindListener
import me.adkhambek.leo.LeoItemBinding
import me.adkhambek.leo.core.LeoAdapterAsync
import me.adkhambek.leo.core.LeoAdapterSync


@JvmOverloads
@LeoAdapterDsl
fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
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
@LeoAdapterDsl
fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
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
@LeoAdapterDsl
fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
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
@LeoAdapterDsl
fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
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