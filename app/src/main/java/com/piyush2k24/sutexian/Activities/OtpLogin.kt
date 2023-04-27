package com.piyush2k24.sutexian.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.piyush2k24.sutexian.databinding.OtpLoginBinding
import java.util.concurrent.TimeUnit

class OtpLogin : AppCompatActivity() {
    private lateinit var binding:OtpLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var resendToken:PhoneAuthProvider.ForceResendingToken
    lateinit var verificationId:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=OtpLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()
        Caller()
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            }

            override fun onVerificationFailed(ec: FirebaseException) {
               showToast(ec.toString())
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationId=p0
                resendToken=p1
                val intent=Intent(applicationContext,OtpVerify::class.java)
                intent.putExtra("verifyId",verificationId)
                Log.d("verifyId",verificationId)
                startActivity(intent)
            }

        }
    }

    private fun Caller(){
        binding.SignInWithEmail.setOnClickListener{
                startActivity(Intent(applicationContext,SignIn::class.java))
        }
        binding.NextToVerifyPhone.setOnClickListener {
            if(isOk()){
                val option= PhoneAuthOptions.newBuilder(firebaseAuth)
                    .setCallbacks(callbacks)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(this)
                    .setPhoneNumber("+91${binding.PhoneNo.text.toString()}")
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(option)
                showToast("Please Verify Number")
                val intent:Intent=Intent(this,OtpVerify::class.java)
                intent.putExtra("UserPhone","+91${binding.PhoneNo.text.toString()}")
                startActivity(intent)
            }
        }
    }

    private fun isOk(): Boolean {
        if(binding.PhoneNo.text.toString().isEmpty()){
            showToast("Please Enter Your Number")
            return false
        }else if (binding.PhoneNo.text.toString().length<10){
            showToast("Phone MustBe 10 Digit")
            return false
        }
        return true
    }
    private fun showToast(str: String) {
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}