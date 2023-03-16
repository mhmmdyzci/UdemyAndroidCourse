package com.mhmmdyzci.kennyappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mhmmdyzci.kennyappkotlin.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable { }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        timer()
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        hideImages()
    }
    fun increaseScore(view : View){

        score +=1
        binding.scoreText.text = "Score: $score"


    }
    fun timer(){
        object  : CountDownTimer(15000,1000){
            override fun onTick(p0: Long) {
                // her bir saniyede ne olcak (Burda text viewdeki 10 nu bir azaltcak)
                binding.timeText.text = "Left: ${p0 / 1000}"


            }

            override fun onFinish() {
                //işlem bitince ne olcak
                // runnable ı durduruyor
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                binding.timeText.text = "Left: 0"
                val alertDialog = AlertDialog.Builder(this@MainActivity)
                alertDialog.setTitle("Game Over")
                alertDialog.setMessage("Restart The Game?")
                alertDialog.setPositiveButton("Yes"){d, w ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alertDialog.setNegativeButton("No"){d,w->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }
                alertDialog.show()

            }
        }.start()
    }
    fun hideImages(){
        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }
}