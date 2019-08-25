package com.amanciodrp.yellotaxi;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;

import com.amanciodrp.yellotaxi.customeractivity.CustomerLoginActivity;
import com.amanciodrp.yellotaxi.driveractivity.DriverLoginActivity;

public class MainActivity extends AppCompatActivity {
    private CardView mDriver, mCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDriver = findViewById(R.id.driver);
        mCustomer = findViewById(R.id.customer);

        startService(new Intent(MainActivity.this, OnAppKilled.class));

        mDriver.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DriverLoginActivity.class);
            startActivity(intent);
            finish();
        });

        mCustomer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CustomerLoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
