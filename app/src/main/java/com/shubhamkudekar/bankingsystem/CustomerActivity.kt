package com.shubhamkudekar.bankingsystem

import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shubhamkudekar.bankingsystem.Adapter.AllCustomerAdapter
import com.shubhamkudekar.bankingsystem.Adapter.UserClass
import com.shubhamkudekar.bankingsystem.data.UserContract.UserEntry.*
import com.shubhamkudekar.bankingsystem.data.UserDbHelper
import java.text.FieldPosition

class CustomerActivity : AppCompatActivity() {
    lateinit var name: TextView
    lateinit var phone:TextView
    lateinit var email:TextView
    lateinit var balance:TextView
    lateinit var transfer:Button
    lateinit var userDbHelper: UserDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
        name=findViewById(R.id.tv_name)
        phone=findViewById(R.id.tv_phone)
        email=findViewById(R.id.tv_email)
        balance=findViewById(R.id.tv_balance)
        transfer=findViewById(R.id.btn_transfer)
        userDbHelper=UserDbHelper(applicationContext)

        var bundle=intent.extras
        var position= bundle!!.getString("position")
        val x=position?.toInt()
        var cursor=assignValues(x)
        //the below shhould happen in select User Activity
        transfer.setOnClickListener{
            val intent=Intent(this,SelectUserActivity::class.java)
            intent.putExtra("id",cursor.getString(cursor.getColumnIndex(BaseColumns._ID)))
            startActivity(intent)

        }

    }

    private fun assignValues(position: Int?): Cursor {
        val db = userDbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID, COLUMN_NAME_USERNAME,
            COLUMN_NAME_PHONE, COLUMN_NAME_EMAIL,
                    COLUMN_NAME_BALANCE
        )
        var cursor: Cursor = db.query(
            TABLE_NAME,
            projection,
            null, null, null, null, null
        )
        var x=0
        cursor.moveToFirst()
        while(x!=position){
            cursor.moveToNext()
            x++
        }
        name.text=cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USERNAME))
        phone.text=cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PHONE))
        email.text=cursor.getString(cursor.getColumnIndex(COLUMN_NAME_EMAIL))
        balance.text="₹ "+cursor.getString(cursor.getColumnIndex(COLUMN_NAME_BALANCE))
        return cursor

    }


}