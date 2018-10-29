package com.almahery.multiscreenappsmsptask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Num_btn(View view) {
        Intent intent = new Intent(MainActivity.this,NumbersActivity.class);
        startActivity(intent);

    }

    public void Fam_btn(View view) {
        Intent intent = new Intent(MainActivity.this,FamilyActivity.class);
        startActivity(intent);

    }

    public void Color_btn(View view) {
        Intent intent = new Intent(MainActivity.this,ColorsActivity.class);
        startActivity(intent);

    }

    public void Phar_btn(View view) {
        Intent intent = new Intent(MainActivity.this,PhrasesActivity.class);
        startActivity(intent);

    }
}
