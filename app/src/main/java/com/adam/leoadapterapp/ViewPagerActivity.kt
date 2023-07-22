package com.adam.leoadapterapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.adam.leoadapterapp.data.Person
import com.adam.leoadapterapp.data.colors
import com.adam.leoadapterapp.databinding.ActivityViewPagerBinding
import com.adam.leoadapterapp.databinding.PagePersonBinding
import com.adkhambek.leo.LeoAdapter
import com.adkhambek.leo.viewpager.setupAdapter
import com.thedeanda.lorem.LoremIpsum

@SuppressLint("SetTextI18n")
class ViewPagerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leoAdapter: LeoAdapter<Person, PagePersonBinding> = binding.viewPager.setupAdapter(
            viewBinding = PagePersonBinding::inflate
        ) { itemBinding, index, item ->

            itemBinding.personName.text = "${item.name}  $index"
            itemBinding.personAge.text = "Age = ${item.age}"
            itemBinding.root.setBackgroundResource(colors[index % colors.size])

            itemBinding.root.setOnClickListener {
                Toast.makeText(itemBinding.root.context, "${item.name}  $index", Toast.LENGTH_SHORT).show()
            }
        }

        val lorem = LoremIpsum.getInstance()
        val data = (1..1000).map {
            Person(it, it, lorem.name)
        }

        leoAdapter.setList(data.shuffled())
        binding.fab.setOnClickListener {
            leoAdapter.setList(data.shuffled())
        }
    }
}
