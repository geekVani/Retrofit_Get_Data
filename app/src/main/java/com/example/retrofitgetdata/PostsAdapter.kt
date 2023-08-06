package com.example.retrofitgetdata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.databinding.CustomPostsBinding

/*
* create array list just like in mainactivity
* create constructor to send the data to main activity from adapter class
* right click-> generate -> secondary constructor -> Ok -> hover -> convert to primary constructor
* not using item view but adapterbinding object to access textviews directly using view binding
* inherit PostsAdapter from Recyclerview.adapter class
* onCreateViewHolder: as using view binding, don't create object of view class and return it
    * but use custompost binding
* create object of adapter class in main activity
*/

class PostsAdapter(var postsList: ArrayList<Posts>)
    : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    inner class PostsViewHolder(val adapterBinding: CustomPostsBinding)
        : RecyclerView.ViewHolder(adapterBinding.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = CustomPostsBinding.inflate(LayoutInflater.from(parent.context)
            , parent, false)
        return PostsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {

        // displaying data in textview
        holder.adapterBinding.textViewUserID.text = postsList[position].userId.toString()
        holder.adapterBinding.textViewId.text = postsList[position].id.toString()
        holder.adapterBinding.textViewTitle.text = postsList[position].title
        holder.adapterBinding.textViewBody.text = postsList[position].subtitle
    }

}