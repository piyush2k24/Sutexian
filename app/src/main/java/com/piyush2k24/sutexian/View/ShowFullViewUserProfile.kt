package com.piyush2k24.sutexian.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.piyush2k24.sutexian.Activities.SignIn
import com.piyush2k24.sutexian.R
import com.piyush2k24.sutexian.databinding.ShowFullViewUserProfileBinding

class ShowFullViewUserProfile : AppCompatActivity() {
    private lateinit var binding: ShowFullViewUserProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ShowFullViewUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        Caller()
    }
    private fun Caller(){
        binding.UserPhoto.setImageResource(intent.getIntExtra("UserPhoto", R.drawable.piyush_frag))
        binding.UserName.setText(intent.getStringExtra("UserName"))
        binding.UserEmail.setText(intent.getStringExtra("UserEmail"))
        binding.UserPhone.setText(intent.getStringExtra("UserPhone"))
        binding.UserDesignation.setText(intent.getStringExtra("UserDesignation"))

        binding.BackForward.setOnClickListener{
            startActivity(Intent(this@ShowFullViewUserProfile,DemoUsersList::class.java))
        }

        binding.LogOut.setOnClickListener{
            firebaseAuth.signOut()
            showToast("SignOut Successfully")
            startActivity(Intent(this@ShowFullViewUserProfile,SignIn::class.java))
        }
    }
    private fun showToast(str: String) {
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}