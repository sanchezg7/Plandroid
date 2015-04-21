package com.example.plandroid.plandroid;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;


public class DeleteEventActivity extends ActionBarActivity implements View.OnClickListener{

    String deleteEvent_username;
    String deleteEvent_ename;
    private ListView deleteEventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event);

        // Obtain values passed through Intents
        Bundle b = getIntent().getExtras();
        deleteEvent_username = b.getString("DELETE_EVENT_USERNAME");

        //this is just to check that texts are read in
        TextView one_textView = (TextView) findViewById(R.id.del_usernameView);
        one_textView.setText(deleteEvent_username);
        //Button
        Button del_event = (Button) findViewById(R.id.del_event_bt);

        //create Listview to hold the results
        this.deleteEventListView = (ListView) this.findViewById(R.id.deleteEventListView);
        del_event.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.del_event_bt:
                //Log.e("eventQtn", "pressed");
                Toast.makeText(DeleteEventActivity.this, "Delete Event pressed", Toast.LENGTH_SHORT).show();
                deleteEvent_ename = null;
                //deleteEvent_ename = eventNameField.getText().toString();
                //new GetEvents().execute("2", deleteEvent_username, deleteEvent_ename);
                //break;
        }
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_event, menu);
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
