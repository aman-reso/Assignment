package com.example.assignment.aman.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.assignment.R
import com.example.assignment.aman.adapter.HomePagerAdapter
import com.example.assignment.aman.viewmodels.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    private val homeViewModel: HomeViewModel? by viewModels()
    private var viewPager:ViewPager2?=null
    private var tabLayout:TabLayout?=null
    private val contactFragment: ContactFragment? by lazy { ContactFragment() }
    private val suggestedFragment : SuggestedFragment? by lazy { SuggestedFragment() }
    private var homePagerAdapter: HomePagerAdapter?=null
    var names= arrayListOf<String>("SUGGESTED","CONTACT")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRequiredView()
    }
    private fun setUpRequiredView(){
        viewPager=findViewById(R.id.homeViewPager)
        tabLayout=findViewById(R.id.tabLayout)
        val listOfFragment= ArrayList<Fragment>()
        if (contactFragment!=null && suggestedFragment!=null) {
            listOfFragment.add(suggestedFragment!!)
            listOfFragment.add(contactFragment!!)
        }
        homePagerAdapter= HomePagerAdapter(this,listOfFragment)
        viewPager?.adapter=homePagerAdapter
        setUpTab()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
       openSwipeable()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun openSwipeable(){
        var intent= Intent(this, BasicSwipeActivity::class.java)
        startActivity(intent)
    }
    private fun setUpTab(){
        if (tabLayout!=null && viewPager!=null) {
            TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
                tab.text = names[position]
            }.attach()
        }
        viewPager?.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("Selected_Page", position.toString())
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

}