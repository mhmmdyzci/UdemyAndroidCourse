package com.example.hiltkotlin

import javax.inject.Inject

class Musician
    @Inject
    constructor(instrument: Instrument, band: Band) {
    fun sing(){
        println("working..")

    }
}