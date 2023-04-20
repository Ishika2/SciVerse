package com.example.sciverse.PhoneAuthentication

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sciverse.MainActivity
import com.example.sciverse.databinding.ActivityOtpactivityBinding

import com.example.sciverse.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class OTPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpactivityBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String
    private lateinit var dailog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Please wait.......")
        builder.setCancelable(false)
        dailog = builder.create()
        dailog.show()

        val phoneNumber = "+91"+intent.getStringExtra("number")

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    dailog.dismiss()
                    Toast.makeText(this@OTPActivity, "Please try again later...", Toast.LENGTH_SHORT).show()
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)

                    dailog.dismiss()
                    verificationId = p0
                }
            })          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

        binding.proceed.setOnClickListener{

            if(binding.OTP.text!!.isEmpty()){
                Toast.makeText(this, "Please enter OTP...", Toast.LENGTH_LONG).show()
            }else{
                dailog.show()
                val credential = PhoneAuthProvider.getCredential(verificationId, binding.OTP.text.toString())
                auth.signInWithCredential(credential)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            dailog.dismiss()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }else{
                            dailog.dismiss()
                            Toast.makeText(this, "Error ${it.exception}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}