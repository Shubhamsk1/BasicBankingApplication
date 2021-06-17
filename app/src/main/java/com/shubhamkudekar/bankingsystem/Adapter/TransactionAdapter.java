package com.shubhamkudekar.bankingsystem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamkudekar.bankingsystem.R;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    @NonNull
    private List<TransactionClass> transactionClassList;
    public TransactionAdapter(List<TransactionClass> list){
        this.transactionClassList=list;
    }
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_design,parent,false);
        return new TransactionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        String sender=transactionClassList.get(position).getSender();
        String receiver=transactionClassList.get(position).getReceiver();
        String amount=transactionClassList.get(position).getAmount();
        holder.setData(sender,receiver,amount);
    }

    @Override
    public int getItemCount() {
        return transactionClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvsender;
        private TextView tvreceiver;
        private TextView tvamount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvsender=itemView.findViewById(R.id.txt_from);
            tvreceiver=itemView.findViewById(R.id.txt_to);
            tvamount=itemView.findViewById(R.id.txt_amt);
        }

        public void setData(String sender, String receiver, String amount) {
            tvsender.setText(sender);
            tvreceiver.setText(receiver);
            tvamount.setText(amount);
        }
    }
}
