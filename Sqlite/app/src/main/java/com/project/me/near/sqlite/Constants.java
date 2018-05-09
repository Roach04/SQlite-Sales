package com.project.me.near.sqlite;

/**
 * Created by user on 2016-06-09.
 */
public class Constants {

    public static abstract class UserInfo{

        /**
         * Declare constants to be used for sqlite purposes.
         * Info should have their keys corresponding with their values.
         */

        //field names
        public static final String names = "names";
        public static final String email = "Email_address";
        public static final String phone = "Phone_number";
        public static final String status = "status";
        //Table name constant
        public static final String table_name = "users";
    }
}
