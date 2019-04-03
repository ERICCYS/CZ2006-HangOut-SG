package com.example.hangout_v0.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.youth.banner.Banner;
import java.util.ArrayList;

import com.example.hangout_v0.R;

public class HomeFragment extends Fragment {

    private SearchView mSearchView = null;
    private ListView mListView = null;
    private String[] mDatas = {};

    private ImageView imageButton_1;
    private ImageView imageButton_2;
    private ImageView imageButton_3;
    private ImageView imageButton_4;


    private ArrayList<Integer> images;

    ViewPager viewPager;
    private ViewPager mViewPager;
    private com.example.hangout_v0.Home.CardPagerAdapter mCardAdapter;
    private com.example.hangout_v0.Home.ShadowTransformer mCardShadowTransformer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.homepage_layout, container, false);



        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);

        initImageData();
        Banner banner = (Banner) view.findViewById(R.id.home_banner);
        banner.setImageLoader(new com.example.hangout_v0.Home.GlideImageLoader());
        banner.setImages(images);
        banner.start();


        imageButton_1 = view.findViewById(R.id.home_icon_food);
        imageButton_2 = view.findViewById(R.id.home_icon_entertainment);
        imageButton_3 = view.findViewById(R.id.home_icon_plaza);

        imageButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.google.com/maps/search/restaurants/@1.3509345,103.6767959,15z/data=!3m1!4b1");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(it);
            }
        });

        imageButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.google.com/maps/search/entertainment/@1.3509314,103.6155102,12z/data=!3m1!4b1");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(it);
            }
        });

        imageButton_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.google.com/maps/search/plaza/@1.3509294,103.6155101,12z/data=!3m1!4b1");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(it);
            }
        });

        mViewPager = (ViewPager) view.findViewById(R.id.home_cardViewPager);

        mCardAdapter = new com.example.hangout_v0.Home.CardPagerAdapter();
        mCardAdapter.addCardItem(new com.example.hangout_v0.Home.CardItem(R.string.title_1, R.string.text_1));
        mCardAdapter.addCardItem(new com.example.hangout_v0.Home.CardItem(R.string.title_2, R.string.text_2));
        mCardAdapter.addCardItem(new com.example.hangout_v0.Home.CardItem(R.string.title_3, R.string.text_3));
        mCardAdapter.addCardItem(new com.example.hangout_v0.Home.CardItem(R.string.title_4, R.string.text_4));

        mCardShadowTransformer = new com.example.hangout_v0.Home.ShadowTransformer(mViewPager, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);


        return view;
    }

    private void initImageData() {
        images = new ArrayList<>();
        images.add(R.drawable.home_banner_shop_esora);
        images.add(R.drawable.home_banner_shop_jaan);
        images.add(R.drawable.home_banner_shop_amo);
        images.add(R.drawable.home_banner_shop_folklore);
        images.add(R.drawable.home_banner_shop_humpback);
    }

}

