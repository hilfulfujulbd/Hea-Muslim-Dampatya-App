package com.shahrinsiddeka.heamoslimdampatya;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class WelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel);


        Thread thread = new Thread(this::SkipApplication);
        thread.start();
    }

    public void SkipApplication() {
        int progress;
        for (progress = 0; progress <= 100; progress = progress + 1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}