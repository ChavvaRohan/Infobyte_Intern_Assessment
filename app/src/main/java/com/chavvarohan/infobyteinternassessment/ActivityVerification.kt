package com.chavvarohan.infobyteinternassessment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.chavvarohan.infobyteinternassessment.databinding.ActivityMainBinding
import com.chavvarohan.infobyteinternassessment.databinding.ActivityVerificationBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class ActivityVerification : AppCompatActivity() {
    private lateinit var binding: ActivityVerificationBinding
    private lateinit var otp: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onSendOpt()
        numberOtpMove()

    }

    private fun onSendOpt() {
        binding.buttonSendOtp.setOnClickListener {
            if (binding.editTextMobileNo.text.toString().length == 10) {

                binding.progressbarSendOtp.visibility = View.VISIBLE
                binding.buttonSendOtp.visibility = View.INVISIBLE


                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91" + binding.editTextMobileNo.text.toString(),
                    60,
                    TimeUnit.SECONDS, this,
                    object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                            binding.progressbarSendOtp.visibility = View.GONE
                            binding.buttonSendOtp.visibility = View.INVISIBLE
                        }

                        override fun onVerificationFailed(e: FirebaseException) {
                            binding.progressbarSendOtp.visibility = View.GONE
                            binding.buttonSendOtp.visibility = View.INVISIBLE
                            Toast.makeText(this@ActivityVerification, e.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        override fun onCodeSent(
                            backendOpt: String,
                            p1: PhoneAuthProvider.ForceResendingToken
                        ) {
                            binding.progressbarSendOtp.visibility = View.GONE
                            binding.buttonSendOtp.visibility = View.INVISIBLE
                            otp = backendOpt
                            onVerifyButton(otp)

                        }

                    })

            } else {
                Toast.makeText(this, "Incorrect Number(10 digits must)", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onVerifyButton(otp: String) {
        binding.buttonVerify.setOnClickListener {
            if (binding.editText1.text.toString().trim()
                    .isNotEmpty() && binding.editText2.text.toString().trim()
                    .isNotEmpty() && binding.editText3.text.toString().trim()
                    .isNotEmpty() && binding.editText4.text.toString().trim().isNotEmpty()
                && binding.editText5.text.toString().trim().isNotEmpty()
                && binding.editText6.text.toString().trim().isNotEmpty()
            ) {
                var enterCodeOtp = binding.editText1.text.toString() +
                        binding.editText2.text.toString() +
                        binding.editText3.text.toString() +
                        binding.editText4.text.toString() +
                        binding.editText5.text.toString() +
                        binding.editText6.text.toString()

                if (otp != null) {
                    binding.progressbarVerifyOtp.visibility = View.VISIBLE
                    binding.buttonVerify.visibility = View.INVISIBLE
                    var phoneAuthCredential =
                        PhoneAuthProvider.getCredential(otp, enterCodeOtp)
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener { task ->
                            binding.progressbarVerifyOtp.visibility = View.VISIBLE
                            binding.buttonVerify.visibility = View.INVISIBLE
                            if (task.isSuccessful) {
                                val intent = Intent(this, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "Enter the correct otp", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Please check internet connection", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Please enter all numbers", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonResend.setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + binding.editTextMobileNo.text.toString(),
                60,
                TimeUnit.SECONDS, this,
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                    }

                    override fun onVerificationFailed(e: FirebaseException) {

                        Toast.makeText(this@ActivityVerification, e.message, Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onCodeSent(
                        newBackendOpt: String,
                        p1: PhoneAuthProvider.ForceResendingToken
                    ) {
                        this@ActivityVerification.otp = newBackendOpt
                        Toast.makeText(
                            this@ActivityVerification,
                            "Otp sended successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
        }
    }

    private fun numberOtpMove() {
        binding.editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim().isNotEmpty()) {
                    binding.editText2.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.editText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim().isNotEmpty()) {
                    binding.editText3.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.editText3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim().isNotEmpty()) {
                    binding.editText4.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.editText4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim().isNotEmpty()) {
                    binding.editText5.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.editText5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim().isNotEmpty()) {
                    binding.editText6.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
}