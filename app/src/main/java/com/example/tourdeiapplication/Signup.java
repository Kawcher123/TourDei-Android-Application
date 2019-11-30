package com.example.tourdeiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText name,username,email,password,phone,gender;
    CardView button;
    private FirebaseAuth firebaseAuth;
    ProgressBar pb;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=(EditText)findViewById(R.id.name1);
        username=(EditText)findViewById(R.id.username1);
        email=(EditText)findViewById(R.id.email1);
        password=(EditText)findViewById(R.id.password1);
        phone=(EditText)findViewById(R.id.phoneno1);
        gender=(EditText)findViewById(R.id.gender1);
        button=(CardView)findViewById(R.id.signup1);

        pb=(ProgressBar)findViewById(R.id.progress1);

        firebaseAuth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("UserDetail");






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name1=name.getText().toString().trim();
                final String user=username.getText().toString().trim();
                final String email1=email.getText().toString().trim();
                final String pass=password.getText().toString().trim();
                final String phone1=phone.getText().toString().trim();
                final String gender1=gender.getText().toString().trim();

                if(TextUtils.isEmpty(name1))
                {
                    Toast.makeText(Signup.this,"Emapty name",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(user))
                {
                    Toast.makeText(Signup.this,"Empty username",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(email1))
                {
                    Toast.makeText(Signup.this,"Empty email",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(pass))
                {
                    Toast.makeText(Signup.this,"Empty password",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(phone1))
                {
                    Toast.makeText(Signup.this,"Empty phone number",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(gender1))
                {
                    Toast.makeText(Signup.this,"Empty gender",Toast.LENGTH_LONG).show();
                    return;
                }


                else {
                    pb.setVisibility(View.VISIBLE);

                    firebaseAuth.createUserWithEmailAndPassword(email1, pass)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    pb.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        UserDetail information=new UserDetail(name1,user,email1,phone1,gender1);
                                        FirebaseDatabase.getInstance().getReference("UserDetail")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                            }
                                        });


                                    } else {
                                        Toast.makeText(Signup.this,"Authentication failed",Toast.LENGTH_LONG).show();

                                    }

                                    // ...
                                }
                            });

                }





            }
        });

    }
}
