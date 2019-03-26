package com.example.hangout_v0.Recommendation;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hangout_v0.R;


public class ShopInListAdapter extends ArrayAdapter<String> {
    private Activity context;

    private String[] name;
    private String[] description;
    private Integer[] imgid;
    private Float[] rating;
    private String[] distance;
    private String[] carParkCapacity;

    public ShopInListAdapter(Activity context, String[] name, String[] description, Integer[] imgid, Float[] rating, String[] distance, String[] carParkCapacity) {
        super(context, R.layout.shop_item_in_list, name);
        this.context = context;
        this.name=name;
        this.description=description;
        this.imgid=imgid;
        this.rating=rating;
        this.distance=distance;
        this.carParkCapacity=carParkCapacity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;

        if (r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.shop_item_in_list,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) r.getTag();
        }

        viewHolder.nameView.setText(name[position]);
        viewHolder.descriptionView.setText(description[position]);
        viewHolder.imageView.setImageResource(imgid[position]);
        viewHolder.ratingView.setRating(rating[position]);
        viewHolder.distanceView.setText(distance[position]);
        viewHolder.carParkCapacityView.setText(carParkCapacity[position]);

        return r;
    }

}

class ViewHolder{
    TextView nameView;
    TextView descriptionView;
    ImageView imageView;
    RatingBar ratingView;
    TextView distanceView;
    TextView carParkCapacityView;

    ViewHolder(View v){
        nameView = v.findViewById(R.id.nameTextView);
        descriptionView = v.findViewById(R.id.descriptionTextView);
        imageView = v.findViewById(R.id.imageView);
        ratingView = v.findViewById(R.id.ratingBar);
        distanceView = v.findViewById(R.id.distanceTextView);
        carParkCapacityView = v.findViewById(R.id.carParkCapacityTextView);
    }
}