package com.piyush2k24.sutexian.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.piyush2k24.sutexian.databinding.ActivityShowFullUserDetailBinding

class ShowFullUserDetail : AppCompatActivity() {
    private lateinit var binding: ActivityShowFullUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowFullUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}