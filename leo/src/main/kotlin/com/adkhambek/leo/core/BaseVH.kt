@file:JvmSynthetic

package com.adkhambek.leo.core

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

internal class BaseVH<VB : ViewBinding>(
    internal val viewBinding: VB,
) : RecyclerView.ViewHolder(viewBinding.root)