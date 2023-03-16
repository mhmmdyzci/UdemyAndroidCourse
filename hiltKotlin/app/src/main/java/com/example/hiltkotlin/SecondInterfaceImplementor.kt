package com.example.hiltkotlin

import javax.inject.Inject

class SecondInterfaceImplementor @Inject constructor() : MyInterface {
    override fun myPrintFuction(): String {
        return "My Interface Second Implementor"
    }
}