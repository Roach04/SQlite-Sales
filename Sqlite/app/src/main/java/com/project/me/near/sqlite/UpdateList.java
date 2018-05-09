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

public class UpdateList extends AppCompatActivity {

    EditText na,ma,te;

    String names,mails,phone;

    Database db;

    String newName, newMail, newTell;

    String text;

    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //go back to display activity.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // fonts.
        typeface = Typeface.createFromAsset(getAssets(),"fonts/SansationLight.ttf");

        //initialize the edit texts.
        na = (EditText) findViewById(R.id.editTextNN);

        ma = (EditText) findViewById(R.id.editTextEE);

        te = (EditText) findViewById(R.id.editTextPP);

        //assign the font.
        na.setTypeface(typeface);

        ma.setTypeface(typeface);

        te.setTypeface(typeface);

        //populate the edit texts with data from the search intent.
        names = getIntent().getExtras().getString("newName");

        mails = getIntent().getExtras().getString("newMail");

        phone = getIntent().getExtras().getString("newTell");

        text = getIntent().getExtras().getString("newText");

        //assign the data you've got from the search activity to the update activity's
        //edit texts.
        na.setText(names);

        ma.setText(mails);

        te.setText(phone);
    }

    public void updates(View view) {

        //instantiate the db and pass in the context.
        db = new Database(this);

        //get the text and convert them to string.
        newName = na.getText().toString();
        newMail = ma.getText().toString();
        newTell = te.getText().toString();

        int count = db.Updates(text,newName,newMail,newTell);

        //toast a message.
        Toast.makeText(UpdateList.this, count + " Update(s) Successful.", Toast.LENGTH_SHORT).show();

        //close the db.
        db.close();

        //kill the activity and go back to search.
        finish();

        startActivity(new Intent(this, Display.class));
    }
}
