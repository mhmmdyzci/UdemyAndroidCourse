package com.mhmmdyzci.storingdatakotlin
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mhmmdyzci.storingdatakotlin.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences : SharedPreferences
     var myAge : Int? = null
     var ageFromDatabase : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPreferences = this.getSharedPreferences("com.mhmmdyzci.storingdatakotlin", MODE_PRIVATE)
        ageFromDatabase = sharedPreferences.getInt("age",-1)
        if (ageFromDatabase == -1  ){
            binding.textView.text = "Your age: "
        }else{
            binding.textView.text = "Your age: " + ageFromDatabase
        }

    }

    fun save(view: View){
        //SharedPreferences
        myAge = binding.textView.toString().toIntOrNull()
        if (myAge != null){
            binding.textView.text = "Your age: " + myAge
            sharedPreferences.edit().putInt("age",myAge!!).apply()

        }else{
            binding.textView.text = "Error"
        }
    }
    fun delete(view:View){
        ageFromDatabase = sharedPreferences.getInt("age",-1)
        if (ageFromDatabase !=-1 ){
            sharedPreferences.edit().remove("age").apply()
            binding.textView.text = "Your age: "

        }


    }
}