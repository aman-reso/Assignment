package com.example.assignment.aman.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter( private val activity: AppCompatActivity, private val fragmentlist:ArrayList<Fragment>):
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return fragmentlist.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentlist[position]
    }
}