package com.example.sumeetharyani.sharedpeferencesdemo;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private String sharedPrefFile = "com.example.sumeetharyani.sharedpeferencesdemo.MainActivity";
    private TextView countTextView;
    private int mCount = 0;
    private int mColor;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button countButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        countTextView = findViewById(R.id.count_textview);
        mCount = Integer.valueOf((String) countTextView.getText());
        mColor = ContextCompat.getColor(this, R.color.default_background);
        button1 = findViewById(R.id.black_background_button);
        button2 = findViewById(R.id.red_background_button);
        button3 = findViewById(R.id.blue_background_button);
        button4 = findViewById(R.id.green_background_button);
        countButton = findViewById(R.id.count_button);
        resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCount = 0;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                countTextView.setText(String.valueOf(0));
                countTextView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.default_background));
                editor.clear();
                editor.apply();
            }
        });
        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCount++;
                countTextView.setText(String.valueOf(mCount));
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackground(view);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackground(view);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackground(view);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackground(view);
            }
        });
        mCount = sharedPreferences.getInt("count", 0);
        mColor = sharedPreferences.getInt("colour", ContextCompat.getColor(this, R.color.default_background));
        countTextView.setText(String.valueOf(mCount));
        countTextView.setBackgroundColor(mColor);
        SharedPreferences.OnSharedPreferenceChangeListener listener =
                new SharedPreferences.OnSharedPreferenceChangeListener() {
                    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                        Toast.makeText(MainActivity.this, "CHANGES MADE", Toast.LENGTH_SHORT).show();
                    }
                };
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("count", mCount);
        editor.putInt("colour", mColor);
        editor.apply();
    }

    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        countTextView.setBackgroundColor(color);
        mColor = color;
    }

}
