package com.example.tourdeiapplication.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tourdeiapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    CardView cv;
    ImageView imageView;
    TextView textView,textView2;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentuser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        textView = root.findViewById(R.id.text_gallery);
        textView2 = root.findViewById(R.id.text2);

        firebaseAuth=FirebaseAuth.getInstance();
        currentuser=firebaseAuth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("UserDetail");

        Query query=databaseReference.orderByChild("email").equalTo(currentuser.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String name=""+ds.child("name").getValue();
                    String lastname=""+ds.child("username").getValue();
                    String s1 = name.substring(0, 1).toUpperCase();
                    String name1 = s1 + name.substring(1);

                    String s2 = lastname.substring(0, 1).toUpperCase();
                    String lastname1 = s2 + lastname.substring(1);
                    textView.setText(name1);
                    textView2.setText(lastname1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        return root;
    }
}