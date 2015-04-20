package com.example.plandroid.plandroid;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;


public class SearchEventActivity extends ActionBarActivity implements View.OnClickListener{

    String searchEvent_username;
    String searchEvent_ename;
    private ListView searchEventListView;
    EditText eventNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_event);

        eventNameField = (EditText) findViewById(R.id.in_eventname);

        // Obtain values passed through Intents
        Bundle b = getIntent().getExtras();
        searchEvent_username = b.getString("USERNAME_1");

        //this is just to check that texts are read in
        TextView one_textView = (TextView) findViewById(R.id.textView1);
        one_textView.setText(searchEvent_username);
        //Button
        Button eventQBtn = (Button) findViewById(R.id.eventQBtn);

        //create Listview to hold the results
        this.searchEventListView = (ListView) this.findViewById(R.id.searchEventListView);
        eventQBtn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_event, menu);
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

    public void setListAdapter(JSONArray jsonArray){
        if(jsonArray != null){
            this.searchEventListView.setAdapter(new EventListViewAdapter(jsonArray, this));
        }else{
            Toast.makeText(SearchEventActivity.this, "Query Failed for User: " +  searchEvent_username, Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.eventQBtn:
                //Log.e("eventQtn", "pressed");
                Toast.makeText(SearchEventActivity.this, "eventQBtn pressed", Toast.LENGTH_SHORT).show();
                searchEvent_ename = null;
                searchEvent_ename = eventNameField.getText().toString();
                new GetEvents().execute("2", searchEvent_username, searchEvent_ename);
                break;
        }
    }

    public class GetEvents extends AsyncTask<String, Long, JSONArray>{


        dbConnect handle = new dbConnect();
        @Override
        protected JSONArray doInBackground(String... params){

            return handle.selector(params);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray){
            setListAdapter(jsonArray);
        }
    }
}
