package com.adam.leoadapterapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.adam.leo.LeoAdapter
import com.adam.leo.spinner.setupAdapter
import com.adam.leoadapterapp.data.Person
import com.adam.leoadapterapp.data.colors
import kotlinx.android.synthetic.main.activity_spinner.*


@SuppressLint("SetTextI18n")
class SpinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        val leoAdapter: LeoAdapter<Person> = spinner.setupAdapter(
            R.layout.recycler_item
        ) {
            val textView: TextView = findViewById(R.id.textView)

            bind { _, index, item ->
                Log.i("TTT", currentPosition.toString())
                textView.apply {
                    text = "${item.name}  $index"
                    setBackgroundResource(colors[index % colors.size])
                }
            }
        }

        val data = (1..1000).map {
            Person(it, it, "Adam")
        }

        leoAdapter.setList(data.shuffled())
        fab.setOnClickListener {
            leoAdapter.setList(data.shuffled())
        }
    }
}