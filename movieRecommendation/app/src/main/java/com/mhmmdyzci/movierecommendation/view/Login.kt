package com.mhmmdyzci.movierecommendation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mhmmdyzci.movierecommendation.databinding.ActivityLoginBinding
import com.mhmmdyzci.movierecommendation.databinding.ActivityMainBinding

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
    }
    fun signInClicked(view:View){

    }
    fun signUpClicked(view: View){
        var email = binding.userEmailText.text.toString()
        var password = binding.passwordText.text.toString()
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            Toast.makeText(this,"You Have Successfully Registered",Toast.LENGTH_LONG).show()

        }.addOnFailureListener {
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
        }



    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            var intent = Intent(this@Login,MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}