package com.example.sciverse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sciverse.databinding.ActivityGoogleCustomLoginBinding

class CustomLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleCustomLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleCustomLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}