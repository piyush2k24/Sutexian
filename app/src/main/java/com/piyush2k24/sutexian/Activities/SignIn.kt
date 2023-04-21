package com.piyush2k24.sutexian.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.piyush2k24.sutexian.MainActivity
import com.piyush2k24.sutexian.databinding.SignInBinding

class SignIn : AppCompatActivity() {
    private lateinit var binding: SignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=SignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        caller();
    }

    private fun caller(){
        binding.SignIn.setOnClickListener{
            if(isValidate()){
                startActivity(Intent(this@SignIn,MainActivity::class.java))
                showToast("Successfully SignIn !")
            }
        }
        binding.CreateAnNewAccount.setOnClickListener{
            startActivity(Intent(applicationContext,SignUp::class.java))
        }

    }
    private fun isValidate(): Boolean {
        if(binding.EmailId.text.toString().isEmpty()){
            showToast("Please Enter Your Email");
            return false
        }else if (!Patterns.EMAIL_ADDRESS.matcher(binding.EmailId.text.toString()).matches()){
            showToast("Please Enter Valid Email")
            return false
        }else if (binding.Password.text.toString().isEmpty()){
            showToast("Password Can't be Empty")
            return false
        }else if (binding.Password.text.toString().length<6){
            showToast("Password length must be 6 char")
            return false
        }
        return true
    }

    private fun showToast(str: String){
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}