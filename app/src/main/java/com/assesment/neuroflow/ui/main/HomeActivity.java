package com.assesment.neuroflow.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.assesment.neuroflow.R;
import com.assesment.neuroflow.data.User;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class HomeActivity extends AppCompatActivity {

    User user;
    List<User> usersList = new ArrayList<>();

    Button btnToMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadData();

        btnToMain = findViewById(R.id.btnHome);
        btnToMain.setOnClickListener( (View v) ->{
            Random random = new Random();
            int randomIndex = random.nextInt(usersList.size());
            user = usersList.get(randomIndex);
            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.putExtra("user", userJson);
            startActivity(intent);
        });

    }

    private void loadData(){
        String date;
        String datePattern = "dd-MM-yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);

        date = simpleDateFormat.format(new Date((long) Long.valueOf("1546341851000") * 1000));
        user = new User("Ryan", 90, date);
        int imageId = getResourceId(this, "ryan", "drawable", getPackageName());
        user.setImage(imageId);
        usersList.add(user);

        date = simpleDateFormat.format(new Date((long) Long.valueOf("1540341851000") * 1000));
        user = new User("Melissa", 90, date);
        imageId = getResourceId(this, "mels", "drawable", getPackageName());
        user.setImage(imageId);
        usersList.add(user);

        date = simpleDateFormat.format(new Date((long) Long.valueOf("1540341651000") * 1000));
        user = new User("Ryan", 85, date);
        imageId = getResourceId(this, "ryan", "drawable", getPackageName());
        user.setImage(imageId);
        usersList.add(user);

        date = simpleDateFormat.format(new Date((long) Long.valueOf("1536442851000") * 1000));
        user = new User("Sam", 98, date);
        imageId = getResourceId(this, "sam", "drawable", getPackageName());
        user.setImage(imageId);
        usersList.add(user);
    }

    public static int getResourceId(Context context, String varName, String resourceName, String packageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(varName, resourceName, packageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }
}
