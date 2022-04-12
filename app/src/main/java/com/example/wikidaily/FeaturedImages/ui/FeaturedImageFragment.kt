package com.example.wikidaily.FeaturedImages.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wikidaily.FeaturedImages.Models.FeaturedImagesList
import com.example.wikidaily.FeaturedImages.Models.Page
import com.example.wikidaily.FeaturedImages.api.FeaturedImagesServices
import com.example.wikidaily.FeaturedImages.api.RetrofitHelper
import com.example.wikidaily.FeaturedImages.repo.FeaturedImagesRepo
import com.example.wikidaily.FeaturedImages.viewModels.MainViewModel
import com.example.wikidaily.FeaturedImages.viewModels.ViewModelFactory
import com.example.wikidaily.R
import com.example.wikidaily.databinding.FragmentFeaturedImageBinding
import kotlinx.coroutines.launch


class FeaturedImageFragment : Fragment() {

    lateinit var binding: FragmentFeaturedImageBinding
    lateinit var viewmodel: MainViewModel
    var imageList: FeaturedImagesList? = FeaturedImagesList()
     private var list : ArrayList<Page?> = ArrayList()
    private var temp : String = ""
    lateinit var adapter: FeaturedImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeaturedImageBinding.inflate(layoutInflater,container,false)

// viewModel BaNANA
        val featuredImagesServices = RetrofitHelper.getRetrofitBuilder().create(
            FeaturedImagesServices::class.java)
        val repo = FeaturedImagesRepo(featuredImagesServices)
        viewmodel = ViewModelProvider(this, ViewModelFactory(repo))[MainViewModel::class.java]
//
//

//
        viewmodel.viewModelScope.launch { viewmodel.getFeaturedImage() }
        fetchData();
        //setup RecyclerView
        binding.imageRecycleView.layoutManager = GridLayoutManager(this.requireContext(),2)
        adapter = FeaturedImagesAdapter(list, viewLifecycleOwner, viewmodel,requireActivity())

        binding.imageRecycleView.adapter = adapter

        return binding.root
    }

    private fun fetchData() {
        viewmodel.featuredImages.observe(viewLifecycleOwner){
            val result = it.query?.pages?.pageList
            if (result != null) {
                for (items in result)
                {
                    list.add(items)
                }

            }
            Log.d("items", list.toString())
            adapter.notifyItemInserted(viewmodel.imageListSize)
            viewmodel.imageListSize = list.size

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewmodel.isimageListchanged.observe(viewLifecycleOwner) {
//            if (viewmodel.isimageListchanged.value == true) {
//                adapter.notifyItemInserted(viewmodel.imageListSize)
//                viewmodel.featuredImages.observe(viewLifecycleOwner){
//                    viewmodel.imageListSize = it.query?.pages?.pageList?.size!!
//
//                }
////                viewmodel.isimageListchanged.value = false
//            }
//        }

//        binding.testText.text = list[0]?.imageinfo?.url

    }

}