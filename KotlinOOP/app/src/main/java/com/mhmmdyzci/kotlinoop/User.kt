package com.mhmmdyzci.kotlinoop

class User : People  {
    var name : String? = null
    var age : Int? = null
    //Bir obje oluşturulduğunda init constructordan daha önce çalışır
    constructor(name: String, age:Int){
        this.name = name
        this.age = age
        println("User Created")
    }
    init {
        println("init")
    }
}