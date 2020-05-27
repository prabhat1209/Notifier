package com.example.notifier;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationCounter {
    private TextView notiNumber;
    private final int MAX_NUMBER = 99;
    private int notification_number_counter = 0;

    public NotificationCounter(View view){
        notiNumber = view.findViewById(R.id.notification_number);
    }
    public void increaseNumber(){
        notification_number_counter++;

        if(notification_number_counter > MAX_NUMBER){
            Log.i("Counter","Maximum Number Reached!");
            //Toast.makeText(getClass(),"Maximum",Toast.LENGTH_LONG).show();
        }else{
            Log.i("Counter",String.valueOf(notification_number_counter));
            notiNumber.setText(String.valueOf(notification_number_counter));
        }
    }

}
