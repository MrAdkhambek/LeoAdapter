package com.adam.leo.spinner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Spinner
import androidx.annotation.LayoutRes
import com.adam.leo.LeoAdapter
import com.adam.leo.LeoAdapterDsl
import com.adam.leo.LeoItemBindListener


internal class LeoAdapter<T>(
    private val inflater: LayoutInflater,
    @LayoutRes private val layoutID: Int,
    private var data: List<T> = arrayListOf()
) : BaseAdapter(), LeoAdapter<T> {

    private var listener: LeoItemBindListener<T>? = null

    override fun getCount(): Int = data.size
    override fun getItem(position: Int): T = data[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(layoutID, parent, false)

        val item: T = getItem(position)
        listener?.invoke(view, position, item)

        return view
    }

    override fun setList(data: List<T>) {
        this.data = ArrayList(data)
        notifyDataSetChanged()
    }

    override fun bind(listener: LeoItemBindListener<T>) {
        this.listener = listener
    }
}


@LeoAdapterDsl
fun <T> Spinner.setupAdapter(
    @LayoutRes layoutID: Int,
    body: LeoAdapter<T>.() -> Unit
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    val leoAdapter = LeoAdapter<T>(inflater, layoutID)
    this.adapter = leoAdapter
    body.invoke(leoAdapter)
    return leoAdapter
}