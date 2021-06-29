package com.example.newsapp.Ui.Adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.Ui.Fragments.BusinessFragment
import com.example.newsapp.Ui.Fragments.HeadlinesFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return HeadlinesFragment()
        }
        return BusinessFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}