package com.example.hangout_v0.Vendor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hangout_v0.R;

import java.util.ArrayList;

public class ReservationAdapterVendor extends RecyclerView.Adapter<ReservationAdapterVendor.RAViewHolder>{
    private static final String TAG = "ReservationAdapter";
    private ArrayList<String> userNames = new ArrayList<>();
    private ArrayList<String> reservationTimes = new ArrayList<>();
    private Context mContext;

    public ReservationAdapterVendor(ArrayList<String> userNames, ArrayList<String> reservationTimes, Context context){
        this.userNames = userNames;
        this.mContext = context;
        this.reservationTimes = reservationTimes;
    }

    @Override
    public RAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_reservation_recycler,parent, false);
        RAViewHolder holder = new RAViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RAViewHolder raViewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

//        Glide.with(mContext)
//                .asBitmap()
//                .load(userNames.get(i))
//                .into(raViewHolder.image);
        raViewHolder.userName.setText(userNames.get(i));
        raViewHolder.time.setText(reservationTimes.get(i));
        raViewHolder.vendorReservationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(mContext, userNames.get(i), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userNames.size();
    }

    public class RAViewHolder extends RecyclerView.ViewHolder{
//        CircleImageView image;
        TextView userName;
        TextView time;
        RelativeLayout vendorReservationLayout;

        public RAViewHolder(@NonNull View itemView) {
            super(itemView);
//            image = itemView.findViewById(R.id.planIcon);
            userName = itemView.findViewById(R.id.UserIdInReservation);
            time = itemView.findViewById(R.id.ReservationTimeInVendor);
            vendorReservationLayout = itemView.findViewById(R.id.vendorReservationLayout);

        }
    }
}
