package com.example.hangout_v0.Me;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.Login.LoginActivity;
import com.example.hangout_v0.Me.avator.UserProfile;
import com.example.hangout_v0.Me.plan.PlanDetailActivity;
import com.example.hangout_v0.Me.plan.PlanHistoryActivity;
import com.example.hangout_v0.Me.reservation.ReservationActivity;
import com.example.hangout_v0.R;
import com.example.hangout_v0.UserMainActivity;

public class MeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        //CardView avator = view.findViewById(R.id.meAvatorWrapper);
//        AppCompatButton signInSignUp = view.findViewById(R.id.meSignInSignUpButton);
        LinearLayout reservation = view.findViewById(R.id.MeReservationLinearLayout);
        LinearLayout plan = view.findViewById(R.id.MePlanLinearLayout);
//        LinearLayout preference = view.findViewById(R.id.fragmentPreferences);
        LinearLayout account = view.findViewById(R.id.fragmentMyAccount);
        LinearLayout helpCenter = view.findViewById(R.id.fragmentHelpCenter);
        LinearLayout more = view.findViewById(R.id.fragmentMore);
        LinearLayout signOut = view.findViewById(R.id.custSignOutButton);

//        avator.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        signInSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "this is to sign in / sign up", Toast.LENGTH_SHORT).show();
//            }
//        });



        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "this is to RESERVATION", Toast.LENGTH_SHORT).show();
                Intent reservationActivity = new Intent(getActivity(), ReservationActivity.class);
                getActivity().startActivity(reservationActivity);
            }
        });

        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), PlanHistoryActivity.class);
                getActivity().startActivity(myIntent);

            }
        });

//        preference.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "this is to change PREFERENCE", Toast.LENGTH_SHORT).show();
//            }
//        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileActivity = new Intent(getActivity(), UserProfile.class);
                getActivity().startActivity(profileActivity);
            }
        });

        helpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MeHelpCenter.class);
                getActivity().startActivity(myIntent);
                //Toast.makeText(v.getContext(), "this is to HELP CENTER", Toast.LENGTH_SHORT).show();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MeMoreActivity.class);
                getActivity().startActivity(myIntent);
                //Toast.makeText(v.getContext(), "this is to MORE", Toast.LENGTH_SHORT).show();
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getActivity();
                try{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Confirming sign out...");
                    builder.setMessage("Are you sure you want to sign out?");
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // reset customer in Hangout Data and go to log in page
                                    HangOutData.setCustomer(null);
                                    HangOutData.setAccessToken(null);
                                    Intent intent = new Intent(getActivity(), com.example.hangout_v0.Login.LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // stay here
                            dialog.cancel();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
