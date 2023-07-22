package com.adkhambek.leo

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

public abstract class LeoAdapter<T : Any, VB : ViewBinding> : RecyclerView.Adapter<LeoVH<VB>>() {

    @LeoAdapterDSL
    public abstract fun setList(data: List<T>)
}
