package com.example.assignment.aman.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment.R
import com.example.assignment.aman.adapter.HomePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BasicSwipeActivity : AppCompatActivity() {
    private var viewPager: ViewPager2?=null
    private var tabLayout: TabLayout?=null
    private val contactFragment: ContactFragment? by lazy { ContactFragment() }
    private val suggestedFragment : SuggestedFragment? by lazy { SuggestedFragment() }
    private val thirdFragment:ThirdFragment? by lazy { ThirdFragment() }
    private var homePagerAdapter: HomePagerAdapter?=null
    var names= arrayListOf("VIDEO","IMAGES","CLIPS")
private var tabIcons: IntArray = intArrayOf(
    R.drawable.ic_baseline_slow_motion_video_24,
    R.drawable.ic_baseline_slow_motion_video_24,
    R.drawable.ic_baseline_slow_motion_video_24
)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_basic_swipe_activiy)
        setUpRequiredView()
    }
    private fun setUpRequiredView(){
        viewPager=findViewById(R.id.viewPager2)
        tabLayout=findViewById(R.id.tabLayout1)
        val listOfFragment= ArrayList<Fragment>()
        if (contactFragment!=null && suggestedFragment!=null && thirdFragment!=null) {
            listOfFragment.add(suggestedFragment!!)
            listOfFragment.add(contactFragment!!)
            listOfFragment.add(thirdFragment!!)
        }
        homePagerAdapter= HomePagerAdapter(this,listOfFragment)
        viewPager?.adapter=homePagerAdapter
        setUpTab()
    }
    private fun setUpTab(){
//        tabLayout?.getTabAt(0)?.setIcon(tabIcons[0])?.text=names[0]
//        tabLayout?.getTabAt(1)?.setIcon(tabIcons[1])?.text=names[1]
//        tabLayout?.getTabAt(2)?.setIcon(tabIcons[2])?.text=names[2]
        if (tabLayout!=null && viewPager!=null) {
            TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
                tab.text = names[position]
                tab.setIcon(tabIcons[position])
            }.attach()
        }
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
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