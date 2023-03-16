package com.mhmmdyzci.movie

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mhmmdyzci.movie.databinding.ActivityMainBinding
import com.mhmmdyzci.movie.databinding.ActivityOldMovieDetailsBinding


class OldMovieDetails : AppCompatActivity() {
    private lateinit var binding: ActivityOldMovieDetailsBinding
    private lateinit var db : FirebaseFirestore
    private lateinit var auth : FirebaseAuth
    private lateinit var comment : String
    private  lateinit var score : String
    private lateinit var oldId : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOldMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        comment = ""
        score = ""

        db = Firebase.firestore
        auth = Firebase.auth

        val intent = intent
        val oldMovieName = intent.getStringExtra("oldMovieName")
        val oldGenre = intent.getStringExtra("oldGenre")
        val oldUserMail = intent.getStringExtra("oldUserMail")
        oldId = intent.getStringExtra("id") as String
        binding.selectedOldMovieName.text= oldMovieName
        binding.selectedOldMovieGenre.text = oldGenre
        binding.addedUser.text = oldUserMail

        getData()

    }
    fun updateMovie(view: View){

         comment = binding.comment.text.toString()
         score = binding.score.text.toString()
        if (comment.isNotEmpty() && score.isNotEmpty()){
           db.collection("oldMovie").document(oldId).update("comment",comment,
        "score",score).addOnCompleteListener {
           }.addOnFailureListener {

           }
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(this,"Görüşleriniz Başarıyla Güncellendi",Toast.LENGTH_LONG).show()

    }else{
        Toast.makeText(this,"Boş alanları doldurunuz",Toast.LENGTH_LONG).show()

    }
    }
    fun getData(){
        db.collection("oldMovie").document(oldId).get().addOnSuccessListener { result ->
            var controlComment = result.get("comment")
            var controlScore = result.get("score")
            if(controlComment!!.equals("String") && controlScore!!.equals("String") ){

            }else{
                val dbComment = result.get("comment") as String
                val dbScore = result.get("score") as String
                binding.score.setText(dbScore)
                binding.comment.setText(dbComment)
            }
        }
    }



}