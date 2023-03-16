package com.mhmmdyzci.kotlinintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mhmmdyzci.kotlinintent.databinding.ActivityNextBinding

class NextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNextBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getData()


    }
    fun getData(){
        val intent = intent
        val userName = intent.getStringExtra("userName")
        val name = intent.getStringExtra("name")
        binding.userNameText.text = "Username: " + userName
        binding.nameTextNext.text = "Name: " + name


    }
}