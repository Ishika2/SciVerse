package com.example.sciverse.NavigationDrawer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sciverse.databinding.FragmentProfileBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {
    private lateinit var _binding:FragmentProfileBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BottomSheetBehavior.from(binding.bottomSheet).apply {
            peekHeight = 300
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
        })
    }
}