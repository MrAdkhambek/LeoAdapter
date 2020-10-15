package com.adam.leoadapterapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.adam.leo.LeoAdapter
import com.adam.leo.recycler.setupAdapter
import com.adam.leoadapterapp.data.Person
import com.adam.leoadapterapp.data.colors
import com.adam.leoadapterapp.data.data
import kotlinx.android.synthetic.main.activity_recycler.*


class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val leoAdapter: LeoAdapter<Person> = recycler.setupAdapter(
            R.layout.recycler_item,
            DIFF_UTIL,
            LinearLayoutManager(recycler.context)
        ) {
            bind { view, index, item ->
                view.findViewById<TextView>(R.id.textView).apply {
                    text = item.name
                    setBackgroundResource(colors[index % colors.size])
                }
            }
        }

        leoAdapter.setList(data.shuffled())
        fab.setOnClickListener {
            leoAdapter.setList(data.shuffled())
        }
    }
}

val DIFF_UTIL: DiffUtil.ItemCallback<Person> = object : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem == newItem
}