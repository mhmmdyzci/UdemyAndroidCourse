package com.mhmmdyzci.movie

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mhmmdyzci.movie.adapter.OldMovieAdapter
import com.mhmmdyzci.movie.databinding.ActivityMainBinding
import com.mhmmdyzci.movie.databinding.ActivityWatched2Binding
import com.mhmmdyzci.movie.model.OldMovie
import org.w3c.dom.Comment


class watched2 : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var oldMovieArrayList: ArrayList<OldMovie>
    private lateinit var binding: ActivityWatched2Binding
    private lateinit var oldMovieAdapter: OldMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatched2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        db = Firebase.firestore
        auth = Firebase.auth
        oldMovieArrayList = ArrayList<OldMovie>()
        getdata()


        binding.recycleView.layoutManager = LinearLayoutManager(this)
        oldMovieAdapter = OldMovieAdapter(oldMovieArrayList)
        binding.recycleView.adapter = oldMovieAdapter


    }


    private fun getdata() {
        db.collection("oldMovie").addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
            }
            if (value != null) {
                if (!value.isEmpty) {
                    val documents = value.documents
                    oldMovieArrayList.clear()
                    for (document in documents) {
                        //casting
                        val oldUserEmail = document.get("oldUserEmail") as String
                        val oldMovieName = document.get("oldMovieName") as String
                        val oldMovieGenre = document.get("oldMovieGenre") as String
                        val oldId = document.id
                        val comment = document.get("comment") as? String
                        val score = document.get("score") as? String
                        val oldMovie = OldMovie(oldUserEmail, oldMovieName, oldMovieGenre,oldId,comment!!,score!!)
                        oldMovieArrayList.add(oldMovie)

                    }
                    oldMovieAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}


