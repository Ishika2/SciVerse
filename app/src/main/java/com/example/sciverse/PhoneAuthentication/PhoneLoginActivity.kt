package com.example.sciverse.PhoneAuthentication

import com.example.sciverse.MainActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.sciverse.databinding.ActivityPhoneLoginBinding

import com.google.firebase.auth.FirebaseAuth

class PhoneLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.otpButton.setOnClickListener{
            if(binding.Phoneno.text!!.isEmpty()){
                Toast.makeText(this, "Enter your phone number", Toast.LENGTH_LONG).show()
            }else{
                var intent = Intent(this, OTPActivity::class.java)
                intent.putExtra("number", binding.Phoneno.text!!.toString())
                startActivity(intent)
            }
        }
    }
}
