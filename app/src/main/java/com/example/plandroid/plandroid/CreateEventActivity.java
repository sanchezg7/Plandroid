package com.example.plandroid.plandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.Toast;

import org.json.JSONArray;


public class CreateEventActivity extends ActionBarActivity implements View.OnClickListener{

    String createEvent_username;
    String createEvent_ename;
    EditText cre_eventNameField;
    EditText cre_description;
    EditText cre_eLocation;
    EditText cre_ePrivacy;

    TimePicker timePicker;
    DatePicker datePicker;

    //event date
    int event_month;
    int event_day;
    int event_year;

    //event times
    int event_hr;
    int event_min;

    boolean success = false; //check to see if query is successful

    Intent myIntent;
    Bundle bundle = new Bundle();

    //event privacy
    String privacy;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        // Obtain values passed through Intents
        Bundle b = getIntent().getExtras();
        createEvent_username = b.getString("CREATE_EVENT_USERNAME");

        //timepicker reference
        timePicker = (TimePicker) findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);

        //datepicker reference
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        //EditText
        cre_eventNameField = (EditText) findViewById(R.id.in_eventname);
        cre_description = (EditText) findViewById(R.id.in_event_des);
        cre_eLocation = (EditText) findViewById(R.id.in_event_location);
        cre_ePrivacy = (EditText) findViewById(R.id.in_privacy);


        //butonn
        Button buttonCE = (Button) findViewById(R.id.buttonCE);

        /*
        Spinner spinner = (Spinner) findViewById(R.id.privacy_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.privacy_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        */

       buttonCE.setOnClickListener(this);




    }


    public void onClick(View v) {
        //Toast.makeText(getBaseContext(), "Date selected:" + (datePicker.getMonth() + 1) + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear() + "\n"
              //  + "Time selected:" + timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute(), Toast.LENGTH_SHORT).show();

        switch(v.getId()) {

            case R.id.buttonCE:
            //gets the date
            event_month = datePicker.getMonth() + 1;
            event_day = datePicker.getDayOfMonth();
            event_year = datePicker.getYear();

            //gets the time
            event_hr = timePicker.getCurrentHour();
            event_min = timePicker.getCurrentMinute();

            String date = Integer.toString(event_year) + "-" + Integer.toString(event_month)+ "-" + Integer.toString(event_day);
            String time = Integer.toString(event_hr) + ":" + Integer.toString(event_min) + ":00";

            createEvent_ename = null;
            createEvent_ename = cre_eventNameField.getText().toString();
            new NewEvent().execute("5", createEvent_username, createEvent_ename, cre_description.getText().toString(), cre_eLocation.getText().toString(), cre_ePrivacy.getText().toString(), date, time);
            break;

        }


    }

    private void onSuccess(boolean success){

        if(success){
            //Log.e("success: " , Boolean.toString(success));
            myIntent = new Intent(CreateEventActivity.this, Options_Activity.class);
            myIntent.putExtras(bundle);
            startActivityForResult(myIntent, 0);
        }else{
            Toast.makeText(CreateEventActivity.this, "Could not create event", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    public class NewEvent extends AsyncTask<String, Long, JSONArray>{

        JSONArray temp = null;
        dbConnect handle = new dbConnect();

        @Override
        protected JSONArray doInBackground(String ... params){
            temp = handle.selector(params);
            return temp;
        }

        protected void onPostExecute(JSONArray jsonArray){
            if(temp != null) {
                Toast.makeText(CreateEventActivity.this, "Event Created", Toast.LENGTH_SHORT).show();
                success = true;
            }else{
                Toast.makeText(CreateEventActivity.this, "Event Creation Failed", Toast.LENGTH_SHORT).show();
                success = false;
            }
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
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
}