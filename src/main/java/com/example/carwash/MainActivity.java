package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final private double ONE_POUND = 0.453592;
    final private double ONE_KILO =  2.20462;

    EditText editNumber;
    RadioGroup rdgrp;
    TextView txtRes;





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber = findViewById(R.id.editNumTxt);
        rdgrp = findViewById(R.id.radioGroup);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_weight);
        getSupportActionBar().setDisplayUseLogoEnabled(true);




        txtRes = findViewById(R.id.txtResult);
        Button btnConvert = findViewById(R.id.btnGetResult);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComputeWeight();
            }
        });

        rdgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ComputeWeight();
            }
        });
    }

    protected void ComputeWeight(){
        txtRes.setText("");
        if(editNumber.getText().toString().equals("")){
            Toast.makeText(this, "Input weight cannot be empty", Toast.LENGTH_SHORT).show();
        } else if(rdgrp.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please select the convertion", Toast.LENGTH_SHORT).show();
        }   else{
            try{
                double inp = Double.parseDouble(editNumber.getText().toString());
                double ans =0;
                String inputUnit="", outputUnit ="";
                if(rdgrp.getCheckedRadioButtonId() == R.id.rdbtnPtoK
                     && inp >1000 ){
                    Toast.makeText(this, "Weight in Pounds exceeded limit", Toast.LENGTH_SHORT).show();
                }   else if(rdgrp.getCheckedRadioButtonId() == R.id.rdbtnPtoK){
                    ans = inp/2.2;
                    txtRes.setText(String.format("Weight = %.2f Kgs", ans));
                    inputUnit="Lbs";
                    outputUnit ="Kgs";

                    Intent summaryIntent =new Intent(MainActivity.this, SummaryActivity.class);
                    Bundle myBundle = new Bundle();
                    myBundle.putDouble("INPUTWT", inp);
                    myBundle.putString("INPUTUT", inputUnit);
                    myBundle.putDouble("OUTPUTWT", ans);
                    myBundle.putString("OUTPUTUT", outputUnit);
                    summaryIntent.putExtras(myBundle);

                    startActivity(summaryIntent);

                }   else if(rdgrp.getCheckedRadioButtonId() == R.id.rdbtnKtoP
                        && inp > 500 ){
                    Toast.makeText(this, "Weight in Pounds exceeded limit", Toast.LENGTH_SHORT).show();
                }   else if(rdgrp.getCheckedRadioButtonId() == R.id.rdbtnKtoP){
                    ans = inp * 2.2;
                    txtRes.setText(String.format("Weight = %.2f Lbs", ans));
                    inputUnit="Kgs";
                    outputUnit ="Lbs";
                }

            }   catch(Exception ex){
                Toast.makeText(this, "input weight is not in correct format", Toast.LENGTH_SHORT).show();
                Log.w("LEC4", ex.getMessage());
            }
        }


    }


}
