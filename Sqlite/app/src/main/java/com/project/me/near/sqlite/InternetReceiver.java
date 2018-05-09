package com.project.me.near.sqlite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class InternetReceiver extends BroadcastReceiver {
    public InternetReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        //check whether there is internet connectivity.
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //network information.
        NetworkInfo info = cm.getActiveNetworkInfo();

        //check whether the phone is connected to the network.
        if (info != null && info.isConnected()){

            Toast.makeText(context, "Internet Connection is Active", Toast.LENGTH_LONG).show();

            //explicitly state the service.
            Intent s = new Intent(context, UpdateService.class);

            //start the service.
            context.startService(s);
        }
        else{

            Toast.makeText(context, "Internet not Connected.", Toast.LENGTH_LONG).show();
        }

        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
