package com.adam.leoadapterapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.adam.leo.LeoAdapter
import com.adam.leo.viewpager.setupAdapter
import com.adam.leoadapterapp.data.Person
import com.adam.leoadapterapp.data.colors
import com.adam.leoadapterapp.data.data
import kotlinx.android.synthetic.main.activity_view_pager.*


class ViewPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val leoAdapter: LeoAdapter<Person> = viewPager.setupAdapter(
            R.layout.page_person
        ) {
            bind { view, index, item ->

                view.findViewById<AppCompatTextView>(R.id.personName).text = item.name
                view.setBackgroundResource(colors[index % colors.size])

                @SuppressLint("SetTextI18n")
                view.findViewById<AppCompatTextView>(R.id.personAge).text = "Age = ${item.age}"
            }
        }

        leoAdapter.setList(data.shuffled())
        fab.setOnClickListener {
            leoAdapter.setList(data.shuffled())
        }
    }
}