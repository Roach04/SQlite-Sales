package com.project.me.near.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Display extends AppCompatActivity {

    Database db;

    ListView list;

    CustomAdapterData customAdapterData;

    ArrayList<Dbase> dbases;

    Dbase dbase;

    String names,mails,tells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar thingy of going back to data activity.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initialize the list view.
        list = (ListView) findViewById(R.id.listViewData);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //start the search activity
                startActivity(new Intent(getApplicationContext(), Search.class));
            }
        });

        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Dbase az = dbases.get(position);

                Toast.makeText(Display.this, "No Info displayed.", Toast.LENGTH_SHORT).show();
            }
        });*/

        //context menu.
        //used when someone long presses on an item.
        registerForContextMenu(list);

        //instantiate db.
        db = new Database(this);

        //instantiate the array list.
        dbases = new ArrayList<>();

        //get the data ie fetch from the database.
        dbases = db.fetch();

        //pass it to the custom adapter.
        //instantiate the adapter and pass in the context plus the array list containing data.
        customAdapterData = new CustomAdapterData(this,dbases);


        //set the adapter.
        list.setAdapter(customAdapterData);
    }

    /**
     * Menus.
     */
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //get the menu xml file and inflate it to the menu.
        getMenuInflater().inflate(R.menu.menu_display,menu);

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.add("Update");

        menu.add("Delete");

        menu.setHeaderTitle("Editable.");

        menu.setHeaderIcon(R.mipmap.folderm);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //get the position of each menu item.
        //create a variable to handle the same.
        int pos = info.position;

        if (item.getTitle().equals("Delete")){

            //get the position.
            //assign the same to a variable.
            Dbase xc = dbases.get(pos);

            //instantiate the db and pass in the context.
            db = new Database(this);

            //delete this record or row from the db.
            db.Delete(xc.getNames());

            //remove the record from the array list.
            dbases.remove(pos);

            //notify the adapter of the removed item.
            customAdapterData.notifyDataSetChanged();

            Toast.makeText(Display.this, xc.getNames()+" Deleted.", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle().equals("Update")){

            //get the position.
            //assign the same to a variable.
            Dbase xc = dbases.get(pos);

            //instantiate the db and pass in the context.
            db = new Database(this);

            //create an intent to go to the update activity.
            Intent intent = new Intent(getApplicationContext(), UpdateList.class);

            //this is done via key and value combinations.
            intent.putExtra("newName", xc.getNames());
            intent.putExtra("newMail", xc.getEmail());
            intent.putExtra("newTell", xc.getPhone());

            //carry the search entry too.
            intent.putExtra("newText", xc.getNames());

            //start the activity and pass in your intent.
            startActivity(intent);

            //Toast.makeText(Display.this, "Update option Awesome.", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}
