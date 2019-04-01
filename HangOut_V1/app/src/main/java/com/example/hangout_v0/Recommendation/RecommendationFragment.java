package com.example.hangout_v0.Recommendation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;
//import com.example.hangout_v0.ShopDetail.ShopInDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RecommendationFragment extends Fragment {

    ListView recShopListView;
    ArrayList<RecShop> recShopList=new ArrayList<>();


//        Collections.shuffle(List<?> list, Random rnd):


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recommendation, container, false);
        recShopListView = view.findViewById(R.id.rec_shop_listView);


        OkHttpClient client = new OkHttpClient();
        String url = HangOutApi.baseUrl + "shops";
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    System.out.println("*******************************************");
                    System.out.println(myResponse);
                    System.out.println("*******************************************");
                    try {
                        JSONArray jsonRecShopList = new JSONArray(myResponse);
                        HangOutData.setShopList(jsonRecShopList);


                        try {
                            System.out.print("Get success");

                            for (int i = 0; i < jsonRecShopList.length(); i++) {
                                JSONObject jsonRecShop = jsonRecShopList.getJSONObject(i);
                                recShopList.add(new RecShop(
                                        jsonRecShop.get("id").toString(),
                                        jsonRecShop.get("name").toString(),
                                        jsonRecShop.get("contactNumber").toString(),
                                        jsonRecShop.get("contactEmail").toString(),
                                        jsonRecShop.get("category").toString(),
                                        jsonRecShop.get("location").toString(),
                                        jsonRecShop.get("carParkNumbers").toString()));
                            }
                            System.out.println("############################################");
                            System.out.println("\n\nthe size is "+recShopList.size()+"\n\n");
                            System.out.println("############################################");

                        } catch (JSONException e) {
                            //textView.setText("Error");
                        }
                    } catch (JSONException e) {

                    }
                }
            }
        });


        com.example.hangout_v0.Recommendation.ShopInListAdapter shopInListAdapter = new com.example.hangout_v0.Recommendation.ShopInListAdapter(getActivity(), recShopList);
        recShopListView.setAdapter(shopInListAdapter);

        recShopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String chosenShopId = recShopList.get(position).getShopId();
//                Intent shopDetailActivity = new Intent(view.getContext(), ShopInDetail.class);
//                shopDetailActivity.putExtra("shopId",chosenShopId);
//                startActivity(shopDetailActivity);
            }
        });

        return view;
    }
}

//         later call API to get data
//        String[] name = {"peach","mango","apple","pear","watermelon","cherry"};
//        String[] description = {"She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
//                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
//                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
//                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
//                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
//                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. "};
//        Integer[] imgid = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6};
//        Float[] rating = {4.0f,3.0f,4.3f,4.0f,4.2f,4.1f};
//        String[] distance = {"4.0","3.0","4.3","4.0","4.2","4.1"};
//        String[] carParkCapacity = {"90%","80%","80%","80%","80%","80%"};
