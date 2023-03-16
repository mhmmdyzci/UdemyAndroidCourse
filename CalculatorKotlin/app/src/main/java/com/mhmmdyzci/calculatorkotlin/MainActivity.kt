package com.mhmmdyzci.calculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mhmmdyzci.calculatorkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
     var number1 : Int? = null
     var number2 : Int? = null
     var result : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun mySum(view: View){
         number1 = binding.editText.toString().toIntOrNull()
         number2 = binding.editText2.toString().toIntOrNull()
        if (number1 == null || number2 == null){
            binding.resultText.text = "Error"

        }else{
            result = number1!! + number2!!
            binding.resultText.text = result.toString()

        }

    }

    fun mySub(view: View){
        number1 = binding.editText.toString().toIntOrNull()
        number2 = binding.editText2.toString().toIntOrNull()
        if (number1 == null || number2 == null){
            binding.resultText.text = "Error"

        }else{
            result = number1!! - number2!!
            binding.resultText.text = result.toString()

        }

    }
    fun myMultiply(view: View){
        number1 = binding.editText.toString().toIntOrNull()
        number2 = binding.editText2.toString().toIntOrNull()
        if (number1 == null || number2 == null){
            binding.resultText.text = "Error"

        }else{
            result = number1!! * number2!!
            binding.resultText.text = result.toString()

        }

    }

    fun myDiv(view: View){
        number1 = binding.editText.toString().toIntOrNull()
        number2 = binding.editText2.toString().toIntOrNull()
        if (number1 == null || number2 == null){
            binding.resultText.text = "Error"

        }else{
            result = number1!! / number2!!
            binding.resultText.text = result.toString()

        }

    }




}