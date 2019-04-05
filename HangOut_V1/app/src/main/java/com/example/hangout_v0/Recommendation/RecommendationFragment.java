package com.example.hangout_v0.Recommendation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hangout_v0.R;
import com.example.hangout_v0.ShopDetail.ShopInDetail;

import java.util.ArrayList;


public class RecommendationFragment extends Fragment {

    ListView recShopListView;

    public ArrayList<String> ids;
    public ArrayList<String> names;
    public ArrayList<String> contactNumbers;
    public ArrayList<String> contactEmails;
    public ArrayList<String> categories;
    public ArrayList<String> locations;
    public ArrayList<String> carParkNumbers;

    public float[] rating = {4.0f, 3.0f, 4.3f, 4.0f, 4.2f, 4.1f, 4.0f, 3.0f, 4.3f, 4.0f, 4.2f, 4.1f, 4.0f, 3.0f, 4.3f, 4.0f, 4.2f, 4.1f, 4.0f, 3.0f, 4.3f, 4.0f, 4.2f, 4.1f};
    public int[] image = {R.drawable.shotpot, R.drawable.macdonald, R.drawable.longjohn};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ids = this.getArguments().getStringArrayList("ids");
        names = this.getArguments().getStringArrayList("names");
        contactNumbers = this.getArguments().getStringArrayList("contactNumbers");
        contactEmails = this.getArguments().getStringArrayList("contactEmails");
        categories = this.getArguments().getStringArrayList("categories");
        locations = this.getArguments().getStringArrayList("locations");
        carParkNumbers = this.getArguments().getStringArrayList("carParkNumbers");


        View view = inflater.inflate(R.layout.fragment_recommendation, container, false);
        recShopListView = view.findViewById(R.id.rec_shop_listView);

        com.example.hangout_v0.Recommendation.ShopInListAdapter shopInListAdapter = new com.example.hangout_v0.Recommendation.ShopInListAdapter(getActivity(), names, locations, image, rating, carParkNumbers);

        recShopListView.setAdapter(shopInListAdapter);

        recShopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent shopDetailActivity = new Intent(view.getContext(), ShopInDetail.class);

                shopDetailActivity.putExtra("chosenShopId", ids.get(position));
                shopDetailActivity.putExtra("chosenShopName", names.get(position));
                shopDetailActivity.putExtra("chosenShopLocation", locations.get(position));
                shopDetailActivity.putExtra("chosenShopPhone", contactNumbers.get(position));
                shopDetailActivity.putExtra("chosenShopEmail", contactEmails.get(position));
                shopDetailActivity.putExtra("chosenShopRating", Float.toString(rating[position]));
                shopDetailActivity.putExtra("chosenShopCarpark", carParkNumbers.get(position));
                startActivity(shopDetailActivity);
            }
        });

        return view;
    }
}

