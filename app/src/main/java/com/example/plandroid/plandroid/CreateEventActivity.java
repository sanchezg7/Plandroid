package com.example.plandroid.plandroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.Toast;


public class CreateEventActivity extends ActionBarActivity {

    TimePicker timePicker;
    DatePicker datePicker;

    //event date
    int event_month;
    int event_day;
    int event_year;

    //event times
    int event_hr;
    int event_min;

    //event privacy
    String privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        EditText ePrivacy = (EditText) findViewById(R.id.in_privacy);

        //timepicker reference
        timePicker = (TimePicker) findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);

        //datepicker reference
        datePicker = (DatePicker) findViewById(R.id.datePicker);

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




    }


    public void onClick(View v) {
        Toast.makeText(getBaseContext(), "Date selected:" + (datePicker.getMonth() + 1) + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear() + "\n"
                + "Time selected:" + timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute(), Toast.LENGTH_SHORT).show();

        //gets the date
        event_month = datePicker.getMonth() + 1;
        event_day = datePicker.getDayOfMonth();
        event_year = datePicker.getYear();

        //gets the time
        event_hr = timePicker.getCurrentHour();
        event_min = timePicker.getCurrentMinute();

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