package com.example.hiltkotlin

import com.google.gson.Gson
import javax.inject.Inject

class ClassExample@Inject
constructor(@FirstImplementor private val myInterfaceImplementor: MyInterface,
            private var gson : Gson,
            @SecondImplementor private val mySecondInterfaceImplementor : MyInterface
) {
    fun myFunction () : String {
        return "Working: ${myInterfaceImplementor.myPrintFuction()}"
    }

    fun secondFunction () : String {
        return "Working: ${mySecondInterfaceImplementor.myPrintFuction()}"
    }
}