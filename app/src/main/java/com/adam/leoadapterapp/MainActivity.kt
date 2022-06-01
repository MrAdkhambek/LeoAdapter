package com.adam.leoadapterapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adam.leoadapterapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.actionRecycler.setOnClickListener {
            startActivity(Intent(this, RecyclerActivity::class.java))
        }

        binding.actionViewPager.setOnClickListener {
            startActivity(Intent(this, ViewPagerActivity::class.java))
        }
    }
}



