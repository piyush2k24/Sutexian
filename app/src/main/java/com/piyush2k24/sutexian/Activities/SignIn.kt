package com.piyush2k24.sutexian.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.piyush2k24.sutexian.R
import com.piyush2k24.sutexian.View.DemoUsersList
import com.piyush2k24.sutexian.View.GetDataFromRealtimeFirebase
import com.piyush2k24.sutexian.databinding.SignInBinding

class SignIn : AppCompatActivity() {
    private lateinit var binding: SignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=SignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        caller();

        val googleSignInOptions=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient=GoogleSignIn.getClient(this,googleSignInOptions)

        binding.SignInWithGoogle.setOnClickListener {
            googleSignIN()
        }
    }

    private fun googleSignIN(){
        val signinIntent=googleSignInClient.signInIntent
        launcher.launch(signinIntent)
    }

    private val launcher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
                if (result.resultCode==Activity.RESULT_OK){
                    val task=GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    handleResults(task)
                }

    }

    private fun handleResults(task: Task<GoogleSignInAccount>){
        if(task.isSuccessful){
            val account: GoogleSignInAccount?=task.result
            if(account!=null){
                updateUi(account)
            }else{
                showToast(task.exception.toString())
            }
        }
    }

    private fun updateUi(account: GoogleSignInAccount){
        val credential=GoogleAuthProvider.getCredential(account.idToken,null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    showToast("Welcome")
                    startActivity(Intent(this,GetDataFromRealtimeFirebase::class.java))
                }else{
                    showToast(it.toString())
                }
            }
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