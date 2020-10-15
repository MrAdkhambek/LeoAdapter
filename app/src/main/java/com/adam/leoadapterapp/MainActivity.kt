package com.adam.leoadapterapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        actionRecycler.setOnClickListener {
            startActivity(Intent(this, RecyclerActivity::class.java))
        }

        actionSpinner.setOnClickListener {
            startActivity(Intent(this, SpinnerActivity::class.java))
        }

        actionViewPager.setOnClickListener {
            startActivity(Intent(this, ViewPagerActivity::class.java))
        }
    }
}



