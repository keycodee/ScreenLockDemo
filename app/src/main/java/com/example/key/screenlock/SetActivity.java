package com.example.key.screenlock;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class SetActivity extends AppCompatActivity {

    List<Integer> passList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        final GestureLock lock = (GestureLock) findViewById(R.id.view);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        lock.setOnDrawFinishedListener(new GestureLock.OnDrawFinishedListener() {
            @Override
            public boolean OnDrawFinished(List<Integer> passList) {
                if (passList.size() < 3) {
                    Toast.makeText(SetActivity.this, "密码不能小于3个点", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    SetActivity.this.passList = passList;
                    return true;

                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lock.resetPoints();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passList != null) {
                    StringBuilder sb = new StringBuilder();
                    for (Integer i : passList) {
                        sb.append(i);
                    }
                    SharedPreferences sp = SetActivity.this.getSharedPreferences("password", SetActivity.this.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("password", sb.toString());
                    editor.commit();

                    Toast.makeText(SetActivity.this, "保存完成", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set, menu);
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
