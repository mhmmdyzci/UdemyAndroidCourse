package com.example.artbooktesting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.example.artbooktesting.R
import com.example.artbooktesting.roomdb.Art
import javax.inject.Inject

class ArtRecyclerAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<ArtRecyclerAdapter.ArtHolder>() {
    class ArtHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



    // İki liste arasındaki farkı hesaplayan ve böylece reycleview in gerekli kısımlarını güncelleyen bir sınıf
    private val diffUtil = object : DiffUtil.ItemCallback<Art>() {
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            // itemler ayınımı
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }
    }
    private var recyclerListDiffer = AsyncListDiffer(this,diffUtil)

    var arts : List<Art>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_row,parent,false)
        return ArtHolder(view)
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.artRowImageView)
        val nameText = holder.itemView.findViewById<TextView>(R.id.artRowNameText)
        val artistNameText = holder.itemView.findViewById<TextView>(R.id.artRowArtistNameText)
        val yearText = holder.itemView.findViewById<TextView>(R.id.artRowYearText)
        val art = arts[position]
        holder.itemView.apply {
            nameText.text = "Name: ${art.name}"
            artistNameText.text = "Artist Name: ${art.artistName} "
            yearText.text = "Year: ${art.year}"
            glide.load(art.ImageUrl).into(imageView)


        }

    }

    override fun getItemCount(): Int {
        return arts.size
    }


}