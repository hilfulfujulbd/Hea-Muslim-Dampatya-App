package com.shahrinsiddeka.heamoslimdampatya;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.shahrinsiddeka.heamoslimdampatya.MainAdapter.ChapterClick;
import com.shahrinsiddeka.heamoslimdampatya.MainAdapter.ChapterModel;
import com.shahrinsiddeka.heamoslimdampatya.MainAdapter.RecyclerAdapterView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ChapterClick, NavigationView.OnNavigationItemSelectedListener {

    RecyclerAdapterView recyclerAdapterView;
    RecyclerView recyclerView;

    ArrayList<ChapterModel> chapterModels;

    DrawerLayout drawerLayoutNavigation;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Toggle Menu
        drawerLayoutNavigation = findViewById(R.id.drawerLayoutMain);

        materialToolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_menu_view);

        setSupportActionBar(materialToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);

        // Drawable Navigation Show Toggle
        toggle = new ActionBarDrawerToggle(this, drawerLayoutNavigation, materialToolbar, R.string.openNavigation, R.string.closeNavigation);
        drawerLayoutNavigation.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.google_justify_icon);

        navigationView.setNavigationItemSelectedListener(this);


        showStartUp();

        chapterModels = new ArrayList<>();
        chapterModels.add(new ChapterModel("স্বামী স্ত্রীর সম্পর্ক", "1"));
        chapterModels.add(new ChapterModel("মহব্বাত", "2"));
        chapterModels.add(new ChapterModel("পরিবারের পর্দা", "3"));
        chapterModels.add(new ChapterModel("দায়িত্ব", "4"));
        chapterModels.add(new ChapterModel("সংসার জীবন", "5"));
        chapterModels.add(new ChapterModel("বৈশিষ্ট্য অনুযায়ী দাম্পত্য", "6"));
        chapterModels.add(new ChapterModel("আদর্শ দাম্পত্য", "7"));
        chapterModels.add(new ChapterModel("আদর্শ মা- বাবা হোন", "8"));
        chapterModels.add(new ChapterModel("সন্তান সন্ততি", "9"));
        chapterModels.add(new ChapterModel("বিবাহ বিচ্ছেদ", "10"));
        chapterModels.add(new ChapterModel("পুরুষের বহু বিবাহ", "11"));
        chapterModels.add(new ChapterModel("এক নজরে", "12"));

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set a layout manager (e.g., LinearLayoutManager)

        // Create and set Adapter
        recyclerAdapterView = new RecyclerAdapterView(chapterModels, this);
        recyclerView.setAdapter(recyclerAdapterView);

    }

    @Override
    public void onClick(String file, String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("file", file);
        Intent intent = new Intent(this, ReadChapter.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void showStartUp() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.salam);
        dialog.setCancelable(true);

        ImageView close = dialog.findViewById(R.id.salamImage);
        close.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_menu) {
            if (drawerLayoutNavigation.isDrawerOpen(GravityCompat.START)) {
                drawerLayoutNavigation.closeDrawer(GravityCompat.START);
            }
            return true;
        } else if (item.getItemId() == R.id.about_writer) {
            Bundle bundle = new Bundle();
            bundle.putString("title", "লেখকের পরিচয়");
            bundle.putString("file", "0");
            Intent intent = new Intent(this, ReadChapter.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (item.getItemId() == R.id.about_info) {
            Bundle bundle = new Bundle();
            bundle.putString("title", "লেখকের কথা");
            bundle.putString("file", "110");
            Intent intent = new Intent(this, ReadChapter.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}