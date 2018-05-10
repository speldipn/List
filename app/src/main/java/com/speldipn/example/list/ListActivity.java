package com.speldipn.example.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ListActivity extends AppCompatActivity {

  ListView listView;
  public static final String LIST_TITLE = "list_title";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);

    // 데이터 <> 아답터 <> 리스트뷰

    // 1. 리스트뷰와 코드 연결
    listView = findViewById(R.id.listView);

    // 2. 데이터 생성
    final String[] data = {
      "월", "화", "수", "목", "금", "토", "일",
      "월", "화", "수", "목", "금", "토", "일"
    };

    // 3. 데이터와 아답터 연결
    // - 아답터에서 리스트 요소 각각에 적용할 수 있는 레이아웃을 커스터마이징 해서 넣을 수 있다.
    // - 리스트뷰를 사용할때는 아답터 자체를 커스터마이징
    CustomAdapter customAdapter = new CustomAdapter(this, data);


    // 4. 아답터와 리스트뷰를 연결
    listView.setAdapter(customAdapter);

    // 5. 리스트뷰에 리스너 달기
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 1. 상세 페이지로 넘어가기 위해 Intent 생성
        Intent intent = new Intent(getBaseContext(), DetailActivity.class);

        // 2. 넘길 데이터를 세팅
        String selected = data[position];
        intent.putExtra(LIST_TITLE, selected);

        // 3. 액티비티 호출
        startActivity(intent);
      }
    });

  }
}

class CustomAdapter extends BaseAdapter {
  LayoutInflater inflater;
  String data[];

  public CustomAdapter(Context context, String[] data) { // 생성자를 통해 사용할 데이터를 받는다.
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.data = data;
  }

  // 화면에 표시될 요소의 총개수를 리턴한다.
  @Override
  public int getCount() {
    return data.length;
  }

  // 현재 포지션의 데이터를 리턴한다.
  @Override
  public Object getItem(int position) {
    return data[position];
  }

  // 현재 포지션을 리턴한다.
  @Override
  public long getItemId(int position) {
    return position;
  }

  // 현재 포지션에 완성된 뷰(데이터가 입력된 상태)를 리턴한다.
  @Override
  public View getView(int position, View storedView, ViewGroup parent) {
    // 레이아웃을 화면에 그리기 위해 LayoutInflator를 사용한다. 이건 context에서 꺼낼 수 있다.

    Holder holder = null;

    // 1. 리스트 아이템 레이아웃에 inflate 한다.
    // 추가 : 뷰의 재사용 (convertView -> storedView)
    if (storedView == null) {
      storedView = inflater.inflate(R.layout.list_item, null);
      // 2. 아이템 레이아웃 안에 있는 요소를 Holder와 연결.
      holder = new Holder();
      holder.textNo = storedView.findViewById(R.id.textNo);
      holder.textTitle = storedView.findViewById(R.id.textTitle);
      storedView.setTag(holder);
    } else {
      holder = (Holder) storedView.getTag();
    }

    // 3. 요소에 넣을 값을 가져온다.
    String title = data[position];
    String no = (position + 1) + "";

    // 4. 요소에 값을 넣는다.
    holder.textNo.setText(no);
    holder.textTitle.setText(title);

    return storedView;
  }
}

class Holder {
  TextView textNo;
  TextView textTitle;
}
