package com.sgh.apk1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    Button btn_login;
    private FirebaseAuth mAuth;
    private String email, password;
    String check,check_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Login Form");
        mAuth = FirebaseAuth.getInstance();
        //mAuth.signOut();

        //For Signin
        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=editEmail.getText().toString().trim();
                String password=editPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(LoginActivity.this,"Please enter email",Toast.LENGTH_SHORT);
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginActivity.this,"Please enter password",Toast.LENGTH_SHORT).show();
                }
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Query query= FirebaseDatabase.getInstance().getReference("member").orderByChild("userType").equalTo("amc");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    ValueEventListener postListener = new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            //    UsersBean usersBean=new UsersBean(UsersBean.class);
                                            for(DataSnapshot ds:dataSnapshot.getChildren())
                                            {
                                                if(String.valueOf(ds.getValue()).equals("DataSnapshot { key = userType, value = amc }"))
                                                {
                                                    startActivity(new Intent(getApplicationContext(),AmcActivity.class));
                                                }
                                                else
                                                {
                                                    startActivity(new Intent(getApplicationContext(),AmcActivity.class));
                                                }

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    };

                                    query.addListenerForSingleValueEvent(postListener);

                                   /* if(check!=null && check.equalsIgnoreCase("amc"))
                                    {
                                        Toast.makeText(LoginActivity.this,"user is amc",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),AmcActivity.class));

                                    }
                                    else
                                    {
                                        Toast.makeText(LoginActivity.this,"user is normal",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                    }*/
//                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                }
                            }
                        });
            }
        });


    }


    public void btn_signup(View view) {
        startActivity(new Intent(getApplicationContext(),SignupActivity.class));
    }
}