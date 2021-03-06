package com.assesment.neuroflow.ui.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.assesment.neuroflow.R;
import com.assesment.neuroflow.data.Gender;
import com.assesment.neuroflow.data.User;
import com.assesment.neuroflow.utils.Common;
import com.google.gson.Gson;
import com.vaibhavlakhera.circularprogressview.CircularProgressView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.assesment.neuroflow.ui.main.HomeActivity.getResourceId;


public class MainActivity extends AppCompatActivity {


    private Gender user;
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

        user = gson.fromJson(getIntent().getStringExtra("user"), Gender.class);

        setup();

    }

    private void setup() {

        backArrow = findViewById(R.id.iv_BackArrow);
        backArrow.setOnClickListener((View v) -> {
                finish();
        });

        String date;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Common.DATE_PATTERN);

        date = simpleDateFormat.format(new Date((user.getDate_created())));

        profilePic = findViewById(R.id.iv_profilePic);
        timeStamp = findViewById(R.id.tvTimeStamp);
        name = findViewById(R.id.tvName);
        timeStamp.setText(date);
        name.setText(Character.toUpperCase(user.getName().charAt(0))+user.getName().substring(1));
        int imageId = getResourceId(this, user.getName(), "drawable", getPackageName());
        profilePic.setImageResource(imageId);
        progressCircle = findViewById(R.id.progressCircle);
        progressCircle.setProgress(user.getScore(),true);



    }



}
