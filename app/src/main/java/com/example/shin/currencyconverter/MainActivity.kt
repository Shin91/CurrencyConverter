package com.example.shin.currencyconverter

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    val netConnection : CheckInternetConnection = CheckInternetConnection(this)
    private lateinit var spinnerCurrentCurrency: Spinner
    lateinit var txtCurrentPrice: TextView
    private lateinit var txtCurrenrDate: TextView
    private lateinit var spinnerCurrencyFrom: Spinner
    private lateinit var spinnerCurrencyTo: Spinner
    private lateinit var btnConvert: Button
    lateinit var txtConvertedCurrency: TextView
    lateinit var edtAmount: EditText
    var currFrom: Double = 0.00
    var currTo: Double = 0.00
    var amount: Double = 0.00


    lateinit var toolbar: android.support.v7.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var test = 0.00

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        txtCurrentPrice = findViewById(R.id.txtCurrentPriceView)
        txtCurrenrDate = findViewById(R.id.txtCurrentDate)
        spinnerCurrentCurrency = findViewById(R.id.spinCurrentCurrency)
        spinnerCurrencyFrom = findViewById(R.id.spinCurrencyFrom)
        spinnerCurrencyTo = findViewById(R.id.spinCurrencyTo)
        btnConvert = findViewById(R.id.btnConvert)
        edtAmount = findViewById(R.id.edtAmount)
        txtConvertedCurrency = findViewById(R.id.txtConvertedCurrency)

        if (this.netConnection.isConnected) {

            val innerClassObject = DownloadData()
            innerClassObject.execute()


            val result = innerClassObject.get()

            var spinArrAdapter: ArrayAdapter<Currency> = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, result)
            spinnerCurrentCurrency.adapter = spinArrAdapter

            spinnerCurrentCurrency.setSelection(8)
            txtCurrenrDate.text = result.first().toStringCurrentDate()

            spinnerCurrentCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    txtCurrentPrice.text = result.get(p2).toStringMidPrice()
                    test = result.get(p2).toStringMidPrice().toDouble()
                    println(test)
                }
            }


            spinnerCurrencyFrom.adapter = spinArrAdapter
            spinnerCurrencyTo.adapter = spinArrAdapter

            spinnerCurrencyFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    currFrom = result.get(p2).toStringMidPrice().toDouble()
                    println(currFrom)
                }
            }

            spinnerCurrencyTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    currTo = result.get(p2).toStringMidPrice().toDouble()
                }
            }



            btnConvert.setOnClickListener {

                try {


                    amount = edtAmount.text.toString().toDouble()

                    var convertedCurrency = ConvertCurrency(currFrom, currTo, amount)
                    convertedCurrency.convertCurrency()

                    txtConvertedCurrency.text = convertedCurrency.toString()
                } catch (e: Exception) {
                    Toast.makeText(this, "Podaj wartośc", Toast.LENGTH_SHORT).show()
                }
            }

        }else{

            var toast : Toast = Toast.makeText(this , "Sprawdź połączenie z ineternetem", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()

        }

    }
    inner class DownloadData : AsyncTask<String, Int, List<Currency>>() {




        override fun doInBackground(vararg p0: String?): List<Currency>? {

            val parseCurrency = ParseCurrency()
            return parseCurrency.parseCurrencyObjectFromJSONData()
        }
    }
}

