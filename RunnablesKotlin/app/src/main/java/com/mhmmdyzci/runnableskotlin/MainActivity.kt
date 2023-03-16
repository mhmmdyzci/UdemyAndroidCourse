package com.mhmmdyzci.runnableskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.mhmmdyzci.runnableskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var runnable : Runnable = Runnable {}
    var handler : Handler = Handler(Looper.getMainLooper())
    var number = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun start(view: View){
        number = 0
        runnable = object  : Runnable{
            override fun run() {
                number += 1
                binding.textView.text = "Time: $number "
                handler.postDelayed(this,1000)
            }

        }
        handler.post(runnable)
    }
    fun stop(view:View){
        handler.removeCallbacks(runnable)
        number= 0
        binding.textView.text = "Time: 0"

    }
}