package com.adam.leoadapterapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.adam.leo.LeoAdapter
import com.adam.leo.spinner.setupAdapter
import com.adam.leoadapterapp.data.Person
import com.adam.leoadapterapp.data.colors
import com.adam.leoadapterapp.data.data
import kotlinx.android.synthetic.main.activity_spinner.*


class SpinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        val leoAdapter: LeoAdapter<Person> = spinner.setupAdapter(
            R.layout.recycler_item
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