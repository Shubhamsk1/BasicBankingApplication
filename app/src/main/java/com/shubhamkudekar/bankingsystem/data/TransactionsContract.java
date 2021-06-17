package com.shubhamkudekar.bankingsystem.data;

import android.provider.BaseColumns;

public class TransactionsContract {
    private TransactionsContract(){}
    public  static class TransactionsEntry implements BaseColumns{
        public static final String TRANSACTION_TABLE_NAME="Transactions";
        public static final String TRANSACTION_COLUMN_FROM ="froms";
        public static final String TRANSACTION_COLUMN_TO="tos";
        public  static final String TRANSACTION_COLUMN_AMOUNT="amount";
    }
}
