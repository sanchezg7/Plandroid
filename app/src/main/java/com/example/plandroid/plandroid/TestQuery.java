package com.example.plandroid.plandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TestQuery extends ActionBarActivity implements View.OnClickListener {

    //this is what will display the results to the user, this is temporary we
    //will instead use listviews on the actual results
    private TextView sampleQryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_query);

        //Button References
        Button rQuery = (Button) this.findViewById(R.id.runQ);

        this.sampleQryTextView = (TextView) findViewById(R.id.sampleQryTextView);
        rQuery.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_query, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        //Intent myIntent;
        switch (v.getId()) {
            case R.id.runQ:
                new GetUsers().execute("0"); //this will call get all users
                break;

        }
    }

    //formats the current json array and makes it a string and finally sets the textview
    public void setTextToTextView(JSONArray jsonArray){
        String s = "";
        if(jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject json = null;
                try {
                    json = jsonArray.getJSONObject(i);
                    s = s +
                            "u_ID: " + json.getString("u_ID") + " \n" +
                            "username: " + json.getString("username") + " \n" +
                            "firstname: " + json.getString("firstname") + "\n" +
                            "lastname: " + json.getString("lastname") + "\n";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            this.sampleQryTextView.setText(s);
        }
        else{
            this.sampleQryTextView.setText("Query Failed");
        }
    }


    //this AsyncTask will return all users, thus requiring no parameters from dbConnect
    //format of AynscTask param, progress, and Result which is the thing that is returned
    public class GetUsers extends AsyncTask<String, Long, JSONArray> {

        dbConnect handle = new dbConnect();
        @Override
        protected JSONArray doInBackground(String... params){

            return handle.selector(params);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray){
            setTextToTextView(jsonArray);
        }
    }
}
