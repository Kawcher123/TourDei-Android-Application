package com.example.tourdeiapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class login extends AppCompatActivity {
    CardView cv,cv1;
    EditText email,password,phone,code;
    private FirebaseAuth firebaseAuth;
    ProgressBar pb;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    SharedPreferences sp;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        phone=(EditText)findViewById(R.id.varifyphone);
        code=(EditText)findViewById(R.id.varifycode);

        cv=(CardView)findViewById(R.id.signup);
        cv1=(CardView)findViewById(R.id.signin);

        pb=(ProgressBar)findViewById(R.id.progress1);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        sp = getSharedPreferences("login",MODE_PRIVATE);

        if(sp.getBoolean("logged",false)){
            if(firebaseUser!=null){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);}
        }





        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Phone=phone.getText().toString();

                if(TextUtils.isEmpty(Phone))
                {
                    Toast.makeText(login.this,"Empty Phone",Toast.LENGTH_LONG).show();
                    return;
                }


                else {

                    pb.setVisibility(View.VISIBLE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            Phone,        // Phone number to verify
                            60,                 // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            login.this,               // Activity (for callback binding)
                            callbacks);




                }


            }
        });

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cv1.setVisibility(View.INVISIBLE);
                phone.setVisibility(View.INVISIBLE);
                String varidcode=code.getText().toString();
                if(TextUtils.isEmpty(varidcode))
                {
                    Toast.makeText(login.this,"Enter the code",Toast.LENGTH_LONG).show();
                }
                else
                {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, varidcode);
                    signInWithPhoneAuthCredential(credential);
                }

            }
        });



        callbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Toast.makeText(login.this,"enter correct phone number",Toast.LENGTH_LONG).show();


                cv1.setVisibility(View.VISIBLE);
                phone.setVisibility(View.VISIBLE);


                cv.setVisibility(View.INVISIBLE);
                code.setVisibility(View.INVISIBLE);

            }


            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {


                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                Toast.makeText(login.this,"code has been sent",Toast.LENGTH_LONG).show();

                cv1.setVisibility(View.INVISIBLE);
                phone.setVisibility(View.INVISIBLE);


                cv.setVisibility(View.VISIBLE);
                code.setVisibility(View.VISIBLE);

            }




        };

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            startActivity(new Intent(getApplicationContext(),Signup.class));
                            sp.edit().putBoolean("logged",true).apply();


                        } else {


                            Toast.makeText(getApplicationContext()," failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



}
