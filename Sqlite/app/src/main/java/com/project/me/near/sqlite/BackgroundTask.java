package com.project.me.near.sqlite;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by user on 2016-06-30.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> {

    //context.
    Context context;

    BackgroundTask(Context context){

        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        //url where the data will be saved in.
        String onlineUrl = "http://womanart.co.ke/sales/insertData.php";

        //set params.
        String method = params[0];

        if (method.equals("online")){

            /*try {
                //get the params for the data being sent online.
                String name = params[1];
                String mail = params[2];
                String tell = params[3];
            }
            catch (ArrayIndexOutOfBoundsException e){

                e.printStackTrace();
            }*/
            //instantiate the url.
            try {

                //get the params for the data being sent online.
                String name = params[1];
                String mail = params[2];
                String tell = params[3];

                URL url = new URL(onlineUrl);

                //open a http url connection.
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                //set the connection method.
                httpURLConnection.setRequestMethod("POST");

                //set the do output.
                httpURLConnection.setDoOutput(true);

                //get the output stream.
                OutputStream outputStream = httpURLConnection.getOutputStream();

                //instantiate the buffered writer.
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                //package the data and assign a variable to the same.
                String data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("mail", "UTF-8")+"="+URLEncoder.encode(mail,"UTF-8")+"&"+
                        URLEncoder.encode("tell", "UTF-8")+"="+URLEncoder.encode(tell,"UTF-8");

                //log the data.
                Log.d("DATA", data);

                //write the data to the buffered writer.
                bufferedWriter.write(data);

                //flush the same.
                bufferedWriter.flush();

                //close the output stream
                outputStream.close();

                //input stream to get a response from the server.
                InputStream inputStream = httpURLConnection.getInputStream();

                //instantiate the buffered reader and pass in the input stream
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                //response from the server.
                String response = "";

                String line = "";

                //check to ensure that the buffered reader is not null.
                //then get the info from the same.
                while ((line = bufferedReader.readLine()) != null){

                    response += line;
                }

                //close the buffered reader.
                bufferedReader.close();

                //close the input stream
                inputStream.close();

                //close/ disconnect the http connection.
                httpURLConnection.disconnect();

                //return a response from the server.
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
        return " Failed.";

        //stop the service.

    }
}
