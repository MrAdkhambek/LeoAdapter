package com.adam.leoadapterapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.adam.leo.LeoAdapter
import com.adam.leo.setupAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var leoAdapter: LeoAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data: ArrayList<String> = arrayListOf(
            "Adam",
            "Peter",
            "John",
            "Artur",
            "Joseph",
            "Adam",
            "Peter",
            "John",
            "Artur",
            "Joseph",
            "Adam",
            "Peter",
            "John",
            "Artur",
            "Joseph",
            "Adam",
            "Peter",
            "John",
            "Artur",
            "Joseph",
            "Adam",
            "Peter",
            "John",
            "Artur",
            "Joseph"
        )

        val color: Array<Int> = arrayOf(
            android.R.color.holo_blue_bright,
            android.R.color.holo_blue_dark,
            android.R.color.holo_green_light,
            android.R.color.holo_red_light,
            android.R.color.holo_purple
        )

        recycler.setupAdapter<String>(
            R.layout.recycler_item
        ) {

            bind { view, index, item ->
                view.findViewById<TextView>(R.id.textView).apply {
                    text = item
                    setBackgroundResource(color[index % color.size])
                }
            }

            submitList(data)

            leoAdapter = this
        }

        fab.setOnClickListener {
            data.shuffle()
            leoAdapter.submitList(data)
        }
    }
}
