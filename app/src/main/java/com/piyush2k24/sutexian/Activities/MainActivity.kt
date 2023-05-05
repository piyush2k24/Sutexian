package com.piyush2k24.sutexian.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.piyush2k24.sutexian.R
import com.piyush2k24.sutexian.databinding.ActivityMainBinding
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.crashBtn.setOnClickListener {
            throw RuntimeException("Crash App")
        }
    }
}