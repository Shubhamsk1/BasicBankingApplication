package com.shubhamkudekar.bankingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SuccessPayment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_payment)
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent= Intent(this,AllCustomersActivity::class.java)
            startActivity(intent)
        },2000)


    }
}