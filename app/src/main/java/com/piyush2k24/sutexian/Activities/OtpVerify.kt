package com.piyush2k24.sutexian.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.piyush2k24.sutexian.View.DemoUsersList
import com.piyush2k24.sutexian.databinding.OtpVerifyBinding

class OtpVerify : AppCompatActivity() {
    private lateinit var  binding: OtpVerifyBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=OtpVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        val verificationId=  intent.getStringExtra("verifyId")

        binding.Verify.setOnClickListener {
            val otp = binding.FillOtp.text.toString()
            if (!otp.isEmpty()) {
                val credential: PhoneAuthCredential =
                    PhoneAuthProvider.getCredential(verificationId.toString(), otp)
                signInWithPhoneAuthCredentialDemo(credential)
            } else {
                showToast("Enter Otp")
            }
        }
    }

    private fun signInWithPhoneAuthCredentialDemo(credential: PhoneAuthCredential){
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    showToast("Welcome")
                    startActivity(Intent(this,DemoUsersList::class.java))
                    finish()
                }else{
                    showToast(it.exception.toString())
                }
            }
    }
    private fun showToast(str: String) {
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}