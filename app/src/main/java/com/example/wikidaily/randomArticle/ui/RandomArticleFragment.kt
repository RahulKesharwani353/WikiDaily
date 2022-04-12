package com.example.wikidaily.randomArticle.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wikidaily.FeaturedImages.ui.FeaturedImagesAdapter
import com.example.wikidaily.R
import com.example.wikidaily.WikiAppilcation
import com.example.wikidaily.databinding.FragmentFeaturedImageBinding
import com.example.wikidaily.databinding.FragmentRandomArticleBinding
import com.example.wikidaily.randomArticle.viewModel.ArticleMainViewModel
import com.example.wikidaily.randomArticle.viewModel.ViewModelFactory
import kotlinx.coroutines.launch

class RandomArticleFragment : Fragment() {



    lateinit var viewmodel : ArticleMainViewModel
    lateinit var binding : FragmentRandomArticleBinding
    lateinit var adapter: RandomArticleAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRandomArticleBinding.inflate(layoutInflater,container,false)

        val repo= (activity?.application as WikiAppilcation).randomArticleRepo
        viewmodel = ViewModelProvider(this, ViewModelFactory(repo))[ArticleMainViewModel::class.java]
        viewmodel.viewModelScope.launch { viewmodel.getRandomArticle() }
        fetchData()



        binding.randomArticleRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = RandomArticleAdapter( viewLifecycleOwner, viewmodel, requireActivity())

        binding.randomArticleRecyclerView.adapter = adapter

        return binding.root
    }

    private fun fetchData() {
        Log.d("random", "aya")
        viewmodel.randomArticles.observe(viewLifecycleOwner){
            val result = it.pages
//            if (result != null) {
//                for (items in result)
//                {
//                    list.add(items)
//                }
//
//            }
            Log.d("itemsArticle", result.toString())
            adapter.notifyItemInserted(0)


        }
    }

}