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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    //function 1
    public JSONArray getUsers(String name){
        //URL for the server
        String url = "http://65.35.235.139:81/getUser.php?name=" + name;
        //Log.e("In getUsers, url: ", url);

        return dbRetrieve(url);
    }

    //function 0
    public JSONArray getAllUsers(){

        //URL to execute on server
        String url = "http://65.35.235.139:81/getAllUsers.php";
        return dbRetrieve(url);
    }

    //retrieve events for user name
    //function 2
    public JSONArray getEvents(String ... params){

       /* //params[1] is username and params[2] is ename
        String username = params[1];
        String ename = "";
        if(params.length == 2 && params[2] != null){
            ename = params[2];
        }

        String url = "http://65.35.235.139:81/getEvent.php?username=" + params[1] + "&ename=" + ename;
        //Log.e("getEvents url ", url);
        return dbRetrieve(url); */

        String username="";
        String eventname="";
        String eventlocation="";
        String eventprivacy="";
        String other="";

        if(params.length >= 3) {
            try{
                username = params[1];
                eventname = URLEncoder.encode(params[2], "utf-8");
                eventlocation = URLEncoder.encode(params[3], "utf-8");
                eventprivacy = params[4];
                other = params[5];
            }catch ( UnsupportedEncodingException e){
                e.printStackTrace();
            }

        }else{
            return null;
        }
        String url = "http://65.35.235.139:81/getEvent.php?username=" + username + "&ename=" + eventname + "&location=" + eventlocation + "&eventprivacy=" + eventprivacy + "&other=" + other;
        Log.e("url: ", url);
        return dbRetrieve(url);


    }

    //function 3
    public JSONArray createUser(String ... params){
        String username;
        String password;
        String firstname;
        String lastname;
        String d_ID;

        if(params.length >= 6) {
            username =  params[1];
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

    //A.O just added
    //function 4
    public JSONArray deleteEvent(String ... params) {

        String username="";
        String eventname="";
        String eventlocation="";
        String eventprivacy="";
        String other="";


        if(params.length >= 6) {
            try{
            username = params[1];
            eventname = URLEncoder.encode(params[2], "utf-8");
            eventlocation = URLEncoder.encode(params[3], "utf-8");
            eventprivacy = params[4];
            other = params[5];
            }catch ( UnsupportedEncodingException e){
                e.printStackTrace();
            }

        }else{
            return null;
        }
        String url = "http://65.35.235.139:81/deleteEvent.php?username=" + username + "&ename=" + eventname + "&location=" + eventlocation + "&eventprivacy=" + eventprivacy + "&other=" + other;
        Log.e("url", url);
        return dbRetrieve(url);
    }

    //function 5
    public JSONArray createEvent(String ... params){
        String username="";
        String eventname="";
        String eventdes="";
        String eventlocation="";
        String eventprivacy="";
        String date="";
        String time="";


        if(params.length >= 8) {
            try{
                username = params[1];
                eventname = URLEncoder.encode(params[2], "utf-8");
                eventdes = URLEncoder.encode(params[3], "utf-8");
                eventlocation = URLEncoder.encode(params[4], "utf-8");
                eventprivacy = params[5];
                date = params[6];
                time = params[7];
            }catch ( UnsupportedEncodingException e){
                e.printStackTrace();
            }

        }else{
            return null;
        }
        String url = "http://65.35.235.139:81/createEvent.php?username=" + username + "&ename=" + eventname + "&eventdes=" + eventdes + "&location=" + eventlocation +
                "&eventprivacy=" + eventprivacy + "&date=" + date + "&time=" + time;
        Log.e("url", url);
        return dbRetrieve(url);
    }

    //function 6
    public JSONArray auth(String ... params){

        String username="";
        String password="";
        if(params.length >= 2)
        {
            username = params[1];
            password = params[2];

        }else{
            return null;
        }
        //URL to execute on server
        String url = "http://65.35.235.139:81/auth.php?username=" + username + "&password=" + password;
        Log.e("url", url);
        return dbRetrieve(url);
    }

    //function 7
    public JSONArray getDepartment(String ... params){

        String username="";
        String dep_id="";
        if(params.length >= 2)
        {
            username = params[1];
            dep_id = params[2];

        }else{
            return null;
        }
        //URL to execute on server
        String url = "http://65.35.235.139:81/getUser.php?username=" + username + "&department=" + dep_id;
        Log.e("url", url);
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
                //Log.e("case 3, sign up user: ", "size of parameter: " + instruction.length);
                return createUser(instruction);
            case"4":
                return deleteEvent(instruction);
            case"5":
                return createEvent(instruction);
            case"6":
                return auth(instruction);
            case"7":
                return getDepartment(instruction);
            default:
                Log.e("Default case", "Shouldn't be here");
                return null;

        }
    }
}