package com.treasurehunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RoundProgressActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        ScanActivity.getBarcodeResult();
    }
}
