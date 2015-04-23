package com.example.plandroid.plandroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Toast;
import android.widget.TextView;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;



public class SignInActivity extends ActionBarActivity implements OnClickListener{

    Intent myIntent;
    Bundle bundle = new Bundle();
    boolean success = false; //check to see if query is successful
    public String username = "";
    public String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        //Button References
        Button log_in = (Button) this.findViewById(R.id.buttonLgn); //changed the name of the buttons to represent what they do, this one is Login
        Button sign_up = (Button) this.findViewById(R.id.buttonSU); //changed the name of button to represent signing up
        Button testQry = (Button) this.findViewById(R.id.btnQuery); //test query is this button



        log_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);
        testQry.setOnClickListener(this);

    }


    //we will use an onClick listener which is a separate function that will handle what to do
    //when a button is pressed on this screen
    @Override
    public void onClick(View v){

        switch (v.getId()){

            //btnQuery goes to another activity screen
            case R.id.btnQuery:
                //Toast.makeText(SignInActivity.this, "btnQuery Button Pressed", Toast.LENGTH_SHORT).show();
                myIntent = new Intent(SignInActivity.this, TestQuery.class);
                startActivity(myIntent);
                break;

            //this button will save the text fields and pass them into the database to make sure the result is one row
            //to indicate valid and successful login otherwise deny entry
            case R.id.buttonLgn:

                //Text References. Gets the texts the user enters
                EditText username_ET = (EditText)findViewById(R.id.in_username);
                EditText password_ET = (EditText)findViewById(R.id.in_password);

                //Converts the user inputs into strings and assigns them.
                //So the strings "username" and "password" can be used to query the database
                username = username_ET.getText().toString(); //GS WHAT DOES THIS DO?
                password = password_ET.getText().toString(); //Shouldn't this go where the login button is pressed? YOU RIGHT

                bundle.putString("USERNAME", username);

                //AsyncTask to sign up the user
                new authenticate().execute("6", username, password);
                break;


            //this button will redirect to another activity that will provide fields for the user to sign up
            case R.id.buttonSU:
                //Log.e("In buttonSU, success: ", Boolean.toString(success));
                //Toast.makeText(SignInActivity.this, "Sign Up Button Pressed", Toast.LENGTH_SHORT).show();
                myIntent = new Intent(SignInActivity.this, SignUp_Activity.class);
                startActivity(myIntent);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
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

    private void onSuccess(boolean success){
        if(success) { //true
            //Log.e("Succesful", "going to Options_Activity");
            myIntent = new Intent(SignInActivity.this, Options_Activity.class);
            myIntent.putExtras(bundle);
            startActivityForResult(myIntent, 0);
        }else if(!success){
            //Log.e("Failed", "do nothing");
            return;
        }

    }





    public class authenticate extends AsyncTask<String, Long, JSONArray>{

        JSONArray temp = null;
        dbConnect handle = new dbConnect();

        @Override
        protected JSONArray doInBackground(String ... params){
            temp = handle.selector(params);
            return temp;
        }

        protected void onPostExecute(JSONArray jsonArray){
            if(temp != null) {
                //Log.e("JSONArray", "not null");
                Toast.makeText(SignInActivity.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                onSuccess(true);
                return;
            }else{
                //Log.e("JSONArray", "NULL");
                Toast.makeText(SignInActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                onSuccess(false);
                return;
            }
        }
    }
}