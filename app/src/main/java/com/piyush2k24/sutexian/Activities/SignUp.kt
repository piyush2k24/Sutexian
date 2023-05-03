package com.piyush2k24.sutexian.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Toast
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

class SignUp : AppCompatActivity() {
    private lateinit var binding: SignUpBinding
    private lateinit var materialDatePicker: MaterialDatePicker<Long>
    private lateinit var arrayAdapter:ArrayAdapter<String>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    var CountryNo= arrayOf(
        "+91","+01","+41","+49","+45","+46","+47","+55",
        "+81","+92","+93","+94"
        );
    var Colleges= arrayOf(
        "Sutex Bank College","J.J Shah College","Pro, V.B Shah College","Akhand Anand College",
        "Mahavir College","SVNIT","S.V Patel College","SDJ College","Govt. Medical College"
    );
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        databaseReference=FirebaseDatabase.getInstance().getReference("Students")
        caller();
    }
    private fun caller(){
        binding.SignUp.setOnClickListener{
            if(isValidate()){
                firebaseAuth.createUserWithEmailAndPassword(binding.EmailId.text.toString(),binding.Password.text.toString())
                    .addOnCompleteListener(OnCompleteListener {
                        if (it.isComplete){
                            startActivity(Intent(this@SignUp,SignIn::class.java))
                        }else{
                            showToast("Error")
                        }
                    })

                val Sid=databaseReference.push().key!!

                val Students= Students(
                    binding.Fname.text.toString(),
                    binding.Lname.text.toString(),
                    binding.Birthdate.text.toString(),
                    binding.EmailId.text.toString(),
                    binding.PhoneNo.text.toString(),
                    binding.AmroliColleges.text.toString()
                )

                databaseReference.child(Sid).setValue(Students)
                    .addOnCompleteListener{

                        showToast("Successfully SignUp")

                        binding.Fname.text?.clear()
                        binding.Lname.text?.clear()
                        binding.Birthdate.text?.clear()
                        binding.EmailId.text?.clear()
                        binding.PhoneNo.text?.clear()
                        binding.AmroliColleges.text?.clear()
                        binding.Password.text?.clear()
                    }
                    .addOnFailureListener{
                        showToast(it.toString())
                    }
            }
        }
        binding.AlreadyHaveAnAccount.setOnClickListener{
            startActivity(Intent(applicationContext,SignIn::class.java))
        }
        BirthDatePicker()
        MaterialSpinner()
    }

    private fun isValidate(): Boolean{
        if(binding.Fname.text.toString().isEmpty()){
            showToast("Enter Your FirstName")
            return false
        }else if (binding.Lname.text.toString().isEmpty()){
            showToast("Enter Your LastName")
            return false
        }else if (binding.Birthdate.text.toString().isEmpty()){
            showToast("Please Choose Your Birthdate ")
            return false
        }else if(!binding.Male.isChecked && !binding.Female.isChecked && !binding.Other.isChecked){
            showToast("Please Choose Your Gender")
            return false
        }else if(binding.EmailId.text.toString().isEmpty()){
            showToast("Please Enter Your Email");
            return false
        }else if (!Patterns.EMAIL_ADDRESS.matcher(binding.EmailId.text.toString()).matches()) {
            showToast("Please Enter Valid Email")
            return false
        }else if (binding.CountryNo.text.toString().isEmpty()){
            showToast("Choose Your Country Code")
            return false
        }else if (binding.PhoneNo.text.toString().isEmpty()){
            showToast("Enter Your PhoneNo")
            return false
        }else if(binding.PhoneNo.text.toString().length<10 || binding.PhoneNo.text.toString().length>10){
            showToast("Phone No Should be 10 digit")
            return false
        }else if (binding.AmroliColleges.text.toString().isEmpty()){
            showToast("Please Choose The college")
            return false
        }else if(binding.Password.text.toString().isEmpty()){
            showToast("Set Your Password")
            return false
        }else if(binding.Password.text.toString().length<6){
            showToast("Password Length Must be 6 char")
            return false
        }
        return true
    }
    private fun BirthDatePicker(){
        materialDatePicker= MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setTitleText("Choose Birthdate")
            .build()

        binding.btnBirthdate.setOnClickListener{
            materialDatePicker.show(supportFragmentManager,"Kotlin Material datePicker")
            materialDatePicker.addOnPositiveButtonClickListener(
                MaterialPickerOnPositiveButtonClickListener {
                    binding.Birthdate.setText(materialDatePicker.headerText)
                }
            )
        }
    }
    private fun MaterialSpinner(){
        arrayAdapter= ArrayAdapter<String>(applicationContext, R.layout.cust_spinner,CountryNo)
        binding.CountryNo.setAdapter(arrayAdapter)

        arrayAdapter= ArrayAdapter<String>(this@SignUp,R.layout.cust_spinner,Colleges)
        binding.AmroliColleges.setAdapter(arrayAdapter)
    }
    private fun showToast(str : String){
        Toast.makeText(this@SignUp,str,Toast.LENGTH_SHORT).show()
    }
}