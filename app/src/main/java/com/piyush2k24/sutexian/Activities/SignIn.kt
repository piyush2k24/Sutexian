package com.piyush2k24.sutexian.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.piyush2k24.sutexian.View.DemoUsersList
import com.piyush2k24.sutexian.databinding.SignInBinding

class SignIn : AppCompatActivity() {
    private lateinit var binding: SignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=SignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        caller();
    }

    private fun caller(){
        binding.SignIn.setOnClickListener{
            if(isValidate()){
                firebaseAuth.signInWithEmailAndPassword(binding.EmailId.text.toString(),binding.Password.text.toString())
                    .addOnSuccessListener {
                        showToast("Successfully SignIn")
                        startActivity(Intent(this@SignIn, DemoUsersList::class.java))
                    }
                    .addOnFailureListener(OnFailureListener {
                        showToast("Password Not Match 🤨")
                    })
            }
        }
        binding.CreateAnNewAccount.setOnClickListener{
            startActivity(Intent(applicationContext,SignUp::class.java))
        }

        binding.SignInWithPhone.setOnClickListener{
            startActivity(Intent(this@SignIn,OtpLogin::class.java))
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
        }
        return true
    }

    private fun showToast(str: String){
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}