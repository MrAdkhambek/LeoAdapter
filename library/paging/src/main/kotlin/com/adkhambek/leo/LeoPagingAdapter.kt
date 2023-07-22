package com.adkhambek.leo

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding

public abstract class LeoPagingAdapter<T : Any, VB : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<T>,
) : PagingDataAdapter<T, LeoVH<VB>>(diffCallback)
