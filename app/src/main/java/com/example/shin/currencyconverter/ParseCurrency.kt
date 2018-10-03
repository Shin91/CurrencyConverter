package com.example.shin.currencyconverter

import org.json.JSONArray

class ParseCurrency {


    fun parseCurrencyObjectFromJSONData(): List<Currency>? {

        var allCurrencyObjects: ArrayList<Currency> = ArrayList()
        var downloadingDataFromURL = DownloadDataFromURL()
        var topLevelCurrencyJSONData = downloadingDataFromURL.downloadJSONDataFromURL("http://api.nbp.pl/api/exchangerates/tables/a/?format=json")
        var currency = Currency()

        var jsonArray: JSONArray = JSONArray(topLevelCurrencyJSONData)
        currency.time = jsonArray.getJSONObject(0).getString("effectiveDate")


        var jsonArray2: JSONArray? = jsonArray.getJSONObject(0).getJSONArray("rates")


        var index: Int = 0
        currency.currencyName = "polski złoty"
        currency.currencyCode = "PLN"
        currency.currencyMidPrice = 1.00
        allCurrencyObjects.add(currency)
        while (index < jsonArray2!!.length()) {

            var currencyObject: Currency = Currency()
            var jsonObject = jsonArray2.getJSONObject(index)


            with(jsonObject) {
                currencyObject.currencyName = getString("currency")
                currencyObject.currencyCode = getString("code")
                currencyObject.currencyMidPrice = getDouble("mid")
            }
            currencyObject.time = currency.time

            allCurrencyObjects.add(currencyObject)
            println(currencyObject.currencyName + " " + currencyObject.time)
            index++
        }

        return allCurrencyObjects
    }
}