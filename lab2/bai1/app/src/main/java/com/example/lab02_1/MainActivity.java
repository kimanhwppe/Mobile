package com.example.lab02_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private ListView lvPerson;
    private final String pList[] = {"Tèo","Tý","Bin","Bo"};
    private ArrayAdapter<String> adapter;
    private TextView tvSelection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPerson = (ListView) findViewById (R.id.lv_person);
        adapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, pList);
        lvPerson.setAdapter(adapter);
        tvSelection = findViewById(R.id.tv_selection);

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvSelection.setText("position: " + i + "; value= " + pList[i]);
            }
        };
        lvPerson.setOnItemClickListener(onItemClickListener);
    }
}