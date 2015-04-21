package com.example.plandroid.plandroid;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;

import org.json.JSONArray;


public class SignUp_Activity extends ActionBarActivity implements OnClickListener{
    String username;
    User newUser = new User(); //this is a new user that will input his or her info
    boolean success = false; //check to see if query is successful
    Spinner spinner; //to select the d_ID number


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);


        //Button References
        Button sign_up = (Button) this.findViewById(R.id.signUpB);

        //A.O. not sure about the line of code below
        //username = usernameTextView.toString(); //this will be added to the database using JDBC
        sign_up.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.department_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.department_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }

    @Override
    public void onClick(View v){
        Intent myIntent;
        Bundle bundle = new Bundle();
        if(v.getId() == R.id.signUpB){

            //Text References. Gets data from user
            EditText firstname_ET = (EditText)findViewById(R.id.in_firstname);
            EditText lastname_ET = (EditText) findViewById(R.id.in_lastname);
            EditText username_ET = (EditText) findViewById(R.id.in_username);
            EditText password_ET = (EditText) findViewById(R.id.in_password);

            //calling set functions on the new user object
            newUser.setFirstname(firstname_ET.getText().toString());
            newUser.setLastname(lastname_ET.getText().toString());
            newUser.setUsername(username_ET.getText().toString());
            newUser.setPassword(password_ET.getText().toString());
            String d_ID = spinner.getSelectedItem().toString();


            //AsyncTask to sign up the user
            new SignUpUser().execute("4", newUser.getUsername(), newUser.getPassword(), newUser.getFirstname(), newUser.getLastname(), d_ID);

            //pass in username to Options_Activity
            bundle.putString("USERNAME", newUser.getUsername());
            if(success) { //true
                myIntent = new Intent(SignUp_Activity.this, Options_Activity.class);
                myIntent.putExtras(bundle);
                startActivityForResult(myIntent, 0);
            }else if(!success){
                return;
            }
            //startActivity(myIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up_, menu);
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

    public class SignUpUser extends AsyncTask<String, Long, JSONArray>{

        JSONArray temp = null;
        dbConnect handle = new dbConnect();

        @Override
        protected JSONArray doInBackground(String ... params){
            temp = handle.selector(params);
            return temp;
        }

        protected void onPostExecute(JSONArray jsonArray){
            if(temp != null) {
                Toast.makeText(SignUp_Activity.this, "User Created", Toast.LENGTH_SHORT).show();
                success = true;
            }else{
                Toast.makeText(SignUp_Activity.this, "User Creation Failed", Toast.LENGTH_SHORT).show();
                success = false;
            }
        }
    }
}
