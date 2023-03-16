package com.mhmmdyzci.viewbindingkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.mhmmdyzci.viewbindingkotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    //private lateinit var textView: TextView
    // Lateinit var -> seni daha sonra tanımlıcam
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //textView = findViewById(R.id.textView)

    }
    fun changeName(view : View){
        //textView.text = "Merhaba kotlin"
        /*val textView = findViewById<TextView>(R.id.textView)
        textView.text="Merhaba kotlin"
         */
        binding.textView.text = "Merhaba kotlin"


    }
}