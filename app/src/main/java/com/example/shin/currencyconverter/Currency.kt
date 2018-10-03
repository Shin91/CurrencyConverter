package com.example.shin.currencyconverter

class Currency(var currencyName : String, var currencyCode : String, var currencyMidPrice: Double, var time : String ) {

    constructor() : this(currencyName = "", currencyCode = "", currencyMidPrice = 0.00, time = "" )

    override fun toString(): String {
        return "$currencyCode $currencyName"
    }

    fun toStringMidPrice() : String{

        return "$currencyMidPrice"
    }

    fun toStringCurrentDate() : String{


        return time
    }
    fun toStringCodeAndPrice() : String{

        return "$currencyCode $currencyMidPrice"

    }


}
