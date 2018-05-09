package com.project.me.near.sqlite;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpdateService extends Service {

    public UpdateService() {
    }

    Database db;

    ArrayList<Dbase> dbases;

    Dbase dbase;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //produce a toast to indicate that this is operational.
        Toast.makeText(getApplicationContext(), "Database Sync Initialized.", Toast.LENGTH_LONG).show();

        //instantiate the db.
        db = new Database(getApplicationContext());

        //instantiate the array list.
        dbases = new ArrayList<>();

        //get the file in here.
        if(db.countBlackSpot() > 0)
        {
            //Toast.makeText(UpdateService.this, "There are Records to be Synced.", Toast.LENGTH_SHORT).show();

            stats();
        }
        else{

            Toast.makeText(UpdateService.this, "Database Record(s) are Synced.", Toast.LENGTH_SHORT).show();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Start another class to send info saved in the db to the online db.
     * fetch the info from the sqlite and prepare it.
     */
    public void stats(){

        String url = "http://womanart.co.ke/sales/insertData.php";

        final Database db = new Database(this);

        ArrayList<Dbase> array = db.fetch_sync();

        Gson gson = new Gson();

        String data = gson.toJson(array);

        Log.d("DATA", data);

        RequestParams params = new RequestParams();

        params.put("json", data);

        AsyncHttpClient client = new AsyncHttpClient();

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int kl, Header[] headers, byte[] response) {

                String res = new String(response);

                Log.d("RESPONSE", res);

                try {
                    JSONArray jsonArray = new JSONArray(res);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject json = jsonArray.getJSONObject(i);

                        Log.d("JSON", String.valueOf(json));

                        String email  = json.getString("key");

                        String status = json.getString("status");

                        if (status.equals("inserted")) {
                            db.update(email);
                            Log.d("DATA", "Synced record for " + email);

                            //count for unsynced data being inserted into the online db.

                            //toast a message.
                            Toast.makeText(UpdateService.this, "Record(s) Inserted.", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            Toast.makeText(UpdateService.this, "All Record(s) are Synced.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    Log.e("FAIL", e.getMessage());

                    //Toast.makeText(UpdateService.this, "Info Sync Failed.", Toast.LENGTH_SHORT).show();
                }

                //stop the service from running.
                stopSelf();
            }

            @Override
            public void onFailure(int kl, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("FAIL", "Failed to load data " + kl);
                Toast.makeText(getApplicationContext(), "Record(s) Not Inserted", Toast.LENGTH_LONG).show();

                //stop the service from running.
                stopSelf();
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
