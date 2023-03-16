package com.mhmmdyzci.kotlininstagram

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mhmmdyzci.kotlininstagram.databinding.ActivityFeedBinding
import com.mhmmdyzci.kotlininstagram.databinding.ActivityMainBinding

class FeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db : FirebaseFirestore
    lateinit var postArrayList : ArrayList<Post>
    lateinit var postAdapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        db = Firebase.firestore
        postArrayList = ArrayList<Post>()
        getData()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        postAdapter = PostAdapter(postArrayList)
        binding.recyclerView.adapter = postAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.feed_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.log_out){
            auth.signOut()
            var intent = Intent(this@FeedActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            var intent = Intent(this@FeedActivity,UploadActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
    private fun getData(){
        db.collection("Posts").orderBy("data",Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if (error != null){
                Toast.makeText(this@FeedActivity,error.localizedMessage,Toast.LENGTH_LONG).show()
            }
            if(value !=null){
                if (!value.isEmpty){
                    val documents = value.documents
                    postArrayList.clear()
                    for(document in documents){
                        //casting
                        val comment = document.get("comment") as String
                        val userEmail = document.get("userEmail") as String
                        val downloadUrl = document.get("downloadUrl") as String
                        val post = Post(userEmail,comment,downloadUrl)
                        postArrayList.add(post)

                    }
                    postAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}