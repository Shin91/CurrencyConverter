package com.example.shin.currencyconverter;

public class ConvertCurrency {


    private Double x;
    private Double y;
    private Double z;
    private Double sum;
    ConvertCurrency(Double x, Double y, Double z) {

        this.x = x;
        this.y = y;
        this.z = z;

    }


    double convertCurrency() {

        sum = 0.00;

        sum = (x / y) * z;


        return sum;
    }

    @Override
    public String toString() {

        return String.format("%.2f" , sum );
    }
}
