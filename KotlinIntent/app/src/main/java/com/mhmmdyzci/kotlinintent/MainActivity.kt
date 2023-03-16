package com.mhmmdyzci.kotlinintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mhmmdyzci.kotlinintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //YAŞAM DÖNGÜSÜNE BAK
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    //Context haberdar yapı
    fun next(view: View){
        val intent = Intent(applicationContext,NextActivity::class.java)
        intent.putExtra("userName",binding.userNameText.text.toString())
        intent.putExtra("name",binding.nameText.text.toString())
        startActivity(intent)
        //önceki aktiviteyi kapatır
        finish()
    }
}