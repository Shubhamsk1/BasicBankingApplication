package com.shubhamkudekar.bankingsystem.data;

import android.provider.BaseColumns;

public final class UserContract {
    private UserContract(){}
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME="Users";
        public static final String COLUMN_NAME_USERNAME ="username";
        public static final String COLUMN_NAME_PHONE="phone";
        public  static final String COLUMN_NAME_EMAIL="email";
        public  static final String COLUMN_NAME_BALANCE="balance";
    }
}
