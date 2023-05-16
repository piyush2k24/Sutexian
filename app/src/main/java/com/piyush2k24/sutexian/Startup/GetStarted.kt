package com.piyush2k24.sutexian.Startup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.piyush2k24.sutexian.Activities.SignIn
import com.piyush2k24.sutexian.Activities.SignUp
import com.piyush2k24.sutexian.R
import com.piyush2k24.sutexian.databinding.GetStartedBinding

class GetStarted : AppCompatActivity() {
    private lateinit var binding: GetStartedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callers();
    }

    private fun callers(){
        binding.SignUp.setOnClickListener {
//            showToast("Sign Up 🤥")
            startActivity(Intent(this@GetStarted,SignUp::class.java))
        }
        binding.SignIn.setOnClickListener{
//            showToast("Sign In 😒")
            startActivity(Intent(applicationContext,SignIn::class.java))
        }
    }

    private fun showToast( str: String){
        Toast.makeText(this@GetStarted,str,Toast.LENGTH_SHORT).show()
    }
}