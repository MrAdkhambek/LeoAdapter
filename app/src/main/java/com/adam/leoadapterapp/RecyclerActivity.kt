package com.adam.leoadapterapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import com.adam.leo.LeoAdapter
import com.adam.leo.recycler.setupAdapter
import com.adam.leoadapterapp.data.Person
import com.adam.leoadapterapp.data.colors
import com.adam.leoadapterapp.data.data
import kotlinx.android.synthetic.main.activity_recycler.*


@SuppressLint("SetTextI18n")
class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)


        val leoAdapter: LeoAdapter<Person> = recycler.setupAdapter(
            R.layout.recycler_item,
            DIFF_UTIL
        ) {
            val textView: TextView = findViewById(R.id.textView)
            textView.setOnClickListener {
                Toast.makeText(it.context, currentPosition.toString(), Toast.LENGTH_SHORT).show()
            }

            bind { _, index, item ->
                textView.text = "${item.name}  $index"
                textView.setBackgroundResource(colors[index % colors.size])
            }
        }

        val data = (1..1000).map {
            Person(it, it,"Adam")
        }

        leoAdapter.setList(data)
        fab.setOnClickListener {
            leoAdapter.setList(data.shuffled())
        }
    }
}

val DIFF_UTIL: DiffUtil.ItemCallback<Person> = object : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem == newItem
}