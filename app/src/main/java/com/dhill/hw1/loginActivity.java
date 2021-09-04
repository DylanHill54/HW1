package com.dhill.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class loginActivity extends AppCompatActivity {
    private EditText mUsername;
    private android.widget.EditText mPassword;
    private String mUsernameString;
    private String mPasswordString;
    user mUser=null;
    private int mUserid;
    private Button mButton;
    private List<user> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        users=new ArrayList<user>();
        user user1=new user("test1","test1",1);
        user user2=new user("test2","test2",2);
        user user3=new user("test3","test3",3);
        user user4=new user("test4","test4",4);
        user user5=new user("test5","test5",5);
        user user6=new user("test6","test6",6);
         users.add(user1);
         users.add(user2);
        users.add(user3);
        users.add(user4);
         users.add(user5);
        users.add(user6);
        wireUpDisplay();
    }



    private void getValuesFromDisplay() {
        mUsernameString=mUsername.getText().toString();
        mPasswordString=mPassword.getText().toString();
    }
    private void wireUpDisplay(){
        mUsername=findViewById(R.id.editTextTextLoginUserName);
        mPassword=findViewById(R.id.editTextTextLoginPassword);

        mButton=findViewById(R.id.buttonLogin);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if (checkForUserInDataBase(mUsernameString) ){
                    if(!validatePassword(mPasswordString)){
                        Toast.makeText(loginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent=MainActivity.intentFactory(getApplicationContext(),mUserid);
                        startActivity(intent);

                    }
                }
            }

        });
    }

    private boolean validatePassword(String mPasswordString) {
        if(mUser.getPassword().equals(mPasswordString)) {
            mUserid=mUser.getUserId();
            return true;
        }
        return false;
    }

    private boolean checkForUserInDataBase(String mUsernameString) {
        for (user u : users){
            if (u.getUserName().equals(mUsernameString)){
                mUser=u;
            }
        }
        if(mUser==null){
            Toast.makeText(this, "no user " +mUsernameString+" found",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }






    public static Intent intentFactory(Context context){
        Intent intent =new Intent(context, loginActivity.class);
        return intent;

    }
}