package com.example.tourdeiapplication.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
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

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    EditText name,user,email,phone,gender;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentuser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        name=root.findViewById(R.id.editname);
        user=root.findViewById(R.id.edituser);
        email=root.findViewById(R.id.editemail);
        phone=root.findViewById(R.id.editphone);
        gender=root.findViewById(R.id.editgender);




        firebaseAuth=FirebaseAuth.getInstance();
        currentuser=firebaseAuth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("UserDetail");

        Query query=databaseReference.orderByChild("email").equalTo(currentuser.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String name1=""+ds.child("name").getValue();
                    String s1 = name1.substring(0, 1).toUpperCase();
                    String name2 = s1 + name1.substring(1);
                    String username1=""+ds.child("username").getValue();
                    String email1=""+ds.child("email").getValue();
                    String phone1=""+ds.child("phone").getValue();
                    String gender1=""+ds.child("gender").getValue();
                    name.setText(name2);
                    user.setText(username1);
                    email.setText(email1);
                    phone.setText(phone1);
                    gender.setText(gender1);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //final TextView textView = root.findViewById(R.id.text_tools);
       /* toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}