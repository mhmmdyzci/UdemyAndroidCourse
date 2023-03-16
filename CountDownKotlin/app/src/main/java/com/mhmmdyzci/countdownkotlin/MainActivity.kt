package com.mhmmdyzci.countdownkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.mhmmdyzci.countdownkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
    fun start(view: View){
        //Abstract sınıftan obje oluştururken Inheritance kullan
        object  : CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                // her bir saniyede ne olcak (Burda text viewdeki 10 nu bir azaltcak)
                binding.textView.text = "Left: ${p0 / 1000}"


            }

            override fun onFinish() {
                //işlem bitince ne olcak
                binding.textView.text = "Left: 0"
            }

        }.start()

    }
}