package com.assesment.neuroflow.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.assesment.neuroflow.R;
import com.assesment.neuroflow.data.User;
import com.vaibhavlakhera.circularprogressview.CircularProgressView;


import java.text.SimpleDateFormat;
import java.util.Date;



public class MainActivity extends AppCompatActivity {

    private User user;
    private TextView timeStamp;
    private TextView name;
    private CircularProgressView progressCircle;
    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();





    }

    private void setup() {

        String datePattern = "dd-MM-yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        String today = simpleDateFormat.format(new Date());

        user = new User("Ryan", 50, today);




        backArrow = findViewById(R.id.iv_BackArrow);
        backArrow.setOnClickListener((View v) -> {

                finish();

        });

        timeStamp = findViewById(R.id.tvTimeStamp);
        name = findViewById(R.id.tvName);
        timeStamp.setText(user.getTimestamp());
        name.setText(user.getName());

        progressCircle = findViewById(R.id.progressCircle);
        progressCircle.setProgress(user.getScore(),true);



    }
}
