package com.example.listview_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String arr[] = new String[]{
            "Iron Man",
            "Spider Man",
            "Doctor Strange",
            "Wanda Vision",
            "Captian Marvel",
            "Caption America",
            "Black Pather",
            "Star Lord",
            "Gamora",
            "Thor",
            "Hulk",
            "Loki",
            "Black Widow",
            "Ant Man",
            "Wasp",
            "Falcon",
            "Winter Soilder"
    };

    POJOModal list[] = POJOModal.generate10Model().toArray(new POJOModal[0]);

    ListView listVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listVw = findViewById(R.id.listView);

        // Using Default Adaptor
//        ArrayAdapter adptr = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1,
//                arr
//        );
//        listVw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                          @Override
//                                          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                              Toast.makeText(MainActivity.this, String.format("%s %s", position, arr[position]), Toast.LENGTH_SHORT).show();
//                                          }
//                                      }
//        );
//        listVw.setAdapter(adptr);


        //Creating Custom Adaptor
        listVw.setAdapter(new CourseAdaptor());
    }

    class CourseAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public POJOModal getItem(int position) {
            return list[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View viewItem = getLayoutInflater().inflate(
                    R.layout.list_of_courses,
                    parent,
                    false
            );

            TextView name = viewItem.findViewById(R.id.name);
            TextView course = viewItem.findViewById(R.id.course);

            name.setText(getItem(position).getName());
            course.setText(getItem(position).getCourse());

            return  viewItem;
        }
    }
}