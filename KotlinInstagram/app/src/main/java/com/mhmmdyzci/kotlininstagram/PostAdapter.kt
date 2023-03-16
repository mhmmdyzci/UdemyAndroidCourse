package com.mhmmdyzci.kotlininstagram

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhmmdyzci.kotlininstagram.databinding.RecyclerViewRowBinding
import com.squareup.picasso.Picasso

class PostAdapter(var postArrayList : ArrayList<Post> ) : RecyclerView.Adapter<PostAdapter.PostHolder>() {
    class PostHolder(val binding: RecyclerViewRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {

        val binding = RecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.recyclerCommentText.text = postArrayList.get(position).comment
        holder.binding.recyclerEmailText.text = postArrayList.get(position).email
        Picasso.get().load(postArrayList.get(position).downloadUrl).into(holder.binding.recyclerImageView)

    }

    override fun getItemCount(): Int {
        return postArrayList.size
    }
}