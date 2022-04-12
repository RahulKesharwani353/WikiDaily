package com.example.wikidaily.randomArticle.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.wikidaily.R
import com.example.wikidaily.randomArticle.viewModel.ArticleMainViewModel
import kotlinx.coroutines.launch


class RandomArticleAdapter(private val lifecycleOwner: LifecycleOwner,
                           private val viewModel: ArticleMainViewModel,
                           private val activity: FragmentActivity
): RecyclerView.Adapter<RandomArticleAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.title)
        val desc = view.findViewById<TextView>(R.id.desc)
    }

    private var continuee:String =""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        viewModel.randomArticles.observe(lifecycleOwner){
            continuee = it.continuee.toString()
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_card,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        viewModel.randomArticles.observe(lifecycleOwner){
//            Log.d("adapterRand", it.query?.pages?.pageList?.get(position)?.pageid.toString())
//            holder.title.text = it.query?.pages?.pageList?.get(position)?.title.toString()
//            viewModel.viewModelScope.launch { viewModel.getRandArticleDetails(
//                titles =it.query?.pages?.pageList?.get(position)?.title.toString() )
//            }
//            holder.desc.text = viewModel.desc.value
//        }\
        var title: String =""

        viewModel.randomArticles.observe(lifecycleOwner){

            Log.d("RandAdapter", it.pages.get(position).title.toString())
             title = it.pages.get(position).title ?: "null"
            holder.title.text = title
        }

        viewModel.randomArticles.observe(lifecycleOwner) { item ->
            item.pages[position].content.observe(lifecycleOwner){
                holder.desc.text = it
                Log.d("RandDesc",it.toString())
            }
        }
        viewModel.viewModelScope.launch {
            viewModel.getRandArticleDetails(title, position)
        }

        if (position == itemCount - 1) {
            Toast.makeText(activity, "New Articles", Toast.LENGTH_SHORT).show()
            viewModel.viewModelScope.launch { viewModel.loadRandomPagesContinued() }
        }
    }

    override fun getItemCount(): Int {
        var size =0
        viewModel.randomArticles.observe(lifecycleOwner){
            size = it.pages.size
        }
        Log.d("RandSize", size.toString())
        return size;
    }
}