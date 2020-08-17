package com.adam.leoadapterapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.adam.leo.LeoAdapter
import com.adam.leo.setupAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var leoAdapter: LeoAdapter<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data: ArrayList<Person> = arrayListOf(
            Person(1, 21, "Adam"),
            Person(6, 21, "Adam"),
            Person(2, 53, "Peter"),
            Person(3, 54, "John"),
            Person(4, 34, "Artur"),
            Person(5, 3, "Joseph")
        )

        val color: Array<Int> = arrayOf(
            android.R.color.holo_blue_bright,
            android.R.color.holo_blue_dark,
            android.R.color.holo_green_light,
            android.R.color.holo_red_light,
            android.R.color.holo_purple
        )

        leoAdapter = recycler.setupAdapter(
            R.layout.recycler_item,
            LinearLayoutManager(recycler.context)
        ) {

            bind { view, index, item ->
                view.findViewById<TextView>(R.id.textView).apply {
                    text = item.name
                    setBackgroundResource(color[index % color.size])
                }
            }
        }

        fab.setOnClickListener {
            data.shuffle()
            leoAdapter.setList(data)
        }
    }
}

data class Person(
    val id: Int,
    val age: Int,
    val name: String
)

private val DIFF_UTIL: ItemCallback<Person> = object : ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem == newItem
}