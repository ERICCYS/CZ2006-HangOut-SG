package com.example.hangout_v0.Me.plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hangout_v0.R;

import java.util.ArrayList;

public class PlanHistoryAdapter extends BaseAdapter {
    ArrayList<String> planName;
    ArrayList<String> planDateTime;
    LayoutInflater mInflater;

    public PlanHistoryAdapter(Context c, ArrayList<String> name, ArrayList<String> dateTime ){
        planName = name;
        planDateTime = dateTime;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return planName.size();
    }

    @Override
    public Object getItem(int position) {
        return planName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= mInflater.inflate(R.layout.me_plan_plan_list,null);
        TextView nameTextView = (TextView) v.findViewById(R.id.me_plan_planHistoryNameTextView);
        TextView dateTimeTextView = (TextView) v.findViewById(R.id.me_plan_planHistoryDateTimeTV);

        String name = planName.get(position);
        String date = planDateTime.get(position);

        nameTextView.setText(name);
        dateTimeTextView.setText(date);
        return v;
    }
}
