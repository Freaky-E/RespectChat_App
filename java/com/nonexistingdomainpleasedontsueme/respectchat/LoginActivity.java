package com.nonexistingdomainpleasedontsueme.respectchat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private EditText mLoginEmail;
    private EditText mLoginPassword;

    private Button mLogin_btn;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mToolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mLoginEmail = findViewById(R.id.login_email);
        mLoginPassword = findViewById(R.id.login_password);
        mLogin_btn = findViewById(R.id.login_btn);

        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mLoginEmail.getEditableText().toString().trim();
                String password = mLoginPassword.getEditableText().toString().trim();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {

                    loginUser(email, password);

                }


            }
        });


    }

    private void loginUser(String email, String password) {

         mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {

               if (task.isSuccessful()) {

                   Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                   mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(mainIntent);
                   finish();

               } else {

                   Toast.makeText(LoginActivity.this, "Who are you ?", Toast.LENGTH_LONG).show();


               }

             }
         });

    }
}
