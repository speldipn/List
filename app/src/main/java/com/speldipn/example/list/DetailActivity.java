package com.speldipn.example.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

  public static final String LIST_TITLE = "list_title";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    Intent intent = getIntent();
    String title = intent.getStringExtra(LIST_TITLE);

    TextView tv = findViewById(R.id.textTitle);
    tv.setText(title);
  }
}
