package com.mhmmdyzci.kotlinoop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //constructor
       var myUser = User("John",32)
        //Encapsulation
        var james = Musician("James","Guitar",50)
        println(james.age)
        println(james.returnBandName("Atil"))

        //Inheritance
        var lars = SuperMusician("Lars","Drums",63)
        println(lars.name)
        println(lars.age)
        println(lars.returnBandName("Atil"))
        lars.sing()

        //Polymorphism
        //Static polymorphism
        var mat = Mathematics()
        println(mat.sum())
        println(mat.sum(3,4))
        println(mat.sum(3,4,5))
        //Dynmaic Polymorphism
        var animal = Animal()
        animal.sing()

        var barley = Dog()
        barley.test()
        barley.sing()

        //Absratc & interface
        myUser.information()
        //interface
        var myPiano = Piano()
        myPiano.brand = "Yamaha"
        myPiano.digital = false
        println(myPiano.roomName)
        myPiano.info()

        //Lambda Expressions
        fun printString(myString: String){
            println(myString)
        }
        printString("My Test String")

        val textString = {myString : String -> println(myString) }
        textString("My Lambda String")

        val multiplyLamba = {a: Int , b: Int -> a*b}
        println(multiplyLamba(3,2))

        val multiplyLamba2 : (Int, Int) -> Int = {a,b -> a*b}
        println(multiplyLamba2(5,5))

        //Asynchrnous
        //callback function, Listener function, completion function
        fun downloadMusicians(url:String, completion : (Musician) -> Unit){
            //download
            //data
         var kirk = Musician("Kirk","Guitar",34)
            completion(kirk)
        }
        downloadMusicians("metalica.com", {musician ->
         println(musician.age) })


    }

}