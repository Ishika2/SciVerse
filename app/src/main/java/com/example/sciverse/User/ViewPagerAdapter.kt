package com.example.sciverse.User

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sciverse.bottomNavBar.LibraryFragment

class ViewPagerAdapter(fragmentActivity: LibraryFragment, private var totalCount: Int) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return totalCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BasicUserFragment()
            1 -> AdvanceUserFragment()
            else -> BasicUserFragment()
        }
    }
}
