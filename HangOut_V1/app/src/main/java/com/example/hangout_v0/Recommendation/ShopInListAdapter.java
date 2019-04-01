package com.example.hangout_v0.Recommendation;


import android.app.Activity;
import android.net.Uri;
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

import java.util.ArrayList;


public class ShopInListAdapter extends ArrayAdapter<String> {
    private Activity context;

    private String[] name;
    private String[] description;
    private String[] imgid;
    private Float[] rating;
    private String[] distance;
    private String[] carParkCapacity;

    public ShopInListAdapter(Activity context, ArrayList<RecShop> recShopList) {

        super(context, R.layout.shop_item_in_list);
        this.context = context;

        for (int i =0; i<recShopList.size(); i++){
            this.name[i]=recShopList.get(i).getName();
            this.description[i] = recShopList.get(i).getDescription();
            this.imgid[i] = "https://static.boredpanda.com/blog/wp-content/uploads/2017/11/My-most-popular-pic-since-I-started-dog-photography-5a0b38cbd5e1e__880.jpg";
            this.rating[i]=recShopList.get(i).getRating();
            this.distance[i] = recShopList.get(i).getDistance();
            this.carParkCapacity[i] = recShopList.get(i).getCarParkNumbers();

        }
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
        viewHolder.imageView.setImageURI(Uri.parse(imgid[position]));
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