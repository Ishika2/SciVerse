package com.example.sciverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.sciverse.NavigationDrawer.ProfileFragment
import com.example.sciverse.bottomNavBar.CalculatorFragment
import com.example.sciverse.bottomNavBar.HomeFragment
import com.example.sciverse.bottomNavBar.LibraryFragment
import com.example.sciverse.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstFragment=HomeFragment()
        var secondFragment=LibraryFragment()
        val thirdFragment=CalculatorFragment()

        // Check if the fragment is added
        if (secondFragment.isAdded) {
            // Set the current fragment to the second fragment
            setCurrentFragment(secondFragment)
        } else {
            // Create the second fragment
            secondFragment = LibraryFragment()

            // Set the current fragment to the second fragment
            setCurrentFragment(secondFragment)
        }

        setCurrentFragment(firstFragment)

        // Initialize bottomNavigationView
        val bottomNavigationView = binding.bottomNavigationView

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(firstFragment)
                R.id.library->setCurrentFragment(secondFragment)
                R.id.calculator->setCurrentFragment(thirdFragment)

            }
            true
        }

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navigationView = findViewById<NavigationView>(R.id.navigation_drawer)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item clicks here
            when (menuItem.itemId) {
                R.id.nav_account -> {
                    // Handle click on menu item 1
                    true
                }
                R.id.nav_settings -> {
                    // Handle click on menu item 2
                    true
                }
                // Add more menu item handling as needed
                else -> false
            }
        }

        binding.imagebutton.setOnClickListener{
            drawerLayout.openDrawer(navigationView)
        }
        binding.profilebutton.setOnClickListener{
            setCurrentFragment(ProfileFragment())
        }
    }

//    private fun selectDrawerItem(menuItem: MenuItem) {
//        // Create a new fragment based on the selected menu item
//        var fragment: Fragment? = null
//        when (menuItem.itemId) {
//            R.id.nav_profile -> fragment = ProfileFragment()
//            R.id.nav_settings -> fragment = SettingsFragment()
//            R.id.nav_logout -> {
//                // Handle logout functionality here
//            }
//        }
//
//        // Replace the current fragment with the selected fragment
//        if (fragment != null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, fragment)
//                .commit()
//        }
//
//        // Highlight the selected item in the navigation drawer
//        menuItem.isChecked = true
//
//        // Close the navigation drawer
//        drawerLayout.closeDrawers()
//    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}