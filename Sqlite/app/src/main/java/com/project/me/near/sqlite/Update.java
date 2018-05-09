package com.project.me.near.sqlite;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    //declare some variables.
    EditText name,mail,tell;

    String names,mails,phone;

    String newName,newMail,newTell;

    Database db;

    String text;

    String id;

    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //menu action bar to go back to display activity.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initialize them.
        name = (EditText) findViewById(R.id.editTextNewNames);

        mail = (EditText) findViewById(R.id.editTextNewMail);

        tell = (EditText) findViewById(R.id.editTextNewPhone);

        //populate the edit texts with data from the search intent.
        names = getIntent().getExtras().getString("newName");

        mails = getIntent().getExtras().getString("newMail");

        phone = getIntent().getExtras().getString("newTell");

        text = getIntent().getExtras().getString("newText");

        //assign the data you've got from the search activity to the update activity's edit texts.
        name.setText(names);

        mail.setText(mails);

        tell.setText(phone);

        //set the fonts.
        typeface = Typeface.createFromAsset(getAssets(), "fonts/SansationLight.ttf");

        //implement the fonts.
        name.setTypeface(typeface);

        mail.setTypeface(typeface);

        tell.setTypeface(typeface);
    }

    public void changes(View view) {

        //instantiate the db and pass in the context.
        db = new Database(this);

        //text = name.getText().toString();

        //get the text and convert them to string.
        newName = name.getText().toString();
        newMail = mail.getText().toString();
        newTell = tell.getText().toString();

        int count = db.Updates(text,newName,newMail,newTell);

        //toast a message.
        Toast.makeText(Update.this, count+" Update Successful.", Toast.LENGTH_SHORT).show();

        //close the db.
        db.close();

        //kill the activity and go back to search.
        finish();

        startActivity(new Intent(this, Display.class));
    }
}
