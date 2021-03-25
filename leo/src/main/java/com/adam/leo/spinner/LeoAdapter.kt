package com.adam.leo.spinner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Spinner
import androidx.viewbinding.ViewBinding
import com.adam.leo.LeoAdapter
import com.adam.leo.LeoAdapterDsl
import com.adam.leo.LeoItemBindListener
import com.adam.leo.LeoItemBinding
import com.adam.leo.core.BaseVH


internal class LeoAdapter<T, VB : ViewBinding>(
    private val inflater: LayoutInflater,
    private var data: List<T> = arrayListOf(),
    private val getViewBinding: LeoItemBinding<VB>,
    private val listener: LeoItemBindListener<T, VB>
) : BaseAdapter(), LeoAdapter<T> {

    override fun getCount(): Int = data.size
    override fun getItem(position: Int): T = data[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item: T = getItem(position)

        val binding: VB = getViewBinding(inflater, parent, false)
        val view: View = convertView ?: binding.root

        @Suppress("UNCHECKED_CAST")
        val holder: BaseVH<T, VB> = (view.tag as? BaseVH<T, VB>?) ?: run {
            val tempHolder = BaseVH(binding, listener)
            view.tag = tempHolder
            tempHolder
        }

        holder(position, item)
        return view
    }

    override fun setList(data: List<T>) {
        this.data = ArrayList(data)
        notifyDataSetChanged()
    }
}


@LeoAdapterDsl
fun <T, VB : ViewBinding> Spinner.setupAdapter(
    getViewBinding: LeoItemBinding<VB>,
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> = setupAdapter(arrayListOf(), getViewBinding, listener)

@LeoAdapterDsl
fun <T, VB : ViewBinding> Spinner.setupAdapter(
    data: List<T>,
    getViewBinding: LeoItemBinding<VB>,
    listener: LeoItemBindListener<T, VB>
): LeoAdapter<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)

    val leoAdapter = LeoAdapter(inflater, data, getViewBinding, listener)
    this.adapter = leoAdapter

    return leoAdapter
}