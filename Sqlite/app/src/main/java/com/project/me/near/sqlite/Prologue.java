package com.project.me.near.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Prologue extends AppCompatActivity {

    ImageView create,eye,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prologue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //go to the search activity
                startActivity(new Intent(getApplicationContext(), Search.class));
            }
        });

        create = (ImageView) findViewById(R.id.imageViewPlus);
        eye = (ImageView) findViewById(R.id.imageViewEye);
        update = (ImageView) findViewById(R.id.imageViewUpdate);
        delete = (ImageView) findViewById(R.id.imageViewGarbage);
    }

    /**
     * Menus.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflate with our menu file.
        getMenuInflater().inflate(R.menu.menu_splash, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //get the id
        int id = item.getItemId();

        //switch statement.
        switch (id){

            case R.id.action_search:

                startActivity(new Intent(this, Search.class));

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void plus(View view) {

        //create class.
        startActivity(new Intent(this, Create.class));
    }

    public void eye(View view) {

        //display class.
        startActivity(new Intent(this, Display.class));
    }

    public void update(View view) {

        startActivity(new Intent(this, Search.class));
    }

    public void delete(View view) {

        startActivity(new Intent(this, Search.class));
    }
}
