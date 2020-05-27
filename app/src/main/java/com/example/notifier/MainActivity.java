package com.example.notifier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notifier.clicked.CheckNotification;
import com.example.notifier.data.MyDbHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    Button btn;
    NotificationCounter notificationCounter;
    ConstraintLayout constraintLayout;
    TextInputEditText not_title, not_desc;
    LinearLayout lay;
    TextView count;

    int total =0, read=0;
    MyDbHelper db;
    CheckNotification cdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.not_button);
        not_title = findViewById(R.id.titleForNotification);
        not_desc = findViewById(R.id.describe);

        notificationCounter  = new NotificationCounter(findViewById(R.id.bell));
        View noti_page = findViewById(R.id.bell);
        count = findViewById(R.id.notification_number);
        lay = findViewById(R.id.notificationNumberContainer);
        constraintLayout = findViewById(R.id.parent);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.notification);

        db = new MyDbHelper(getApplicationContext());
        cdb = new CheckNotification(getApplicationContext());
        getNotification();

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyBoard(v);
                //getNotification();
            }
        });


        noti_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),NotificationActivity.class);
                startActivity(it);
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = not_title.getText().toString();
                String description = not_desc.getText().toString();
                if(title.equals("")){
                    Toast.makeText(getApplicationContext(),"Title is Required!",Toast.LENGTH_SHORT).show();
                }else
                if(description.equals("")){
                    Toast.makeText(getApplicationContext(),"Plz give the description!",Toast.LENGTH_SHORT).show();
                }else{
                    notificationCounter.increaseNumber();
                    Notification not = new Notification(title,description);
                    db.add(not);
                    mediaPlayer.start();
                    not_title.setText("");
                    not_desc.setText("");
                }
                getNotification();
            }
        });

    }

    public void getNotification(){
        read = cdb.getCount();
        total = db.getCount();

        final int no_of_notification = total - read;
        if(no_of_notification>0){
            lay.setVisibility(View.VISIBLE);
            count.setText(String.valueOf(no_of_notification));
        }else{
            lay.setVisibility(View.INVISIBLE);
        }
    }

    private void closeKeyBoard(View view){
        view = this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
