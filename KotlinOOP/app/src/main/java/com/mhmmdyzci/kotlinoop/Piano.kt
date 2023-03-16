package com.mhmmdyzci.kotlinoop

class Piano : HouseDecor,Instrument{
    var brand: String? = null
    var digital : Boolean? = null
    override var roomName: String
        get() = "Kitchen"
        set(value) {}


}