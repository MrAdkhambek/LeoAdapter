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


@LeoAdapterDsl
fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
    getViewBinding: LeoItemBinding<VB>,
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val inflater: LayoutInflater = LayoutInflater.from(context)

    val leoAdapter: LeoAdapterSync<T, VB> = LeoAdapterSync(inflater, getViewBinding, listener)
    this.adapter = leoAdapter

    return leoAdapter
}


@LeoAdapterDsl
fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
    @Orientation orientation: Int,
    getViewBinding: LeoItemBinding<VB>,
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.orientation = orientation

    val leoAdapter: LeoAdapterSync<T, VB> = LeoAdapterSync(inflater, getViewBinding, listener)
    this.adapter = leoAdapter

    return leoAdapter
}

@LeoAdapterDsl
fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
    getViewBinding: LeoItemBinding<VB>,
    diffUtil: DiffUtil.ItemCallback<T>,
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val inflater: LayoutInflater = LayoutInflater.from(context)

    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(inflater, diffUtil, getViewBinding, listener)
    this.adapter = leoAdapter

    return leoAdapter
}

@LeoAdapterDsl
fun <T, VB : ViewBinding> ViewPager2.setupAdapter(
    @Orientation orientation: Int,
    getViewBinding: LeoItemBinding<VB>,
    diffUtil: DiffUtil.ItemCallback<T>,
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {

    val inflater: LayoutInflater = LayoutInflater.from(context)
    this.orientation = orientation

    val leoAdapter: LeoAdapterAsync<T, VB> = LeoAdapterAsync(inflater, diffUtil, getViewBinding, listener)
    this.adapter = leoAdapter

    return leoAdapter
}