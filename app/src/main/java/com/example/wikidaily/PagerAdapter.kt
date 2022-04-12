package com.example.wikidaily

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.wikidaily.FeaturedImages.ui.FeaturedImageFragment
import com.example.wikidaily.databinding.FragmentFeaturedImageBinding
import com.example.wikidaily.randomArticle.ui.RandomArticleFragment

class   PagerAdapter(fragManager: FragmentManager) : FragmentPagerAdapter(fragManager) {
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FeaturedImageFragment()
//            1 -> return FragmentCategories()
            1 -> return RandomArticleFragment()
            else -> return FeaturedImageFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Featured Images"
            1 -> "Random Article"
            2 -> "Categories"
            else -> "Feature images"
        }
    }
}