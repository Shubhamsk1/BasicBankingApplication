package com.shubhamkudekar.bankingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.lang.NullPointerException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var startButton:Button
    lateinit var transactionButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        }catch (e:NullPointerException){}
        setContentView(R.layout.activity_main)
        startButton=findViewById(R.id.btn_start)
        transactionButton=findViewById(R.id.btn_transactions)
        startButton.setOnClickListener(this)
        transactionButton.setOnClickListener(this)


    }
    override fun onClick(v:View){
        if(v.id==R.id.btn_start){
            val intent:Intent=Intent(applicationContext,AllCustomersActivity::class.java)
            startActivity(intent)
        }
        if(v.id==R.id.btn_transactions){
            val intent:Intent=Intent(applicationContext,TransactionsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}

