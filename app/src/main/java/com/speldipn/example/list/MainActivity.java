package com.speldipn.example.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  TextView tv;
  Spinner spinner;
  String[] data = {"월", "화", "수", "목", "금", "토", "일"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    tv = findViewById(R.id.textView);
    spinner = findViewById(R.id.spinner);

    // Spinnner와 같은 리스트 형태의 뷰를 사용하는 루틴
    // 1. 화면과 연결.
    // 2. 리스트에 입력될 데이터를 정의


    // 3. adaptor 를 정의 < 데이터
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this,  android.R.layout.simple_spinner_dropdown_item, data);

    // 4. spinner(리스트)에 adapter를 연결
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = data[position];
        tv.setText(selected.toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

  }
}
