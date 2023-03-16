package com.mhmmdyzci.movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mhmmdyzci.movie.databinding.ActivityUploadMovieBinding

class UploadMovie : AppCompatActivity() {
    private lateinit var binding: ActivityUploadMovieBinding
    private lateinit var db : FirebaseFirestore
    private lateinit var auth : FirebaseAuth
    lateinit var movieName: String
    lateinit var movieGenre:String
    lateinit var information: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        db = Firebase.firestore

    }
    fun upload(view:View){
        movieName = binding.movieName.text.toString()
        movieGenre = binding.genre.text.toString()
        information = binding.information.text.toString()
        if(movieName.isNotEmpty() && movieGenre.isNotEmpty() && information.isNotEmpty() ) {

            val movieMap = hashMapOf<String, Any>()
            movieMap.put("movieName", movieName)
            movieMap.put("movieGenre", movieGenre)
            movieMap.put("information", information)
            movieMap.put("userEmail", auth.currentUser!!.email!!)
            movieMap.put("data", Timestamp.now())
            db.collection("newMovie")
                .add(movieMap)
                .addOnSuccessListener { documentReference ->
                    finish()
                    Toast.makeText(this,"Filminiz başarıyla eklendi",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this,e.localizedMessage,Toast.LENGTH_LONG).show()
                }
        }else{
            Toast.makeText(this@UploadMovie,"Boş alanları doldurunuz",Toast.LENGTH_LONG).show()
        }
    }
}