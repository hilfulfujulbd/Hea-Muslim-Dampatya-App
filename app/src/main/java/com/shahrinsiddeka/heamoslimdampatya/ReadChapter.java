package com.shahrinsiddeka.heamoslimdampatya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadChapter extends AppCompatActivity {
    TextView ReadTextView;
    ImageView imageView;
    MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_chapter);

        materialToolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.headerChapter);

        setSupportActionBar(materialToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.google_home_icon);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String file = intent.getStringExtra("file");

        materialToolbar.setTitle(title);

        ReadTextView = findViewById(R.id.textView);

        fileReadAsync(file);
    }

    public void fileReadAsync(final String file) {
        final ProgressDialog progressDialog = new ProgressDialog(this); // Assuming 'this' is the context
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> {
            String text = "";
            try {
                InputStream inputStream = getAssets().open(file + ".txt");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                final int read = inputStream.read(buffer);
                inputStream.close();
                text = new String(buffer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            final String finalText = text;
            handler.postDelayed(() -> {
                ReadTextView.setText(finalText);
                handler.postDelayed(progressDialog::dismiss, 2000);
            }, 2000);
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}