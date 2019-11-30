package com.example.tourdeiapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tourdeiapplication.R;
import com.example.tourdeiapplication.TravelBlogs;
import com.example.tourdeiapplication.TravellingPlace;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    CardView cv1,cv2,cv3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
       // final TextView textView = root.findViewById(R.id.text_home);
            cv1=root.findViewById(R.id.maketrip1);
            cv2=root.findViewById(R.id.places1);

            cv3=root.findViewById(R.id.blogs1);

      /*  homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Make_aTrip.class);
                startActivity(intent);
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(), TravellingPlace.class);
                startActivity(intent1);
            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(getActivity(), TravelBlogs.class);
                startActivity(intent2);
            }
        });
        return root;
    }





}