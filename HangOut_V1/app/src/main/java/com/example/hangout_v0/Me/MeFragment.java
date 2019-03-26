package com.example.hangout_v0.Me;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hangout_v0.R;

public class MeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        CardView avator = view.findViewById(R.id.meAvatorWrapper);
//        AppCompatButton signInSignUp = view.findViewById(R.id.meSignInSignUpButton);
        LinearLayout reservation = view.findViewById(R.id.MeReservationLinearLayout);
        LinearLayout plan = view.findViewById(R.id.MePlanLinearLayout);
        LinearLayout preference = view.findViewById(R.id.fragmentPreferences);
        LinearLayout account = view.findViewById(R.id.fragmentMyAccount);
        LinearLayout helpCenter = view.findViewById(R.id.fragmentHelpCenter);
        LinearLayout more = view.findViewById(R.id.fragmentMore);

        avator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "this is to change avator", Toast.LENGTH_SHORT).show();
            }
        });

//        signInSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "this is to sign in / sign up", Toast.LENGTH_SHORT).show();
//            }
//        });

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "this is to RESERVATION", Toast.LENGTH_SHORT).show();
            }
        });

        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "this is to PLANS", Toast.LENGTH_SHORT).show();
            }
        });

        preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "this is to change PREFERENCE", Toast.LENGTH_SHORT).show();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "this is to ACCOUNT", Toast.LENGTH_SHORT).show();
            }
        });

        helpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "this is to HELP CENTER", Toast.LENGTH_SHORT).show();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "this is to MORE", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
