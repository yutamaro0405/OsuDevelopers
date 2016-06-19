package com.osudevelopers.seatallocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class CarPeoplesActivity extends AppCompatActivity{

    ArrayList<CarCar> listCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_peoples);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        listCar = (ArrayList<CarCar>) intent.getSerializableExtra(CarSelectionActivity.EXTRA_LISTCAR);

    }

    // 「APPLY Button」押下時に「The number of people EditText」に
    // 入力されている数分だけ、メンバー入力行を追加
    public void applyNumber(View view) {

        TextView number = (TextView) findViewById(R.id.number);
        int num=-1;
        try {
            num = Integer.parseInt(number.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(this, "人数が入力されていません", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!(0<=num && num<100)){
            Toast.makeText(this, "人数が多すぎるため処理できません", Toast.LENGTH_SHORT).show();
            return;
        }

        int maxpassengers = 0;
        for (CarCar car : listCar) {
            maxpassengers = maxpassengers + car.getLoadPeople();
        }

        if (num>maxpassengers){
            Toast.makeText(this, this.getString(R.string.error_peopleCantRide), Toast.LENGTH_SHORT).show();
            return;
        }

        // TableLayoutのグループを取得
        ViewGroup vg = (ViewGroup) findViewById(R.id.TableLayout);

        // 表示済のメンバー入力行を全削除
        vg.removeAllViews();

        // 「The number of people EditText」に入力した数値分、メンバー入力行を追加
        for (int i = 0; i < num; i++) {
            // 行を追加
            getLayoutInflater().inflate(R.layout.table_row, vg);

            // 追加した行に存在する部品の文字設定
            TableRow tr = (TableRow) vg.getChildAt(i);
            String str = String.format(Locale.getDefault(), "Member %d", i + 1);
            ((EditText)(tr.getChildAt(0))).setText(str);

        }
    }


    public void seatAllocate(View view){

        //いろいろ詰めて次の画面に渡す
        ArrayList<CarCar> result=null;
        ArrayList<CarPeople> peoples=new ArrayList<>();
        try {
            // TableLayoutのグループを取得
            ViewGroup vg = (ViewGroup) findViewById(R.id.TableLayout);
            //行ごとに人を登録する
            for(int i=0;i<vg.getChildCount();i++){
                TableRow row = (TableRow)vg.getChildAt(i);
                EditText name = (EditText)(row.getChildAt(0));
                CheckBox isDriver=(CheckBox)(row.getChildAt(1));
                peoples.add(new CarPeople(name.getText().toString(), isDriver.isChecked()));
            }
            //ロジック実行
            result=CarAllocationLogic.exec(this, listCar, peoples);
        }catch(CarException carException){
            //エラー通知して次画面にいかない
            Toast.makeText(this, "エラー："+carException.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        //結果を次画面に渡す
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(CarSelectionActivity.EXTRA_LISTCAR, result);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_peoples, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
