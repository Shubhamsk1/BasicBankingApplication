package com.shubhamkudekar.bankingsystem

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shubhamkudekar.bankingsystem.Adapter.AllCustomerAdapter
import com.shubhamkudekar.bankingsystem.Adapter.SelectUserAdapter
import com.shubhamkudekar.bankingsystem.Adapter.UserClass
import com.shubhamkudekar.bankingsystem.data.UserContract
import com.shubhamkudekar.bankingsystem.data.UserDbHelper

class SelectUserActivity : AppCompatActivity() {
    lateinit var dbHelper: UserDbHelper
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var userList: ArrayList<UserClass>
    lateinit var adapter: SelectUserAdapter
    lateinit var message:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_user)
        var actionBar=supportActionBar
        if (actionBar != null) {
            actionBar.title = "Send TO..."
        }
        var bundle=intent.extras
        message= bundle!!.getString("id").toString()
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

        dbHelper=UserDbHelper(applicationContext)
        initDataRecyclerView()
        initRecyclerView()


    }



    private fun initDataRecyclerView() {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID, UserContract.UserEntry.COLUMN_NAME_USERNAME,
            UserContract.UserEntry.COLUMN_NAME_PHONE, UserContract.UserEntry.COLUMN_NAME_BALANCE
        )
        var cursor: Cursor = db.query(
            UserContract.UserEntry.TABLE_NAME,
            projection,
            UserContract.UserEntry._ID+"!="+message,null , null, null, null
        )

        userList = ArrayList()
        with(cursor) {
            while (moveToNext()) {
                (userList as ArrayList<UserClass>).add(
                    UserClass(
                        R.mipmap.ic_userimg,
                        cursor.getString(getColumnIndex(UserContract.UserEntry.COLUMN_NAME_USERNAME)),
                        cursor.getString(getColumnIndex(UserContract.UserEntry.COLUMN_NAME_PHONE)),
                        cursor.getString(getColumnIndex(UserContract.UserEntry.COLUMN_NAME_BALANCE))
                    )
                )

            }
        }

    }
    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.selectRecyclerView)
        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager
        adapter = SelectUserAdapter(userList,message,this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}