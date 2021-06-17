package com.shubhamkudekar.bankingsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shubhamkudekar.bankingsystem.Adapter.TransactionAdapter
import com.shubhamkudekar.bankingsystem.Adapter.TransactionClass
import com.shubhamkudekar.bankingsystem.data.TransactionDbHelper
import com.shubhamkudekar.bankingsystem.data.TransactionsContract.TransactionsEntry.*

class TransactionsActivity : AppCompatActivity() {
    lateinit var transactionDbHelper: TransactionDbHelper
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TransactionAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var transactionList:ArrayList<TransactionClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_transactions)
        transactionDbHelper= TransactionDbHelper(applicationContext)
        initDataRecyclerView()
        initRecyclerView()

    }

    private fun initDataRecyclerView() {
        val db=transactionDbHelper.readableDatabase
        val projection= arrayOf(
            BaseColumns._ID, TRANSACTION_COLUMN_FROM,
            TRANSACTION_COLUMN_TO, TRANSACTION_COLUMN_AMOUNT
        )
        var cursor=db.query(
            TRANSACTION_TABLE_NAME,
            projection,
            null,
            null,null,null,null
        )
        transactionList= ArrayList()
        with(cursor){
            while(moveToNext()){
                transactionList.add(
                    TransactionClass(
                        getString(getColumnIndex(TRANSACTION_COLUMN_FROM)),
                        getString(getColumnIndex(TRANSACTION_COLUMN_TO)),
                        "₹ "+getInt(getColumnIndex(TRANSACTION_COLUMN_AMOUNT))
                    )
                )
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView=findViewById(R.id.transaction_recycler_view)
        layoutManager= LinearLayoutManager(this)
        layoutManager.orientation=RecyclerView.VERTICAL
        recyclerView.layoutManager=layoutManager
        adapter=TransactionAdapter(transactionList)
        recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()

    }
}