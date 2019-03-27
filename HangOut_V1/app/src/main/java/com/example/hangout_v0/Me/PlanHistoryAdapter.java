package com.example.hangout_v0.Me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hangout_v0.R;

public class PlanHistoryAdapter extends BaseAdapter {
    String[] planName;
    String[] planDescription;
    String[] planDateTime;
    LayoutInflater mInflater;

    public PlanHistoryAdapter(Context c, String[] name, String[] desc, String[] dateTime ){
        planName = name;
        planDescription = desc;
        planDateTime = dateTime;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return planName.length;
    }

    @Override
    public Object getItem(int position) {
        return planName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= mInflater.inflate(R.layout.me_plan_plan_list,null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.descriptionTextView);
        TextView dateTimeTextView = (TextView) v.findViewById(R.id.planHistoryDateTimeTV);

        String name = planName[position];
        String desc = planDescription[position];
        String dati = planDateTime[position];

        nameTextView.setText(name);
        descriptionTextView.setText(desc);
        dateTimeTextView.setText(dati);
        return v;
    }
}
