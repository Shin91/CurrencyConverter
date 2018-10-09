package com.example.shin.currencyconverter

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadDataFromURL {

    @Throws(IOException::class)
    fun downloadJSONDataFromURL(link: String): String {



            val stringBuilder: StringBuilder = StringBuilder()

            val url = URL(link)
            val urlConnection = url.openConnection() as HttpURLConnection
            try {

                val bufferedInputStream: BufferedInputStream = BufferedInputStream(urlConnection.inputStream)
                val bufferedReader: BufferedReader = BufferedReader(InputStreamReader(bufferedInputStream))

                var inputLine: String?
                inputLine = bufferedReader.readLine()

                while (inputLine != null) {

                    stringBuilder.append(inputLine)
                    inputLine = bufferedReader.readLine()

                }

            } finally {
                urlConnection.disconnect()
            }

            return stringBuilder.toString()

    }
}