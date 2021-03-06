package com.adam.leoadapterapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import com.adam.leo.LeoAdapter
import com.adam.leo.recycler.setupAdapter
import com.adam.leoadapterapp.data.Person
import com.adam.leoadapterapp.data.colors
import com.adam.leoadapterapp.databinding.ActivityRecyclerBinding
import com.adam.leoadapterapp.databinding.RecyclerItemBinding


@SuppressLint("SetTextI18n")
class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        val binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leoAdapter: LeoAdapter<Person> = binding.recycler.setupAdapter(
            RecyclerItemBinding::inflate
        ) { itemBinding, index, item ->
            itemBinding.textView.text = "${item.name}  $index"
            itemBinding.textView.setBackgroundResource(colors[index % colors.size])

            itemBinding.root.setOnClickListener {
                Toast.makeText(itemBinding.root.context, "${item.name}  $index", Toast.LENGTH_SHORT).show()
            }
        }

        val data = (1..1000).map {
            Person(it, it, "Adam")
        }

        leoAdapter.setList(data)
        binding.fab.setOnClickListener {
            leoAdapter.setList(data.shuffled())
        }
    }
}

val DIFF_UTIL: DiffUtil.ItemCallback<Person> = object : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem == newItem
}