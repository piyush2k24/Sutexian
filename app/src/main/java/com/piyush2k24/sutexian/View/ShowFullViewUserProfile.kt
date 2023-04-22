package com.piyush2k24.sutexian.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.piyush2k24.sutexian.R
import com.piyush2k24.sutexian.databinding.ShowFullViewUserProfileBinding

class ShowFullViewUserProfile : AppCompatActivity() {
    private lateinit var binding: ShowFullViewUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ShowFullViewUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Caller()
    }
    private fun Caller(){
        binding.UserPhoto.setImageResource(intent.getIntExtra("UserPhoto", R.drawable.piyush_frag))
        binding.UserName.setText(intent.getStringExtra("UserName"))
        binding.UserEmail.setText(intent.getStringExtra("UserEmail"))
        binding.UserPhone.setText(intent.getStringExtra("UserPhone"))
        binding.UserDesignation.setText(intent.getStringExtra("UserDesignation"))

        binding.Back.setOnClickListener {
            startActivity(Intent(this@ShowFullViewUserProfile,DemoUsersList::class.java))
        }
    }
}