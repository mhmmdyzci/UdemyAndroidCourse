package com.mhmmdyzci.movie.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhmmdyzci.movie.OldMovieDetails
import com.mhmmdyzci.movie.databinding.RecyclerviewRowBinding
import com.mhmmdyzci.movie.model.OldMovie

class OldMovieAdapter(val oldMovieArrayList: ArrayList<OldMovie>) : RecyclerView.Adapter<OldMovieAdapter.OldMovieHolder>() {
    class OldMovieHolder(val binding: RecyclerviewRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OldMovieHolder {
        val binding = RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OldMovieHolder(binding)
    }

    override fun onBindViewHolder(holder: OldMovieHolder, position: Int) {
        holder.binding.recyclerViewTextView.text = oldMovieArrayList.get(position).oldMovieName
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,OldMovieDetails::class.java)
            intent.putExtra("id",oldMovieArrayList.get(position).oldId)
            intent.putExtra("comment",oldMovieArrayList.get(position).comment)
            intent.putExtra("score",oldMovieArrayList.get(position).score)
            intent.putExtra("oldMovieName",oldMovieArrayList.get(position).oldMovieName)
            intent.putExtra("oldGenre",oldMovieArrayList.get(position).oldMovieGenre)
            intent.putExtra("oldUserMail",oldMovieArrayList.get(position).oldUserEmail)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return oldMovieArrayList.size
    }
}