package com.example.plandroid.plandroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;


public class Options_Activity extends ActionBarActivity {
    String options_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_);

        // Obtain values passed through Intents
        Bundle b = getIntent().getExtras();
        options_username = b.getString("USERNAME");

        //Button References
        Button create_e = (Button) this.findViewById(R.id.button1);
        Button view_e = (Button) this.findViewById(R.id.button2);
        Button delete_e = (Button) this.findViewById(R.id.button3);

        //creating onClick Listener
        OnClickListener theClickEvent = new OnClickListener(){
            public void onClick(View arg0){
                if(arg0.getId() == R.id.button1){ //go to CreateEventActivity log_in button pressed
                    Intent myIntent = new Intent(arg0.getContext(), CreateEventActivity.class);
                    startActivity(myIntent);
                }
                else if(arg0.getId() == R.id.button2){ //go to SearchEventActivity when sign_up button pressed
                    Intent myIntent = new Intent(arg0.getContext(), SearchEventActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("USERNAME_1", options_username);
                    myIntent.putExtras(bundle);
                    startActivityForResult(myIntent, 0);
                    //startActivity(myIntent);
                }
                else if(arg0.getId() == R.id.button3){ //go to DeleteEventActivity when sign_up button pressed
                    Intent myIntent = new Intent(arg0.getContext(), DeleteEventActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("DELETE_EVENT_USERNAME", options_username);
                    myIntent.putExtras(bundle);
                    startActivityForResult(myIntent, 0);
                }
            }
        };
        create_e.setOnClickListener(theClickEvent);
        view_e.setOnClickListener(theClickEvent);
        delete_e.setOnClickListener(theClickEvent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options_, menu);
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