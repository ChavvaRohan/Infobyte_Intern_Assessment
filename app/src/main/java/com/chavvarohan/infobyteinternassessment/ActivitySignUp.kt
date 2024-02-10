package com.chavvarohan.infobyteinternassessment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chavvarohan.infobyteinternassessment.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class ActivitySignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewSignIn.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        onSignUpClicked()

    }
    private fun onSignUpClicked(){
        binding.buttonSignUp.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()

            val email = binding.editTextEmail.text.toString()
            val pass = binding.editTextPassword.text.toString()
            val confirmPass = binding.editTextConfirmPassword.text.toString()
            val name = binding.editTextName.text.toString()

             if (name.isNotEmpty() && email.isNotEmpty() && pass.trim().isNotEmpty() ) {
                 if (pass.length >= 6) {
                     if (pass == confirmPass) {
                         firebaseAuth.createUserWithEmailAndPassword(email, pass)
                             .addOnCompleteListener {
                                 if (it.isSuccessful) {
                                     var intent = Intent(this, ActivityVerification::class.java)
                                     startActivity(intent)
                                 } else {
                                     Toast.makeText(
                                         this,
                                         it.exception.toString(),
                                         Toast.LENGTH_SHORT
                                     ).show()
                                 }
                             }
                     } else {
                         Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                     }
                 }else{
                     Toast.makeText(
                         this, "Password should be greater than 6 characters", Toast.LENGTH_SHORT).show()
                 }
             } else {
                 Toast.makeText(
                     this, "Enter all fields", Toast.LENGTH_SHORT).show()
             }
        }
    }

}
