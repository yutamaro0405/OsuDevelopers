package com.osudevelopers.seatallocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class CarSelectionActivity extends AppCompatActivity implements Serializable{
    public final static String EXTRA_LISTCAR = "com.osudevelopers.seatallocation.LISTCAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("台数の選択");
        setSupportActionBar(toolbar);


    }

    public void goCarPeoples(View view) throws CarException{
        ArrayList<CarCar> listCar = new ArrayList<CarCar>();
        Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
        int num5 = Integer.parseInt((String)spinner5.getSelectedItem());
        for(int i=0; i < num5; i++){
            listCar.add(new Car_Sedan5());
        }
        Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        int num7 = Integer.parseInt((String)spinner7.getSelectedItem());
        for(int i=0; i < num7; i++) {
            listCar.add(new Car_Wish7());
        }

        //車が無い場合
        if (listCar.size() == 0) {
            Toast.makeText(this, this.getString(R.string.error_noCarZero), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, CarPeoplesActivity.class);
        intent.putExtra(EXTRA_LISTCAR, listCar);
        startActivity(intent);
    }

}
