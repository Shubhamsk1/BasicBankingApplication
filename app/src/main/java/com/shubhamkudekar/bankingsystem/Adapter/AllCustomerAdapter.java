package com.shubhamkudekar.bankingsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamkudekar.bankingsystem.AllCustomersActivity;
import com.shubhamkudekar.bankingsystem.CustomerActivity;
import com.shubhamkudekar.bankingsystem.R;

import java.util.List;

public class AllCustomerAdapter extends  RecyclerView.Adapter<AllCustomerAdapter.ViewHolder>{

    @NonNull
    private List<UserClass>userClassList;
    public AllCustomerAdapter(List<UserClass> userList){this.userClassList=userList;}
    @Override
    public AllCustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllCustomerAdapter.ViewHolder holder, final int position) {
        int resource=userClassList.get(position).getImageview();
        String userName=userClassList.get(position).getUserName();
        String phone=userClassList.get(position).getPhone();
        String curBalance="₹ " + userClassList.get(position).getCurBalance();
        holder.setData(resource,userName,phone,curBalance);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(),CustomerActivity.class);
                intent.putExtra("position",position+"");
                holder.itemView.getContext().startActivities(new Intent[]{intent});
            }
        });



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
