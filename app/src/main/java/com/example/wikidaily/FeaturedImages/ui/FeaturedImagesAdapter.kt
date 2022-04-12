package com.example.wikidaily.FeaturedImages.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wikidaily.FeaturedImages.Models.Page
import com.example.wikidaily.FeaturedImages.viewModels.MainViewModel
import com.example.wikidaily.R
import com.example.wikidaily.databinding.ImageCardBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeaturedImagesAdapter(private val list : ArrayList<Page?>, private val lifecycleOwner: LifecycleOwner,
                            private val viewModel: MainViewModel,
                            private val activity: FragmentActivity
) :  RecyclerView.Adapter<FeaturedImagesAdapter.MyViewHolder>() {

    private var continuee : String =""
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        viewModel.featuredImages.observe(lifecycleOwner){
            continuee = it.continuee?.gcmcontinue.toString()
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_card,parent,false)

        return  MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        viewModel.featuredImages.observe(lifecycleOwner){
            Log.d("adapterNew", it.query?.pages?.pageList?.get(position)?.pageid.toString())
            setImage(holder.img,
                it.query?.pages?.pageList?.get(position)?.imageinfo?.url.toString(),position)
        }

//        viewModel.imageList?.query?.pages?.pageList?.get(position)?.imageinfo?.url?.let {
//            //   holder.binding.imageView.setImageDrawable(holder.itemView.resources.getDrawable(R.drawable.ic_broken_image))
//            viewModel.viewModelScope.launch(Dispatchers.IO) {
//                setImage(holder.binding.imageView, it, position)
//            }
//        }
        if (position == itemCount - 1) {
            viewModel.viewModelScope.launch { viewModel.continueLoadingImages(continuee) }
            Toast.makeText(activity, "New Page", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        var size =0
        viewModel.featuredImages.observe(lifecycleOwner){
             size = it.query?.pages?.pageList?.size!!
        }
        Log.d("size", size.toString())
        return size;
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img = view.findViewById<ImageView>(R.id.imageView)
    }

    private fun setImage(imgView: ImageView, url: String, pos: Int) {
        Glide.with(activity)
            .load(url)
            .placeholder(R.drawable.broken)
            .into(imgView)
    }

}