package com.example.tourdeiapplication.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tourdeiapplication.R;
import com.example.tourdeiapplication.Welcome;
import com.example.tourdeiapplication.login;
import com.google.firebase.auth.FirebaseAuth;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;
    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
      button=root.findViewById(R.id.signout);


      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              FirebaseAuth.getInstance().signOut();
              Intent intent=new Intent(getActivity(), Welcome.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
              startActivity(intent);
          }
      });

        return root;
    }
}