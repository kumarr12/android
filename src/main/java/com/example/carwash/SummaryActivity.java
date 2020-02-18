package com.example.carwash;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView txt = findViewById(R.id.txtViewSummary);

        String outString = "Conversion Summary\n";
        txt.setTextSize(Dimension.SP,24);

        if(getIntent() != null){
            Bundle myBundle = getIntent().getExtras();
            outString += String.format("Input Weight = %2f %s\n",
                                        myBundle.getDouble("INPUTWT"));
        }

        txt.setText(outString);
        setContentView(txt);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
