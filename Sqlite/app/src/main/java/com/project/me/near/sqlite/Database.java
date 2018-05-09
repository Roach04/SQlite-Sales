package com.project.me.near.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 2016-06-09.
 */
public class Database extends SQLiteOpenHelper {

    //Declarations.
    private static final String DB_NAME = "Saless";

    private static final int DB_VERSION = 1;

    public Database(Context context) {

        /**
         * Pass in the context, db name, factory which is null and the current db version.
         */
        super(context, DB_NAME, null, DB_VERSION);

        //Log to ensure that db is created.
        Log.d("DB", "DB Created.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //here we are creating the table.
        String sql="CREATE TABLE IF NOT EXISTS " + Constants.UserInfo.table_name +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constants.UserInfo.names + " TEXT NOT NULL unique, "
                + Constants.UserInfo.email  + " TEXT NOT NULL , "
                + Constants.UserInfo.phone  + " TEXT NOT NULL , "
                + Constants.UserInfo.status  + " INTEGER)";

        //execute the query.
        db.execSQL(sql);

        //log the table creation.
        Log.d("DB", "TABLE Created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Insert data into the database.
     */
    public void insert(String names, String email, String phone){

        SQLiteDatabase db;

        //get a connection from the db for making changes ie writable.
        //context is required as well.
        db = this.getWritableDatabase();

        //instantiate the content values.
        ContentValues values = new ContentValues();

        //load the content values with data.
        values.put(Constants.UserInfo.names,names);
        values.put(Constants.UserInfo.email, email);
        values.put(Constants.UserInfo.phone, phone);
        values.put(Constants.UserInfo.status,0);
        //you can now insert data into the database.
        //pass in the tb name content values.
        db.insert(Constants.UserInfo.table_name, null, values);

        //close the db.
        db.close();
    }


    public ArrayList<Dbase> fetch_sync(){

        //initialize the array list and assign a variable to it.
        ArrayList<Dbase> dbases = new ArrayList<>();

        //create a connection to database.
        //open it up for reading purposes.
        SQLiteDatabase db = this.getWritableDatabase();

        //write a query to fetch the data rom the table.
        String sql = " select * from "+ Constants.UserInfo.table_name+" where status=0";//1==synced

        //create a cursor and store the data in there.
        //also create its variable as well
        Cursor cursor = db.rawQuery(sql, null);

        //ensure that data is there before showing it to the ui.
        if (cursor.moveToFirst()){

            //a do while to loop through the data.
            do {

                //get the data and assign a variable to the same.
                String name = cursor.getString(1);
                String mail = cursor.getString(2);
                String tell = cursor.getString(3);

                String status = cursor.getString(4);
                Log.d("DATA",name+" "+status);

                //instantiate our class dbase and pass in our data.
                //create a variable to the same.
                Dbase dbase = new Dbase(name,mail,tell);

                //add the data into our array list.
                dbases.add(dbase);
            }
            while (cursor.moveToNext());
        }

        //close the db.
        db.close();

        //return what the cursor holds ie data.
        return dbases;
    }
    /**
     * Fetch data and display in a list view.
     */
    public ArrayList<Dbase> fetch(){

        //initialize the array list and assign a variable to it.
        ArrayList<Dbase> dbases = new ArrayList<>();

        //create a connection to database.
        //open it up for reading purposes.
        SQLiteDatabase db = this.getWritableDatabase();

        //write a query to fetch the data rom the table.
        String sql = " select * from "+ Constants.UserInfo.table_name;

        //create a cursor and store the data in there.
        //also create its variable as well
        Cursor cursor = db.rawQuery(sql, null);

        //ensure that data is there before showing it to the ui.
        if (cursor.moveToFirst()){

            //a do while to loop through the data.
            do {

                //get the data and assign a variable to the same.
                String name = cursor.getString(1);
                String mail = cursor.getString(2);
                String tell = cursor.getString(3);



                //instantiate our class dbase and pass in our data.
                //create a variable to the same.
                Dbase dbase = new Dbase(name,mail,tell);

                //add the data into our array list.
                dbases.add(0,dbase);
            }
            while (cursor.moveToNext());
        }

        //close the db.
        db.close();

        //return what the cursor holds ie data.
        return dbases;
    }

    /**
     * Search the database and output the searched items.
     */
    public Cursor search(String names){

        //create sqlite object.
        //and create a connection to the db plus pass in the context.
        SQLiteDatabase db = this.getReadableDatabase();

        //create an array and pass in the data.
        String[] data = {Constants.UserInfo.names,Constants.UserInfo.email,Constants.UserInfo.phone};

        //write an sql statement ensuring that we get only the search item
        //that relates to the one in the db.
        //assign the same to a variable.
        String sql = Constants.UserInfo.names+" LIKE ?";

        //get all the argument inputed into our edit text assign the same to an array
        String[] arg = {names};

        //use the cursor to execute our query.
        Cursor cursor = db.query(Constants.UserInfo.table_name,data,sql,arg,null,null,null);

        //return the cursor holding the data.
        return cursor;
    }

    /**
     * Delete data from the database.
     */
    public void Delete(String names){

        //connection to the db and create a variable to the same.
        SQLiteDatabase db = this.getReadableDatabase();

        //write an sql statement ensuring that we get only the search item
        //that relates to the one in the db.
        //assign the same to a variable.
        String sql = Constants.UserInfo.names+" LIKE ?";

        //get all the argument inputed into our edit text assign the same to an array
        String[] arg = {names};

        //statement to delete from the db.
        //pass in the table name, the sql statement, plus arguments ie the names in our case.
        db.delete(Constants.UserInfo.table_name, sql, arg);

        //close the database.
        db.close();
    }

    /**
     * Update data from the database and save the same.
     */
    public int Updates(String text, String newName, String newMail,String newTell){

        //connect to db.
        //getWritableDatabase(); used coz we are writing to the db.

        //create a connection to the db.
        SQLiteDatabase db;

        db = this.getWritableDatabase();

        //instantiate the content values.
        ContentValues contentValues = new ContentValues();

        //propagate the data into the content values.
        contentValues.put(Constants.UserInfo.names, newName);
        contentValues.put(Constants.UserInfo.email, newMail);
        contentValues.put(Constants.UserInfo.phone, newTell);

        //selection ie select the name that is like what is held under the name in the db.
        String select = Constants.UserInfo.names+" LIKE ?";

        //pass in the arguments.
        String[] args = {text};

        //update the data in the db.
        //we need to also be able to count the rows that have been affected by the changes.
        int count = db.update(Constants.UserInfo.table_name,contentValues,select,args);

        //return the count
        return count;
    }

    /**
     * Updates the database on the server with info received from the phone.
     */
    public void update(String email){

        SQLiteDatabase db = this.getWritableDatabase();

        //query to update the db based on status.
        String sql = "UPDATE "+ Constants.UserInfo.table_name +" SET status='1' WHERE "+Constants.UserInfo.email+" = '"+email+"' ";

        Log.d("DATA", "sql: " + sql);

        //execute the query.
        db.execSQL(sql);

        //close the db
        db.close();
    }

    //count the rows in the database.
    public int countBlackSpot(){

        //select * from the table
        String selectQuery = "SELECT  * FROM "+ Constants.UserInfo.table_name+" WHERE status=0";

        //connection to database.
        SQLiteDatabase database = this.getWritableDatabase();

        //store the data to the cursor.
        //execute the query.
        Cursor cursor = database.rawQuery(selectQuery, null);

        //count all the black spots.
        int count = cursor.getCount();

        //close the database connection
        database.close();

        //return the number of black spots.
        return count;
    }
}
