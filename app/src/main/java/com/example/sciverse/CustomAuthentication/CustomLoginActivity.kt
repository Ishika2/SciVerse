package com.example.sciverse.CustomAuthentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.sciverse.MainActivity
import com.example.sciverse.databinding.ActivityCustomLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient

import com.google.firebase.auth.FirebaseAuth

class CustomLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomLoginBinding
    private lateinit var auth: FirebaseAuth
    private val tag = "SignIn TAG"
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN: Int = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alreadySignup.setOnClickListener{
            val intent = Intent(this, CustomSigninActivity::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener{
            val email = binding.username.text.toString()
            val pass = binding.password.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}