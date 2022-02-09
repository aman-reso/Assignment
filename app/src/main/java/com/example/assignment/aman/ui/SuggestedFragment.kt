package com.example.assignment.aman.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.aman.adapter.HomeAdapter
import com.example.assignment.aman.viewmodels.HomeViewModel
import com.example.assignment.aman.viewmodels.distinctUntilChanged
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SuggestedFragment() : Fragment() {
    private var recyclerView: RecyclerView?=null
    private val mAdapter: HomeAdapter by lazy { HomeAdapter() }
    private val homeViewModel: HomeViewModel? by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        setUpRecyclerView(view)
        setUpObserver()
        return view;
    }
   private fun setUpRecyclerView(view: View?) {
       recyclerView=view?.findViewById(R.id.homeRecyclerView)
       recyclerView?.apply {
           layoutManager=LinearLayoutManager(context)
           adapter=mAdapter
       }
    }

   private fun setUpObserver(){
       homeViewModel?.liveData?.distinctUntilChanged()?.observe(viewLifecycleOwner){
           if (it!=null){
               System.out.println("contactListSize"+it.contactSuggestionList.size)
               System.out.println("dummy"+it.friendSugesstionList)
               mAdapter.submitList(it.friendSugesstionList)
           }
       }
       homeViewModel?.callApiRequest()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {
        @JvmStatic
        fun newInstance() = SuggestedFragment().apply {

        }
    }

}