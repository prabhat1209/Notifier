package com.example.notifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowNotification extends AppCompatActivity {

    ImageView back;
    TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notification);
        back = findViewById(R.id.back_recView);
        title = findViewById(R.id.title_show);
        description = findViewById(R.id.show_desc);

        Intent it = getIntent();

        String name = it.getStringExtra("Rname");
        String dname = it.getStringExtra("Rdesc");

        title.setText(name);
        description.setText(dname);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NotificationActivity.class));
                finish();
            }
        });
    }
}
