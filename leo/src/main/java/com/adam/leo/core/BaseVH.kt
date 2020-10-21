package com.adam.leo.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer


internal abstract class BaseVH(
    containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    abstract fun onBind()
}