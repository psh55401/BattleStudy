package com.example.myapplication5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class LodingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            try{
                Thread.sleep(4000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(this,MainActivity.class));
            finish();
    }
}
