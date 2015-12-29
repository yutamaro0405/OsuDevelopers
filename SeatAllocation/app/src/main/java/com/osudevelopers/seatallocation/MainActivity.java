package com.osudevelopers.seatallocation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    // 「APPLY Button」押下時に「The number of people EditText」に
    // 入力されている数分だけ、メンバー入力行を追加
    public void applyNumber(View view) {

        TextView number = (TextView) findViewById(R.id.number);
        int num = Integer.parseInt(number.getText().toString());

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
