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
import org.json.JSONException;
import org.json.JSONObject;


public class DepartmentActivity extends ActionBarActivity implements View.OnClickListener{

    String department_username;
    String department_name;
    private ListView searchEventListView;
    EditText dep_ID;

    private TextView dep_textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);


        // Obtain values passed through Intents
        Bundle b = getIntent().getExtras();
        department_username = b.getString("DEPARTMENT_EVENT_USERNAME");

        //this is just to check that texts are read in
        TextView one_textView = (TextView) findViewById(R.id.dep_usernameView);
        one_textView.setText(department_username);

        dep_textView = (TextView) findViewById(R.id.dep);
        //this.dep = (TextView) findViewById(R.id.dep);

        //Button
        Button ser_dep_bt = (Button) findViewById(R.id.depQBtn);

        //EditText
        dep_ID = (EditText) findViewById(R.id.in_departmentId);


        //create Listview to hold the results
        this.searchEventListView = (ListView) this.findViewById(R.id.searchEventListView);
        ser_dep_bt.setOnClickListener(this);

    }



    public void setTextToTextView(JSONArray jsonArray){
        String s = "";
        if(jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject json = null;
                try {
                    json = jsonArray.getJSONObject(i);
                    s = s +
                            "username: " + json.getString("username")+"\n";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            this.dep_textView.setText(s);
        }
        else{
            this.dep_textView.setText("Query Failed");
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.depQBtn:
                //Log.e("eventQtn", "pressed");
                Toast.makeText(DepartmentActivity.this, "Search Department", Toast.LENGTH_SHORT).show();
                department_name = null;
                department_name = dep_ID.getText().toString();
                new GetDepartments().execute("7", department_username, department_name);
                break;
        }
    }

//this AsyncTask will return all users, thus requiring no parameters from dbConnect
//format of AynscTask param, progress, and Result which is the thing that is returned
public class GetDepartments extends AsyncTask<String, Long, JSONArray> {

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




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_department, menu);
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
