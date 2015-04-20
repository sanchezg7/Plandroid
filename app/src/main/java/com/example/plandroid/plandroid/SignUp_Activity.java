package com.example.plandroid.plandroid;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
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
    boolean success = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);


        //Button References
        Button sign_up = (Button) this.findViewById(R.id.signUpB);

        //A.O. not sure about the line of code below
        //username = usernameTextView.toString(); //this will be added to the database using JDBC
        sign_up.setOnClickListener(this);

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
            newUser.setPassword(password_ET.getText().toString()); //A.O. ensure 2 passwords are the same

            //AsyncTask to sign up the user
            new SignUpUser().execute("4", newUser.getFirstname(), newUser.getLastname(), newUser.getPassword());

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
            return handle.selector(params[0]);
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
