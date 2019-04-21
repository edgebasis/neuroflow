package com.assesment.neuroflow.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.assesment.neuroflow.R;
import com.assesment.neuroflow.data.User;
import com.google.gson.Gson;
import com.vaibhavlakhera.circularprogressview.CircularProgressView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<User> usersList = new ArrayList<>();
    private User user;
    private TextView timeStamp;
    private TextView name;
    private CircularProgressView progressCircle;
    private ImageView backArrow;
    private ImageView profilePic;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();

        user = gson.fromJson(getIntent().getStringExtra("user"), User.class);

        setup();

    }

    private void setup() {

        backArrow = findViewById(R.id.iv_BackArrow);
        backArrow.setOnClickListener((View v) -> {
                finish();
        });

        profilePic = findViewById(R.id.iv_profilePic);
        timeStamp = findViewById(R.id.tvTimeStamp);
        name = findViewById(R.id.tvName);
        timeStamp.setText(user.getTimestamp());
        name.setText(user.getName());

        profilePic.setImageResource(user.getImage());
        progressCircle = findViewById(R.id.progressCircle);
        progressCircle.setProgress(user.getScore(),true);



    }



}
