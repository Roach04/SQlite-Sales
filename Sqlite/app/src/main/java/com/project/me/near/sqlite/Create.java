package com.project.me.near.sqlite;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Create extends AppCompatActivity {

    Toolbar toolbar;

    EditText names,email,phone;

    String name,mail,tell;

    Database db;

    Typeface typeFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //display and arrow in the toolbar to navigate back to data activity.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        names = (EditText) findViewById(R.id.editTextNames);

        email = (EditText) findViewById(R.id.editTextEmail);

        phone = (EditText) findViewById(R.id.editTextPhone);

        //set up fonts for the texts.
        typeFace = Typeface.createFromAsset(getAssets(),"fonts/SansationLight.ttf");

        names.setTypeface(typeFace);

        email.setTypeface(typeFace);

        phone.setTypeface(typeFace);

        //instantiate the db and pass in the context ie this.
        db = new Database(this);
    }

    /**
     * Insert data into the db.
     * @param view
     */
    public void save(View view) {

        //convert users' input to string.
        name = names.getText().toString();

        mail = email.getText().toString();

        tell = phone.getText().toString();

        //validate to ensure that all fields are filled with data.
        if (!name.isEmpty() && !mail.isEmpty() && !tell.isEmpty()){

            db.insert(name,mail,tell);

            //Log the message.
            Log.d("DB", "Record inserted.");

            //toast as well.
            Toast.makeText(Create.this, " Record Inserted...", Toast.LENGTH_LONG).show();

            //clear all the fields after inserting a record.
            names.setText(null);

            email.setText(null);

            phone.setText(null);

        }
        else{

            //toast a message if we have an empty field.
            Toast.makeText(Create.this, " Ensure all field(s) are filled.", Toast.LENGTH_LONG).show();
        }


    }
}
