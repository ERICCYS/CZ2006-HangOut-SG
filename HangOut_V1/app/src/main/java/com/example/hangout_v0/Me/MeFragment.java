package com.example.hangout_v0.Me;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.Me.avator.UserProfile;
import com.example.hangout_v0.Me.plan.PlanHistoryActivity;
import com.example.hangout_v0.Me.reservation.ReservationActivity;
import com.example.hangout_v0.R;

public class MeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        LinearLayout reservation = view.findViewById(R.id.MeReservationLinearLayout);
        LinearLayout plan = view.findViewById(R.id.MePlanLinearLayout);
        LinearLayout account = view.findViewById(R.id.fragmentMyAccount);
        LinearLayout helpCenter = view.findViewById(R.id.fragmentHelpCenter);
        LinearLayout more = view.findViewById(R.id.fragmentMore);
        LinearLayout signOut = view.findViewById(R.id.custSignOutButton);


        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MeMoreActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getActivity();
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Confirming sign out...");
                    builder.setMessage("Are you sure you want to sign out?");
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    HangOutData.setCustomer(null);
                                    HangOutData.setAccessToken(null);
                                    HangOutApi.accessToken = null;
                                    Intent intent = new Intent(getActivity(), com.example.hangout_v0.Login.LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
