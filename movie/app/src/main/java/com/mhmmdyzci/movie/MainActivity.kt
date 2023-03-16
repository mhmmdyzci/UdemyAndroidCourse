package com.mhmmdyzci.movie

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mhmmdyzci.movie.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var permissionLauncher : ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        registerLauncher()



    }
    fun signUpClicked(view:View){

        var email = binding.userEmailText.text.toString()
        var password = binding.passwordText.text.toString()
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            Toast.makeText(this@MainActivity,"You have successfully registered",Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun signInClicked(view:View){
        var email = binding.userEmailText.text.toString()
        var password = binding.passwordText.text.toString()
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            var intent = Intent(this@MainActivity,ChooseMovie::class.java)
            finish()
            startActivity(intent)
            Toast.makeText(this@MainActivity,"You have successfully logged in",Toast.LENGTH_LONG).show()

        }.addOnFailureListener {
            Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
        }
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.POST_NOTIFICATIONS)){
                Snackbar.make(view,"Bildirim için izine ihtiyacım var",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission",View.OnClickListener {
                    permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)

                }).show()
            }
            else{
                permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)

            }
        }


    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            var intent = Intent(this@MainActivity,ChooseMovie::class.java)
            finish()
            startActivity(intent)
        }
    }
    private fun registerLauncher(){
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ result ->
            if(result){
                if(ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this@MainActivity,"İzin verildi",Toast.LENGTH_LONG).show()

                }
            }else{
                Toast.makeText(this@MainActivity,"Permission needed",Toast.LENGTH_LONG).show()
            }
        }
    }



}