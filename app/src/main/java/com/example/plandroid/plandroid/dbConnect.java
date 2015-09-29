package com.example.plandroid.plandroid;

import android.app.Application;
import android.net.Uri;
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
public class dbConnect extends Application{

    public static final String GET_USER_ALL="getAllUsers";
    public static final String GET_EVENTS = "getEvent";
    public static final String CREATE_USER = "createUser";
    public static final String DELETE_EVENT = "deleteEvent";
    public static final String CREATE_EVENT = "createEvent";
    public static final String AUTHENTICATE = "authenticate";
    public static final String GET_DEPARTMENT = "GET_DEPARTMENT";
    public String ip_address = null;

    String username;
    String eventname;
    String eventdes;
    String eventlocation;
    String eventprivacy;
    String date;
    String time;
    String other;
    String password;
    String firstname;
    String lastname;
    String department;

    public dbConnect(){

        ip_address = getResources().getString(R.string.ip_address);

        username="";
        eventname="";
        eventdes="";
        eventlocation="";
        eventprivacy="";
        date="";
        time="";
        other="";
        password="";
        firstname="";
        lastname="";
        department="";
    }

    public JSONArray selector (String... instruction){

        //Log.e("Instruction[0]: ", instruction[0]);
        //Log.e("Instruction[1]: ", instruction[1]);
        switch(instruction[0]){
            case GET_USER_ALL:
                //Log.e("In case 0", "calls getAllUsers()");
                return getAllUsers();
            case GET_EVENTS:
                //Log.e("case 2, getEvents", "size of parameter: " + instruction.length);
                return getEvents(instruction);
            case CREATE_USER:
                //Log.e("case 3, sign up user: ", "size of parameter: " + instruction.length);
                return createUser(instruction);
            case DELETE_EVENT:
                return deleteEvent(instruction);
            case CREATE_EVENT:
                return createEvent(instruction);
            case AUTHENTICATE:
                return auth(instruction);
            case GET_DEPARTMENT:
                return getDepartment(instruction);
            default:
                Log.e("Default case", "Shouldn't be here");
                return null;
        }
    }

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

    public String baseUrlBuilder(String ... allParameters){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(ip_address)
                .appendPath(allParameters[0]);

        for(int index = 1; index < allParameters.length; index+=2){
            builder.appendQueryParameter(allParameters[index], allParameters[index+1]);
        }

        return builder.build().toString();
    }

    public JSONArray getAllUsers(){
        return dbRetrieve(baseUrlBuilder(new String[]{GET_USER_ALL}));
    }


    public JSONArray getEvents(String ... params){

        if(params.length >=3) {
            username = params[1];
            eventname = params[2];
            eventlocation = params[3];
            eventprivacy = params[4];
            other = params[5];
        } else {
            return null;
        }

        String url = baseUrlBuilder(GET_EVENTS,
                "username", username,
                "eventname", eventname,
                "eventlocation", eventlocation,
                "eventprivacy", eventprivacy,
                "other", other);
        Log.e("url: ", url);
        return dbRetrieve(url);
    }


    public JSONArray createUser(String ... params){
        if(params.length >= 6) {
            username =  params[1];
            password = params[2];
            firstname = params[3];
            lastname = params[4];
            department = params[5];
        }else{
            return null;
        }
        String url = baseUrlBuilder(CREATE_USER,
                                    "username", username,
                                    "password", password,
                                    "firstname", firstname,
                                    "lastname", lastname,
                                    "department", department);
        return dbRetrieve(url);
    }


    public JSONArray deleteEvent(String ... params) {

        if(params.length >= 6) {
            username = params[1];
            eventname = params[2];
            eventlocation = params[3];
            eventprivacy = params[4];
            other = params[5];
        }else{
            return null;
        }
        String url = baseUrlBuilder(DELETE_EVENT,
                                    "username", username,
                                    "eventname", eventname,
                                    "eventlocation", eventlocation,
                                    "eventprivacy", eventprivacy,
                                    "other", other);
        Log.e("url", url);
        return dbRetrieve(url);
    }


    public JSONArray createEvent(String ... params){

        if(params.length >= 8) {
            username = params[1];
            eventname = params[2];
            eventdes = params[3];
            eventlocation = params[4];
            eventprivacy = params[5];
            date = params[6];
            time = params[7];
        }else{
            return null;
        }
        String url = baseUrlBuilder(CREATE_EVENT,
                                    "username", username,
                                    "eventname", eventname,
                                    "eventdes", eventdes,
                                    "eventlocation", eventlocation,
                                    "eventprivacy", eventprivacy,
                                    "date", date,
                                    "time", time);
        Log.e("url", url);
        return dbRetrieve(url);
    }


    public JSONArray auth(String ... params){

        if(params.length >= 2) {
            username = params[1];
            password = params[2];
        }else{
            return null;
        }
        String url = baseUrlBuilder(AUTHENTICATE,
                                    "username", username,
                                    "password", password);
        Log.e("url", url);
        return dbRetrieve(url);
    }


    public JSONArray getDepartment(String ... params){

        if(params.length >= 2) {
            username = params[1];
            department = params[2];

        }else{
            return null;
        }
        String url = baseUrlBuilder(GET_DEPARTMENT,
                                    "username", username,
                                    "department", department);
        Log.e("url", url);
        return dbRetrieve(url);
    }
}