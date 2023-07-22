package com.adam.leoadapterapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.DiffUtil
import com.adam.leoadapterapp.data.Person
import com.adam.leoadapterapp.data.colors
import com.adam.leoadapterapp.databinding.ActivityRecyclerBinding
import com.adam.leoadapterapp.databinding.RecyclerItemBinding
import com.adkhambek.leo.LeoAdapter
import com.adkhambek.leo.recycler.setupAdapter
import com.thedeanda.lorem.LoremIpsum

@SuppressLint("SetTextI18n")
class RecyclerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        val binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leoAdapter: LeoAdapter<Person, RecyclerItemBinding> = binding.recycler.setupAdapter(
            viewBinding = RecyclerItemBinding::inflate,
            diffCallback = DIFF_UTIL,
        ) { itemBinding, index, item ->
            itemBinding.textView.text = "${item.name}  $index"
            itemBinding.textView.setBackgroundResource(colors[index % colors.size])

            itemBinding.root.setOnClickListener {
                Toast.makeText(itemBinding.root.context, "${item.name}  $index", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val lorem = LoremIpsum.getInstance()
        val data = (1..1000).map {
            Person(it, it, lorem.name)
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
