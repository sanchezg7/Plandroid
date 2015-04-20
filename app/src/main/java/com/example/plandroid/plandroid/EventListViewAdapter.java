package com.example.plandroid.plandroid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by GerardoSLnv on 4/20/2015.
 */
public class EventListViewAdapter extends BaseAdapter {

    private JSONArray dataArray;
    private Activity activity;

    private static LayoutInflater inflater = null;

    public EventListViewAdapter(JSONArray jsonArray, Activity activity){
        this.dataArray = jsonArray;
        this.activity = activity; //change a to the right thing

        inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return this.dataArray.length();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //set up convert view if it is null
        ListCell cell;
        if(convertView == null){ //need to initialize the view here
            //infate ask for the view to inflate and the root
            convertView = inflater.inflate(R.layout.search_event_listview_cell, null);
            cell = new ListCell();

            //what the view will contain
            cell.eName = (TextView) convertView.findViewById(R.id.view_event_name);
            cell.eDate = (TextView) convertView.findViewById(R.id.view_event_date);
            cell.eDes = (TextView) convertView.findViewById(R.id.view_event_des);

            //set the tag to allow for reuse of this cell
            convertView.setTag(cell);

        }else{ //if not null, it will reuse the existing cell and change the data of the cell here
            cell = (ListCell) convertView.getTag();

        }
        try {

            JSONObject jsonObject = this.dataArray.getJSONObject(position);
            cell.eName.setText(jsonObject.getString("ename"));
            cell.eDate.setText(jsonObject.getString("date"));
            cell.eDes.setText(jsonObject.getString("Description"));

        } catch(JSONException e){
            e.printStackTrace();
        }

        return convertView;
    }


    //template for the cell components
    private class ListCell{
        private TextView eName;
        private TextView eDate;
        private TextView eDes;

    }
}
