package com.mhmmdyzci.kennyappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
    }
    fun start(view: View){
        intent = Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)

    }
}