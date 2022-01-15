package com.example.dullard;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOnlyToolbar = this.findViewById(R.id.onlytoolbar);
        Button btnCollapsingLayout = this.findViewById(R.id.callap);
        Button btnPicCollapsing = this.findViewById(R.id.piccollapsing);

        startService(new Intent(this, ProvideService.class));

        btnOnlyToolbar.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,ToolbarActivity.class);
            startActivity(intent);
        });

        btnCollapsingLayout.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,collapsingActivity.class);
            startActivity(intent);
        });

        btnPicCollapsing.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,PicCollapsingActivity.class);
            startActivity(intent);
        });
    }

}