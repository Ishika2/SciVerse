package com.example.sciverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.sciverse.User.ViewPagerAdapter
import com.example.sciverse.bottomNavBar.CalculatorFragment
import com.example.sciverse.bottomNavBar.HomeFragment
import com.example.sciverse.bottomNavBar.LibraryFragment
import com.example.sciverse.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstFragment=HomeFragment()
        val secondFragment=LibraryFragment()
        val thirdFragment=CalculatorFragment()

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

    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}


















































//private fun setupTabLayout() {
//    TabLayoutMediator(
//        binding.tabLayout, binding.viewPager
//    ) { tab, position -> tab.text = "Tab " + (position + 1) }.attach()
//}
//
//private fun setupViewPager() {
//    val adapter = ViewPagerAdapter(this, 2)
//    binding.viewPager.adapter = adapter
//}
//
//override fun onBackPressed() {
//    val viewPager = binding.viewPager
//    if (viewPager.currentItem == 0) {
//        // If the user is currently looking at the first step, allow the system to handle the
//        // Back button. This calls finish() on this activity and pops the back stack.
//        super.onBackPressed()
//    } else {
//        // Otherwise, select the previous step.
//        viewPager.currentItem = viewPager.currentItem - 1
//    }
//}