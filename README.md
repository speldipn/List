# List

## 1. Spinner
1. Layout에 Spinner를 추가
2. 코드에서 ArrayAdapter를 사용하여 UI와 연결
3. Spinner에 Listener를 연결

````java
String[] data = {"월", "화", "수", "목", "금", "토", "일"};

ArrayAdapter<String> adapter = new ArrayAdapter<>(this,  android.R.layout.simple_spinner_dropdown_item, data);
spinner.setAdapter(adapter);
spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    String selected = data[position];
    tv.setText(selected.toString());
  }
  @Override
  public void onNothingSelected(AdapterView<?> parent) {}
});
````

## 2. ListView(Legacy)
* Data - < Adapter > - ListView
1. Layout에 ListView 추가
2. BaseAdapter를 상속받아 CustomAdapter 클래스를 정의
3. ListView에 CustomAdapter를 연결
4. ListView에 Listener를 연결

### CustomAdapter
* LayoutInflater와 리스트에 표시할 데이터를 갖고있어야한다
````java
LayoutInflater inflater;
String data[];

public CustomAdapter(Context context, String[] data) { // 생성자를 통해 사용할 데이터를 받는다.
  inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  this.data = data;
}
````

* 리사이클러뷰와 달리 ListView에서 사용될 별도의 Holder를 정의해줘야한다
````java
class Holder {
  TextView textNo;
  TextView textTitle;
}
````

* 필수 Override함수를 정의해야한다
````java
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
  // storeView가 null인지를 판단하여 holder를 생성
  if(holder == null) {
    holder = new Holder();
    holder.textNo = storedView.findViewById(R.id.textNo);
    holder.textTitle = storedView.findViewById(R.id.textTitle);
    storedView.setTag(holder); // Tag함수를 사용하여 Holder를 View에 달아준다
  } else {
    // Tag함수를 사용하여 View에 달아준 Holder를 가져온다
    holder = (Holder) storedView.getTag();
  }  
  // 이하 생략
  return storeView;
}
````

* ListView에 Adapter를 연결해주고 Listener를 연결한다
````java
listView.setAdapter(customAdapter);
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    // 뷰가 선택되었을때 호출되는 콜백함수. 해당뷰의 position정보가 전달된다
  }
}
````

