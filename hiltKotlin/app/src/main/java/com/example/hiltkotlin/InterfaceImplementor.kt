package com.example.hiltkotlin

import javax.inject.Inject

class InterfaceImplementor
    @Inject constructor() : MyInterface {
    override fun myPrintFuction(): String {
        return "My Interface Implementor"
    }
}