package com.example.plandroid.plandroid;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by GerardoSLnv on 4/14/2015.
 */
public class dbConnect {

    private JSONArray dbRetrieve(String url){

        //Get HttpResponseObject from the server
        //Get HttpEntity from HttpResponseObject

        HttpEntity httpEntity = null;

        try{
            DefaultHttpClient httpClient = new DefaultHttpClient(); //Default HttpClient
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();

        } catch(ClientProtocolException e) {
            //Describes error in http protocol
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();

        }

        JSONArray jsonArray = null;

        if(httpEntity != null){
            try{
                String entityResponse = EntityUtils.toString(httpEntity);
                Log.e("Entity Response: ", entityResponse);
                jsonArray = new JSONArray(entityResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    public JSONArray getUsers(String name){
        //URL for the server
        String url = "http://65.35.235.139:81/getUser.php?name=" + name;
        //Log.e("In getUsers, url: ", url);

        return dbRetrieve(url);
    }

    public JSONArray getAllUsers(){

        //URL to execute on server
        String url = "http://65.35.235.139:81/getAllUsers.php";
        return dbRetrieve(url);
    }

    //retrieve events for user name
    public JSONArray getEvents(String ... params){

        //params[1] is username and params[2] is ename
        String username = params[1];
        String ename = "";
        if(params.length == 2 && params[2] != null){
            ename = params[2];
        }

        String url = "http://65.35.235.139:81/getEvent.php?username=" + params[1] + "&ename=" + ename;
        //Log.e("getEvents url ", url);
        return dbRetrieve(url);
    }

    public JSONArray createUser(String ... params){
        String username;
        String password;
        String firstname;
        String lastname;
        String d_ID;

        if(params.length >= 5) {
            username = params[1];
            password = params[2];
            firstname = params[3];
            lastname = params[4];
            d_ID = params[5];
        }else{
            return null;
        }
        String url = "http://65.35.235.139:81/createUser.php?username=" + username + "&password=" + password + "&lastname=" + lastname + "&firstname=" + firstname + "&d_ID=" + d_ID;

        return dbRetrieve(url);
    }

    public JSONArray selector (String... instruction){

        //Log.e("Instruction[0]: ", instruction[0]);
        //Log.e("Instruction[1]: ", instruction[1]);
        switch(instruction[0]){
            case "0":
                //Log.e("In case 0", "calls getAllUsers()");
                return getAllUsers();
            case "1":
                //Log.e("In case 1", "calls getUsers " + instruction[1]);
                return getUsers(instruction[1]); //calls getUser and passes in the name as a string
            case"2":
                //Log.e("case 2, getEvents", "size of parameter: " + instruction.length);
                return getEvents(instruction);
            case"3":
                Log.e("case 3, sign up user: ", "size of parameter: " + instruction.length);
                return createUser(instruction);
            default:
                Log.e("Default case", "Shouldn't be here");
                return null;

        }
    }
}