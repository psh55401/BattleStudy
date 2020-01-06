package com.example.myapplication5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class show_stopwhatch_background extends AppCompatActivity {
    Button btn_start1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stopwhatch_background);

        btn_start1= findViewById(R.id.btn_start1);

        btn_start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(show_stopwhatch_background.this,stop_watch.class);
                startActivity(intent);
            }
        });
    }
}
