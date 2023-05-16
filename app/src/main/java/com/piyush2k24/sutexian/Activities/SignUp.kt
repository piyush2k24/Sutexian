package com.piyush2k24.sutexian.Activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.piyush2k24.sutexian.Model.Students
import com.piyush2k24.sutexian.R
import com.piyush2k24.sutexian.databinding.SignUpBinding
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.net.Inet4Address

class SignUp : AppCompatActivity() {
    private lateinit var binding: SignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    var sImage:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        databaseReference=FirebaseDatabase.getInstance().getReference("Users")
        caller();
    }
    private fun caller(){
        binding.UserImage.setOnClickListener {
            val fileIntent=Intent(Intent.ACTION_GET_CONTENT)
            fileIntent.setType("image/*")
            ActivityResultLauncher.launch(fileIntent)
        }
        binding.SignUp.setOnClickListener{
            if(isValidate()){
                firebaseAuth.createUserWithEmailAndPassword(binding.UserEmail.text.toString(),binding.UserPassword.text.toString())
                    .addOnCompleteListener(OnCompleteListener {
                        if (it.isComplete){
                            startActivity(Intent(this@SignUp,SignIn::class.java))
                        }else{
                            showToast("Error")
                        }
                    })

                val Sid=databaseReference.push().key!!

                val Students= Students(
                    sImage,
                    binding.UserName.text.toString(),
                    binding.UserEmail.text.toString(),
                    binding.UserPhone.text.toString(),
                    binding.UserPassword.text.toString(),
                )

                databaseReference.child(Sid).setValue(Students)
                    .addOnCompleteListener{

                        showToast("Successfully SignUp")

                        sImage=""
                        binding.UserImage.setImageBitmap(null)
                        binding.UserName.text?.clear()
                        binding.UserEmail.text?.clear()
                        binding.UserPhone.text?.clear()
                        binding.UserPassword.text?.clear()
                    }
                    .addOnFailureListener{
                        showToast(it.toString())
                    }
            }
        }
        binding.AlreadyHaveAnAccount.setOnClickListener{
            startActivity(Intent(applicationContext,SignIn::class.java))
        }
    }

    private val ActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode==AppCompatActivity.RESULT_OK){
            val uri=result.data!!.data
            try{
                val inputStream=applicationContext.contentResolver.openInputStream(uri!!)
                val bitmap=BitmapFactory.decodeStream(inputStream)
                val stream=ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
                val bytes=stream.toByteArray()
                sImage=Base64.encodeToString(bytes,Base64.DEFAULT)
                binding.UserImage.setImageBitmap(bitmap)
                inputStream!!.close()
            }catch (e : Exception){
                showToast(e.message.toString())
            }
        }
    }

    private fun isValidate(): Boolean{
        if(binding.UserName.text.toString().isEmpty()){
            showToast("Enter Your Name")
            return false
        }else if(binding.UserEmail.text.toString().isEmpty()){
            showToast("Please Enter Your Email");
            return false
        }else if (!Patterns.EMAIL_ADDRESS.matcher(binding.UserEmail.text.toString()).matches()) {
            showToast("Please Enter Valid Email")
            return false
        }else if (binding.UserPhone.text.toString().isEmpty()){
            showToast("Enter Your PhoneNo")
            return false
        }else if(binding.UserPhone.text.toString().length<10 || binding.UserPhone.text.toString().length>10){
            showToast("Phone No Should be 10 digit")
            return false
        }else if(binding.UserPassword.text.toString().isEmpty()){
            showToast("Set Your Password")
            return false
        }else if(binding.UserPassword.text.toString().length<6){
            showToast("Password Length Must be 6 char")
            return false
        }else if(!binding.UserConfirmPassword.text.toString().equals(binding.UserPassword.text.toString())){
            showToast("Password Not Same")
            return false
        }
        return true
    }
    private fun showToast(str : String){
        Toast.makeText(this@SignUp,str,Toast.LENGTH_SHORT).show()
    }
}