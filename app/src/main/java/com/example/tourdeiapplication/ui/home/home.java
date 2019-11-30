package com.example.tourdeiapplication.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tourdeiapplication.R;

public class home extends AppCompatActivity {

    CardView cv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

         cv1=(CardView)findViewById(R.id.maketrip1);

         cv1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(home.this, Make_aTrip.class);
                 startActivity(intent);
             }
         });
    }
}
