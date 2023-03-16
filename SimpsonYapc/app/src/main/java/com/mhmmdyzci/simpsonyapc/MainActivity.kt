package com.mhmmdyzci.simpsonyapc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mhmmdyzci.simpsonyapc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun makeSimpson(view:View){
         name = binding.nameText.text.toString()
        var age = binding.ageText.text.toString().toIntOrNull()
        var job = binding.jobText.text.toString()
        val simpson = Simpson(name,age,job)
        binding.textView.text= "Name: ${simpson.name} age: ${simpson.name} job: ${simpson.job} "
    }
    fun scopeExample(view: View){
        //Scope
        println(name)



    }

}