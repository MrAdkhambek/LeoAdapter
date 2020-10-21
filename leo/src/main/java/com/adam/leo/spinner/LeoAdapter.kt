package com.adam.leo.spinner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Spinner
import androidx.annotation.LayoutRes
import com.adam.leo.LeoAdapter
import com.adam.leo.LeoAdapterDsl
import com.adam.leo.LeoAdapterScope
import com.adam.leo.LeoItemBindListener


internal class LeoAdapter<T>(
    private val inflater: LayoutInflater,
    @LayoutRes private val layoutID: Int,
    private var data: List<T> = arrayListOf(),
    private val holderDelegate: LeoAdapterScope<T>.() -> Unit
) : BaseAdapter(), LeoAdapter<T> {


    override fun getCount(): Int = data.size
    override fun getItem(position: Int): T = data[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item: T = getItem(position)

        val view: View = convertView ?: inflater.inflate(layoutID, parent, false)
        val holder: LeoVH<T> = (view.tag as? LeoVH<T>?) ?: run {
            val tempHolder = object : LeoVH<T>() {

                private var listener: LeoItemBindListener<T>? = null

                override fun <V : View> findViewById(id: Int): V = view.findViewById(id)

                override fun bind(listener: LeoItemBindListener<T>) {
                    this.listener = listener
                }

                override fun onBind() {
                    listener?.invoke(view, position, item)
                }

            }.apply(holderDelegate)

            view.tag = tempHolder
            tempHolder
        }

        holder.onBind()
        return view
    }

    override fun setList(data: List<T>) {
        this.data = ArrayList(data)
        notifyDataSetChanged()
    }

    abstract inner class LeoVH<T> : LeoAdapterScope<T> {
        abstract fun onBind()
        abstract override fun <V : View> findViewById(id: Int): V
        abstract override fun bind(listener: LeoItemBindListener<T>)
    }
}


@LeoAdapterDsl
fun <T> Spinner.setupAdapter(
    @LayoutRes layoutID: Int,
    body: LeoAdapterScope<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)

    val leoAdapter = LeoAdapter(inflater, layoutID, arrayListOf(), body)
    this.adapter = leoAdapter

    return leoAdapter
}

@LeoAdapterDsl
fun <T> Spinner.setupAdapter(
    @LayoutRes layoutID: Int,
    data: List<T>,
    body: LeoAdapterScope<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)

    val leoAdapter = LeoAdapter(inflater, layoutID, data, body)
    this.adapter = leoAdapter

    return leoAdapter
}