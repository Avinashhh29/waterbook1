package com.example.myapplication.myapplication.waterbook1.commons

import java.util.regex.Pattern

object Constant {
    const val BASE_URL = "https://api.postalpincode.in/pincode/"
    val PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[@#$%^&+=])" +
            "(?=\\S+$)" +
            ".{4,}" +
            "$")
    val PINCODE_PATTERN = Pattern.compile("^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}\$")
}