package com.shubhamkudekar.bankingsystem

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Gravity.apply
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shubhamkudekar.bankingsystem.Adapter.AllCustomerAdapter
import com.shubhamkudekar.bankingsystem.Adapter.UserClass
import com.shubhamkudekar.bankingsystem.data.UserContract.UserEntry
import com.shubhamkudekar.bankingsystem.data.UserDbHelper

class AllCustomersActivity : AppCompatActivity() {
    lateinit var dbHelper: UserDbHelper
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var userList: ArrayList<UserClass>
    lateinit var adapter: AllCustomerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_all_customers)
        dbHelper=UserDbHelper(applicationContext)
        initDataSql()
        initDataRecyclerView()
        initRecyclerView()

    }

    override fun onBackPressed() {
            var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
    }

    private fun initDataRecyclerView() {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID, UserEntry.COLUMN_NAME_USERNAME,
            UserEntry.COLUMN_NAME_PHONE, UserEntry.COLUMN_NAME_BALANCE
        )
        var cursor: Cursor = db.query(
            UserEntry.TABLE_NAME,
            projection,
            null, null, null, null, null
        )

        userList = ArrayList()
        with(cursor) {
            while (moveToNext()) {
                (userList as ArrayList<UserClass>).add(
                    UserClass(
                        R.mipmap.ic_userimg,
                        cursor.getString(getColumnIndex(UserEntry.COLUMN_NAME_USERNAME)),
                        cursor.getString(getColumnIndex(UserEntry.COLUMN_NAME_PHONE)),
                        cursor.getString(getColumnIndex(UserEntry.COLUMN_NAME_BALANCE))
                    )
                )

            }
        }

    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager
        adapter = AllCustomerAdapter(userList)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun initDataSql() {
        val db = dbHelper.writableDatabase
        var values = ContentValues().apply {
            put(UserEntry.COLUMN_NAME_USERNAME, "Tushar")
            put(UserEntry.COLUMN_NAME_PHONE, "9812348990")
            put(UserEntry.COLUMN_NAME_BALANCE, "10000")
            put(UserEntry.COLUMN_NAME_EMAIL, "2shuux@gmail.com")
        }
        db?.insert(UserEntry.TABLE_NAME, null, values)
        values = ContentValues().apply {
            put(UserEntry.COLUMN_NAME_USERNAME, "Vidhi")
            put(UserEntry.COLUMN_NAME_PHONE, "9812348980")
            put(UserEntry.COLUMN_NAME_BALANCE, 9000)
            put(UserEntry.COLUMN_NAME_EMAIL, "vid2k@gmail.com")
        }
        db?.insert(UserEntry.TABLE_NAME, null, values)
        values = ContentValues().apply {
            put(UserEntry.COLUMN_NAME_USERNAME, "Ojha")
            put(UserEntry.COLUMN_NAME_PHONE, "9812548990")
            put(UserEntry.COLUMN_NAME_BALANCE, 8000)
            put(UserEntry.COLUMN_NAME_EMAIL, "ojha@gmail.com")
        }
        db?.insert(UserEntry.TABLE_NAME, null, values)
        values = ContentValues().apply {
            put(UserEntry.COLUMN_NAME_USERNAME, "Vivek")
            put(UserEntry.COLUMN_NAME_PHONE, "9712344990")
            put(UserEntry.COLUMN_NAME_BALANCE, 7000)
            put(UserEntry.COLUMN_NAME_EMAIL, "vivoj@gmail.com")
        }
        db?.insert(UserEntry.TABLE_NAME, null, values)
        values = ContentValues().apply {
            put(UserEntry.COLUMN_NAME_USERNAME, "Shubham")
            put(UserEntry.COLUMN_NAME_PHONE, "9333348990")
            put(UserEntry.COLUMN_NAME_BALANCE, 6000)
            put(UserEntry.COLUMN_NAME_EMAIL, "shubham@gmail.com")
        }
        db?.insert(UserEntry.TABLE_NAME, null, values)
        values = ContentValues().apply {
            put(UserEntry.COLUMN_NAME_USERNAME, "Rahul")
            put(UserEntry.COLUMN_NAME_PHONE, "9812300990")
            put(UserEntry.COLUMN_NAME_BALANCE, 5000)
            put(UserEntry.COLUMN_NAME_EMAIL, "buds@gmail.com")
        }
        db?.insert(UserEntry.TABLE_NAME, null, values)
        values = ContentValues().apply {
            put(UserEntry.COLUMN_NAME_USERNAME, "Harsh")
            put(UserEntry.COLUMN_NAME_PHONE, "9812954990")
            put(UserEntry.COLUMN_NAME_BALANCE, 4000)
            put(UserEntry.COLUMN_NAME_EMAIL, "harsh@gmail.com")
        }
        db?.insert(UserEntry.TABLE_NAME, null, values)
        values = ContentValues().apply {
            put(UserEntry.COLUMN_NAME_USERNAME, "geeta")
            put(UserEntry.COLUMN_NAME_PHONE, "9812697990")
            put(UserEntry.COLUMN_NAME_BALANCE, 3000)
            put(UserEntry.COLUMN_NAME_EMAIL, "geeta@gmail.com")
        }
        db?.insert(UserEntry.TABLE_NAME, null, values)
        values = ContentValues().apply {
            put(UserEntry.COLUMN_NAME_USERNAME, "Akansha")
            put(UserEntry.COLUMN_NAME_PHONE, "9515796990")
            put(UserEntry.COLUMN_NAME_BALANCE, 2000)
            put(UserEntry.COLUMN_NAME_EMAIL, "akansha@gmail.com")
        }
        db?.insert(UserEntry.TABLE_NAME, null, values)
        values = ContentValues().apply {
            put(UserEntry.COLUMN_NAME_USERNAME, "swati")
            put(UserEntry.COLUMN_NAME_PHONE, "9829022990")
            put(UserEntry.COLUMN_NAME_BALANCE, 1000)
            put(UserEntry.COLUMN_NAME_EMAIL, "geeta@gmail.com")
        }
        db?.insert(UserEntry.TABLE_NAME, null, values)


    }
}