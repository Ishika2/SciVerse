package com.example.sciverse.NavigationDrawer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sciverse.CustomAuthentication.CustomSigninActivity
import com.example.sciverse.JobDB.jobDao
import com.example.sciverse.JobDB.jobRoomDatabase
import com.example.sciverse.R
import com.example.sciverse.databinding.ActivityProfileBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var database: jobRoomDatabase
    private lateinit var dao: jobDao
    private lateinit var adapter: AdapterForJobid
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database
        database = jobRoomDatabase.getDatabase(this)
        // Initialize the dao
        dao = database.dao()

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

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            val datalist = withContext(Dispatchers.IO) {
                dao.getAll()
            }
            adapter = AdapterForJobid(this@ProfileActivity, datalist)
            binding.recyclerView.adapter = adapter
        }
    }
}