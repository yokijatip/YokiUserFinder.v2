package com.enigma.yokiuserfinderv2.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.enigma.yokiuserfinderv2.R
import com.enigma.yokiuserfinderv2.ui.followers.FollowersFragment
import com.enigma.yokiuserfinderv2.ui.following.FollowingFragment

@Suppress("DEPRECATION")
class PagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager,
    private val fragmentData: Bundle
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

//    Judul Tab
    private val JUDULTAB = intArrayOf(R.string.tab_1, R.string.tab2)

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
//        Inisiasi Tab
        return when (position) {
            0 -> FollowersFragment()
            1 -> FollowingFragment()
            else -> throw IllegalArgumentException("error :P")
        }.apply {
//            Save bundle data ke fragment
            arguments = fragmentData
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(JUDULTAB[position])
    }

}