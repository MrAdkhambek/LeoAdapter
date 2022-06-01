package com.adam.leoadapterapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.adkhambek.leo.LeoAdapter
import me.adkhambek.leo.viewpager.setupAdapter
import com.adam.leoadapterapp.data.Person
import com.adam.leoadapterapp.data.colors
import com.adam.leoadapterapp.databinding.ActivityViewPagerBinding
import com.adam.leoadapterapp.databinding.PagePersonBinding


@SuppressLint("SetTextI18n")
class ViewPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leoAdapter: LeoAdapter<Person> = binding.viewPager.setupAdapter(
            PagePersonBinding::inflate
        ) { itemBinding, index, item ->

            itemBinding.personName.text = "${item.name}  $index"
            itemBinding.personAge.text = "Age = ${item.age}"
            itemBinding.root.setBackgroundResource(colors[index % colors.size])

            itemBinding.root.setOnClickListener {
                Toast.makeText(itemBinding.root.context, "${item.name}  $index", Toast.LENGTH_SHORT).show()
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