package com.example.recycleviewexample;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {

    RecyclerView rcView;
    ArrayList<ModelJava> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_main);
        rcView = findViewById(R.id.recycleView);

        list = ModelJava.generateRandomElements(30);

        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setAdapter(new ModelJavaAdaptor(list));

    }
}
