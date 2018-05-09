package com.project.me.near.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    Database db;

    TextView name,mail,tell;

    Cursor cursor;

    EditText entry;

    String text;

    Dbase dbase;

    String names,ma,te;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //we instantiate the db and pass in the context.
        db = new Database(this);

        //arrow menu going back action.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //delete the data using the name in this case the text variable
                //that hold out name.
                db.Delete(text);

                //empty the search area.
                name.setText("");
                mail.setText("");
                tell.setText("");

                //ensure that there is something to be deleted first before deleting.
                try {

                    if (text.isEmpty()){

                        //toast to the user that deletion was a success.
                        Toast.makeText(Search.this, " No item Selected.", Toast.LENGTH_LONG).show();
                    }
                    else{

                        //toast to the user that deletion was a success.
                        Toast.makeText(Search.this, text+" Deleted.", Toast.LENGTH_LONG).show();

                        //go to where data is listed in the view activity.
                        startActivity(new Intent(getApplicationContext(), Display.class));
                    }
                }
                catch (NullPointerException e){

                    e.printStackTrace();
                }
            }
        });

        //find views
        name = (TextView) findViewById(R.id.textViewNa);

        mail = (TextView) findViewById(R.id.textViewEm);

        tell = (TextView) findViewById(R.id.textViewPh);

        entry = (EditText) findViewById(R.id.editText);

        text = entry.getText().toString();

        //Ensure that visibility of these text views is GONE.
        name.setVisibility(View.GONE);
        mail.setVisibility(View.GONE);
        tell.setVisibility(View.GONE);

        /**
         * Another fab
         */
        FloatingActionButton fab01 = (FloatingActionButton) findViewById(R.id.fab01);

        fab01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ensure that there is something ie data to edit.
                if (text.isEmpty()){

                    //toast to the user that deletion was a success.
                    Toast.makeText(Search.this, " No item Selected.", Toast.LENGTH_LONG).show();
                }
                else{

                    //create an intent to go to the update activity.
                    Intent intent = new Intent(getApplicationContext(), Update.class);

                    //we need the data displayed on the search activity to be also
                    //displayed in the update activity so that we can handle it.

                    dbase = new Dbase(names, ma, te);

                    String newTx = entry.getText().toString();

                    String newNa = name.getText().toString();
                    String newMa = mail.getText().toString();
                    String newTe = tell.getText().toString();

                    //this is done via key and value combinations.
                    intent.putExtra("newName", newNa);
                    intent.putExtra("newMail", newMa);
                    intent.putExtra("newTell", newTe);

                    intent.putExtra("newText", newTx);

                    //start the activity and pass in your intent.
                    startActivity(intent);
                }

            }
        });
    }

    public void look(View view) {

        //hide the keyboard after displaying search data.
        hideSoftKeyboard(view);

        text = entry.getText().toString();

        //ensure that the field is not empty.
        if (!text.isEmpty()){

            //instantiate the cursor.
            cursor = db.search(text);

            //set the text with the corresponding text view.
            //ensure that the cursor has data to display.
            if (cursor.moveToFirst()){

                //get the data and assign a variable to the same.
                String nameD = cursor.getString(0);
                String mailD = cursor.getString(1);
                String tellD = cursor.getString(2);


                //assign the data to their respective text views
                name.setText(nameD);
                mail.setText(mailD);
                tell.setText(tellD);

                //Return the visibility status of the text views.
                name.setVisibility(View.VISIBLE);
                mail.setVisibility(View.VISIBLE);
                tell.setVisibility(View.VISIBLE);

            }

            try {

                 if (cursor.isAfterLast()){

                    //toast a negative message.
                    Toast.makeText(Search.this, "No Matching Criteria.", Toast.LENGTH_SHORT).show();
                }
            }
            catch (IndexOutOfBoundsException e){

                e.printStackTrace();
            }

            //close the db connection.
            db.close();
        }
        else{

            //toast a negative message.
            Toast.makeText(Search.this, "What are you searching for exactly??", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hide keyboard.
     * @param v
     */
    private void hideSoftKeyboard(View v) {

        //gain access to the in build keyboard system service.
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        //hide the keyboard.
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
