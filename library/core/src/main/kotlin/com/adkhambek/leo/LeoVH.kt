@file:JvmSynthetic

package com.adkhambek.leo

import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

@RestrictTo(Scope.LIBRARY_GROUP)
public class LeoVH<VB : ViewBinding>(
    public val viewBinding: VB,
) : RecyclerView.ViewHolder(viewBinding.root)
