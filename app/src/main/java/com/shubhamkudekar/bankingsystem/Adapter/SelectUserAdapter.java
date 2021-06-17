package com.shubhamkudekar.bankingsystem.Adapter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamkudekar.bankingsystem.AllCustomersActivity;
import com.shubhamkudekar.bankingsystem.CustomerActivity;
import com.shubhamkudekar.bankingsystem.R;
import com.shubhamkudekar.bankingsystem.SelectUserActivity;
import com.shubhamkudekar.bankingsystem.SuccessPayment;
import com.shubhamkudekar.bankingsystem.data.TransactionDbHelper;
import com.shubhamkudekar.bankingsystem.data.TransactionsContract;
import com.shubhamkudekar.bankingsystem.data.UserContract;
import com.shubhamkudekar.bankingsystem.data.UserDbHelper;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns.*;
import static com.shubhamkudekar.bankingsystem.data.TransactionsContract.TransactionsEntry.TRANSACTION_COLUMN_AMOUNT;
import static com.shubhamkudekar.bankingsystem.data.TransactionsContract.TransactionsEntry.TRANSACTION_COLUMN_FROM;
import static com.shubhamkudekar.bankingsystem.data.TransactionsContract.TransactionsEntry.TRANSACTION_COLUMN_TO;
import static com.shubhamkudekar.bankingsystem.data.TransactionsContract.TransactionsEntry.TRANSACTION_TABLE_NAME;
import static com.shubhamkudekar.bankingsystem.data.UserContract.*;
import static com.shubhamkudekar.bankingsystem.data.UserContract.UserEntry.*;
import com.shubhamkudekar.bankingsystem.data.TransactionsContract.TransactionsEntry.*;

public class SelectUserAdapter extends  RecyclerView.Adapter<SelectUserAdapter.ViewHolder>{


    @NonNull
    private List<UserClass>userClassList;
    UserDbHelper userDbHelper;
    TransactionDbHelper transactionDbHelper;
    Context context;
    public  String message;
    public SelectUserAdapter(List<UserClass> userList, String msg, Context context){
        this.userClassList=userList;
        this.message=msg;
        this.context=context;

    }
    @Override
    public SelectUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_design,parent,false);
        userDbHelper= new UserDbHelper(parent.getContext());
        transactionDbHelper=new TransactionDbHelper(parent.getContext());
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        int resource=userClassList.get(position).getImageview();
        String userName=userClassList.get(position).getUserName();
        String phone=userClassList.get(position).getPhone();
        String curBalance="₹ " + userClassList.get(position).getCurBalance();
        holder.setData(resource,userName,phone,curBalance);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText edittext = new EditText(holder.itemView.getContext());
                AlertDialog.Builder alert =new AlertDialog.Builder(holder.itemView.getContext());
                alert.setMessage("Enter the amount");
                alert.setView(edittext);
                alert.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String phone=userClassList.get(position).getPhone();
                        String value=edittext.getText().toString();
                        assignValToTransaction(phone,value);
                        Intent intent=new Intent(context, SuccessPayment.class);
                        context.startActivity(intent);
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.itemView.getContext(),"Transaction Cancelled",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context,AllCustomersActivity.class);
                        context.startActivity(intent);
                    }
                });
                alert.show();

            }
        });



    }

    private void assignValToTransaction(String phone,String value) {
        SQLiteDatabase db1=userDbHelper.getReadableDatabase();
        String[] projection1 = {
                UserEntry._ID,
                COLUMN_NAME_USERNAME,
                COLUMN_NAME_PHONE,
                COLUMN_NAME_BALANCE
        };
        Cursor cursor1 =db1.query(
                TABLE_NAME,
                projection1,
                null,
                null,
                null,
                null,
                null
        );
        String from = "",to="";
        while(cursor1.moveToNext()){
            String s=cursor1.getString(cursor1.getColumnIndex(_ID));
            if(s.compareTo(message)==0){
                from=cursor1.getString(cursor1.getColumnIndex(COLUMN_NAME_USERNAME));
                Log.d("Sender",from);
                int x=cursor1.getInt(cursor1.getColumnIndex(COLUMN_NAME_BALANCE));
                if(Integer.parseInt(value)>x){
                    Toast.makeText(context,"Transaction Failed",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    SQLiteDatabase db = userDbHelper.getWritableDatabase();
                    int modified_value=x-Integer.parseInt(value);
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(COLUMN_NAME_BALANCE,modified_value);
                    String selection = _ID+" LIKE ? ";
                    String[] selectionArgs={message};
                    int count =db.update(TABLE_NAME,contentValues,selection,selectionArgs);

                }
                break;
            }
        }

        SQLiteDatabase db2=userDbHelper.getReadableDatabase();

        Cursor cursor2 =db2.query(
                TABLE_NAME,
                projection1,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor2.moveToNext()){
            //Log.d("Receiver",cursor1.getString(cursor1.getColumnIndex(COLUMN_NAME_PHONE)));
            String s=cursor2.getString(cursor2.getColumnIndex(COLUMN_NAME_PHONE));
            if(s.compareTo(phone)==0){
                int x=cursor2.getInt(cursor2.getColumnIndex(COLUMN_NAME_BALANCE));
                to=cursor2.getString(cursor2.getColumnIndex(COLUMN_NAME_USERNAME));
                SQLiteDatabase db = userDbHelper.getWritableDatabase();
                int modified_value=x+Integer.parseInt(value);
                ContentValues contentValues=new ContentValues();
                contentValues.put(COLUMN_NAME_BALANCE,modified_value);
                String selection = _ID+" LIKE ? ";
                String[] selectionArgs={cursor2.getString(cursor2.getColumnIndex(_ID))};
                int count =db.update(TABLE_NAME,contentValues,selection,selectionArgs);
                Log.d("Rows Affected",String.valueOf(count));
                break;
            }
        }
        SQLiteDatabase db3=transactionDbHelper.getWritableDatabase();
        String[] projection2={_ID,TRANSACTION_COLUMN_FROM,TRANSACTION_COLUMN_TO,TRANSACTION_COLUMN_AMOUNT};
        Cursor cursor3=db3.query(
                TRANSACTION_TABLE_NAME,
                projection2,
                null,
                null,
                null,
                null,
                null

        );

        ContentValues values=new ContentValues();
        values.put(TRANSACTION_COLUMN_FROM,from);
        values.put(TRANSACTION_COLUMN_TO,to);
        values.put(TRANSACTION_COLUMN_AMOUNT,Integer.parseInt(value));
        long rowId= db3.insert(TRANSACTION_TABLE_NAME,null,values);

    }

    @Override
    public int getItemCount() {
        return userClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tvUserName;
        private TextView tvPhone;
        private TextView tvCurBalance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_user);
            tvUserName=itemView.findViewById(R.id.tv_username);
            tvPhone=itemView.findViewById(R.id.tv_phone);
            tvCurBalance=itemView.findViewById(R.id.tv_balance);

        }

        public void setData(int resource, String userName, String phone, String curBalance) {
            imageView.setImageResource(resource);
            tvUserName.setText(userName);
            tvPhone.setText(phone);
            tvCurBalance.setText(curBalance);
        }
    }
}
