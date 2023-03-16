package com.mhmmdyzci.movie

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.mhmmdyzci.movie.model.NewMovie
import kotlinx.android.synthetic.main.activity_choose_movie.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf


class ChooseMovie : AppCompatActivity() {
    private lateinit var db : FirebaseFirestore
    private lateinit var movieArrayList : ArrayList<NewMovie>
    private lateinit var auth : FirebaseAuth
    private lateinit var selectedUserMail : TextView
    private lateinit var selectedMovieName : TextView
    private lateinit var selectedGenre : TextView
    private lateinit var selectedInformation : TextView
    private lateinit var userEmail : String
    private lateinit var movieName : String
    private lateinit var movieGenre : String
    private lateinit var info : String
    private lateinit var id : String
    private lateinit var selectedId : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_movie)
        db = Firebase.firestore
        auth = Firebase.auth
        movieArrayList = ArrayList<NewMovie>()
        selectedId = ""



    }



    fun chooseMovie(view: View) {
        db.collection("newMovie")
            .get()
            .addOnSuccessListener { result ->
                movieArrayList.clear()
                if(result.isEmpty){
                    Toast.makeText(this,"Yeterli film yok",Toast.LENGTH_LONG).show()


                }else{

                    val dialog = Dialog(this)
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    dialog.setCancelable(false)
                    dialog.setContentView(R.layout.popup)
                    selectedUserMail = dialog.findViewById(R.id.selectedUserMail) as TextView
                    selectedMovieName = dialog.findViewById(R.id.selectedMovieName) as TextView
                    selectedGenre = dialog.findViewById(R.id.selectedMovieGenre) as TextView
                    selectedInformation = dialog.findViewById(R.id.selectedMovieInformation) as TextView

                    for (document in result) {
                        userEmail = document.get("userEmail") as String
                        movieName = document.get("movieName") as String
                        movieGenre = document.get("movieGenre") as String
                        info = document.get("information") as String
                        id = document.id
                        val newMovie = NewMovie(id,userEmail,movieName,movieGenre,info)
                        movieArrayList.add(newMovie)
                    }
                    val random = (0..movieArrayList.size - 1).shuffled().last()
                    selectedUserMail.text = movieArrayList.get(random).userEmail
                    selectedMovieName.text = movieArrayList.get(random).movieName
                    selectedGenre.text = movieArrayList.get(random).movieGenre
                    selectedInformation.text = movieArrayList.get(random).information
                    selectedId = movieArrayList.get(random).id

                    val yesBtn = dialog.findViewById(R.id.like) as Button
                    val noBtn = dialog.findViewById(R.id.dontLıke) as TextView
                    yesBtn.setOnClickListener {



                        db.collection("newMovie").document(selectedId).delete()
                            .addOnSuccessListener { Toast.makeText(this,"İyi Seyirler, Yorum yapmayı unutmayın",Toast.LENGTH_LONG).show() }
                            .addOnFailureListener { e -> Toast.makeText(this,e.localizedMessage,Toast.LENGTH_LONG).show() }

                        val oldMovieMap = hashMapOf<String, Any>()
                        oldMovieMap["oldMovieName"] = selectedMovieName.text
                        oldMovieMap["oldMovieGenre"] = selectedGenre.text
                        oldMovieMap["oldInformation"] = selectedInformation.text
                        oldMovieMap["oldUserEmail"] = auth.currentUser!!.email!!
                        oldMovieMap["comment"] = "String"
                        oldMovieMap["score"] = "String"
                        oldMovieMap["oldDate"] = Timestamp.now()


                        db.collection("oldMovie")
                            .add(oldMovieMap)
                            .addOnFailureListener { e ->
                                Toast.makeText(this,e.localizedMessage,Toast.LENGTH_LONG).show()
                            }
                        dialog.dismiss()
                    }
                    noBtn.setOnClickListener { dialog.dismiss() }
                    dialog.show()

                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@ChooseMovie,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.movie_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_movie){
            val intent = Intent(this,UploadMovie::class.java)
            startActivity(intent)
        }else if(item.itemId==R.id.log_out){
            val intent = Intent(this,MainActivity::class.java)
            auth.signOut()
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }else{
            val intent = Intent(this,watched2::class.java)
            startActivity(intent)

        }
        return super.onOptionsItemSelected(item)
    }
}