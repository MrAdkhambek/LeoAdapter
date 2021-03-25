package com.adam.leoadapterapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adam.leo.LeoAdapter
import com.adam.leo.spinner.setupAdapter
import com.adam.leoadapterapp.data.Person
import com.adam.leoadapterapp.data.colors
import com.adam.leoadapterapp.databinding.ActivitySpinnerBinding
import com.adam.leoadapterapp.databinding.RecyclerItemBinding


@SuppressLint("SetTextI18n")
class SpinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
        val binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leoAdapter: LeoAdapter<Person> = binding.spinner.setupAdapter(
            RecyclerItemBinding::inflate
        ) { itemBinding, index, item ->
            itemBinding.textView.apply {
                text = "${item.name}  $index"
                setBackgroundResource(colors[index % colors.size])
            }
        }

        val data = (1..1000).map {
            Person(it, it, "Adam")
        }

        leoAdapter.setList(data.shuffled())
        binding.fab.setOnClickListener {
            leoAdapter.setList(data.shuffled())
        }
    }
}