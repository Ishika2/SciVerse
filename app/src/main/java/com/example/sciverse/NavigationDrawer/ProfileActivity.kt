package com.example.sciverse.NavigationDrawer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sciverse.CustomAuthentication.CustomSigninActivity
import com.example.sciverse.R
import com.example.sciverse.databinding.ActivityProfileBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BottomSheetBehavior.from(binding.bottomSheet).apply {
            peekHeight = 100
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // User is signed in
            val displayName = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Update UI with user information
            binding.displayNameTextView.setText(displayName)
            binding.emailTextView.setText(email)

            // Load profile picture using a library like Picasso or Glide
            Picasso.get().load(photoUrl).into(binding.profileImage)
        }

        binding.signOutButton.setOnClickListener(View.OnClickListener { // Sign out the user
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, CustomSigninActivity::class.java))
        })
    }
}