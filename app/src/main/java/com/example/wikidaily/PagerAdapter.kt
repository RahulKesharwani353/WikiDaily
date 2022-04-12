package com.example.wikidaily

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.wikidaily.FeaturedImages.ui.FeaturedImageFragment
import com.example.wikidaily.databinding.FragmentFeaturedImageBinding

class   PagerAdapter(fragManager: FragmentManager) : FragmentPagerAdapter(fragManager) {
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FeaturedImageFragment()
//            1 -> return FragmentCategories()
//            2 -> return FragmentRandomPages()
            else -> return FeaturedImageFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Featured Images"
            1 -> return "Categories"
            2 -> return "Random Pages"
            else -> return "Feature images"
        }
    }
}