package com.dhill.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPLaceHolderAPI jsonPlaceHolderApi;
    int mUserId = -1;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);
        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPLaceHolderAPI.class);
        checkForUser();
        getPosts();
        //getComments();
        //createPost();
        //updatePost();
        //deletePost();
    }

    private void checkForUser() {
        mUserId=getIntent().getIntExtra("mUserId",0);
        if(mUserId==0){
            Intent intent= loginActivity.intentFactory(this);
            startActivity(intent);
        }
    }

    private void getPosts(){
        String id=Integer.toString(mUserId);
        Map<String, String> parameters=new HashMap<>();
        parameters.put("userId",id);
        parameters.put("_sort","id");
        parameters.put("_order", "desc");
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    textViewResult.append(content);
                }

            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
    public static Intent intentFactory(Context context, int userId) {
        Intent intent =new Intent(context, MainActivity.class);
        intent.putExtra("mUserId",userId);
        return intent;


    }
}