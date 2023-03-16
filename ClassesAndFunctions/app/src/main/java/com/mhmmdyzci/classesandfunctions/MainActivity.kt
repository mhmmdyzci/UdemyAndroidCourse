package com.mhmmdyzci.classesandfunctions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mySum(10,20)
        //Void -- Unit (Bir şey döndürmüyor)
        button.setOnClickListener {
            println("clicked")
        }
        val karakter = Hımym("Barney",31,"PLEASSEE")
        println(karakter.name)

        //Nullability
        //Nullable (?) & Non-null
        var myString : String? = null
        var myAge : Int? = null
        println(myAge!! * 10)

        // !! kesin değer var diyosun

        // Null safety
        if (myAge != null){
            println(myAge * 10)
        }else{
            println("myAge null")
        }

        // Safe call
        println(myAge?.compareTo(2))

        //elvis
        val myResult = myAge?.compareTo(2) ?:-100
        println(myResult)




    }
    fun test(){
        println("test function")

    }
    //input & Return
    fun mySum(a: Int, b: Int){

        textView.text = "${a+b}"

    }
    fun myMultiply(x:Int, y:Int) :Int{
        return x * y

    }
    fun helloKotlin(view : View){
        println("Hello Kotlin")


    }
}