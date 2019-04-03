package com.example.hangout_v0.Recommendation;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hangout_v0.R;

import java.util.ArrayList;
import java.util.Random;


public class ShopInListAdapter extends ArrayAdapter<String> {
    private Activity context;

    private ArrayList<String> name;
    private ArrayList<String> locations;
    private ArrayList<String> carParkCapacity;

    private int[] image;
    private float[] rating;



    public ShopInListAdapter(Activity context, ArrayList<String> name, ArrayList<String> locations, int[] image, float[] rating, ArrayList<String> carParkCapacity) {
        super(context, R.layout.shop_item_in_list, name);
        this.context = context;

        this.name=name;
        this.locations=locations;
        this.image=image;
        this.rating=rating;
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

        viewHolder.nameView.setText(name.get(position));
        viewHolder.descriptionView.setText(locations.get(position));
        if (position==0)
            viewHolder.imageView.setImageResource(image[0]);
        else if (position==1)
            viewHolder.imageView.setImageResource(image[1]);
        else
            viewHolder.imageView.setImageResource(image[2]);
        viewHolder.ratingView.setRating(rating[new Random().nextInt(5)]);
        viewHolder.distanceView.setText("");
        viewHolder.carParkCapacityView.setText(carParkCapacity.get(position));

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

