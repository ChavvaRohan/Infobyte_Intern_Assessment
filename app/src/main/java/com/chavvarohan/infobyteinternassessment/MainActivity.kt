package com.chavvarohan.infobyteinternassessment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chavvarohan.infobyteinternassessment.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textViewSignUp.setOnClickListener {
            var intent = Intent(this,ActivitySignUp::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {

            val email = binding.editTextEmail.text.toString()
            val pass = binding.editTextPassword.text.toString()

            if (email.isNotEmpty() && pass.trim().isNotEmpty() ) {
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        var intent = Intent(this,ActivityWatchList::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this,"Incorrect email or password", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}