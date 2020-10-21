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

            val personNameAgeView: AppCompatTextView = findViewById(R.id.personAge)
            val personNameTextView: AppCompatTextView = findViewById(R.id.personName)

            bind { view, index, item ->
                personNameTextView.text = item.name
                @SuppressLint("SetTextI18n")
                personNameAgeView.text = "Age = ${item.age}"
                view.setBackgroundResource(colors[index % colors.size])
            }
        }

        leoAdapter.setList(data.shuffled())
        fab.setOnClickListener {
            leoAdapter.setList(data.shuffled())
        }
    }
}