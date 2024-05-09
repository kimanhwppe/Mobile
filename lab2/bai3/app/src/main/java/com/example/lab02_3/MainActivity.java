package com.example.lab02_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvPerson;
    private ArrayList <String> pList;
    private ArrayAdapter<String> adapter;
    private TextView tvSelection;
    private Button btnInput;
    private EditText etInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pList = new ArrayList<String>();
        pList.add("Tèo"); pList.add("Tý"); pList.add("Bin"); pList.add("Bo");
        adapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, pList);

        etInput = findViewById(R.id.et_input);
        btnInput = findViewById(R.id.btn_input);
        btnInput.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (etInput.getText() != null)
                {
                    pList.add(etInput.getText().toString());
                }
                adapter.notifyDataSetChanged();
            }
        });

        lvPerson = (ListView) findViewById (R.id.lv_person);
        lvPerson.setAdapter(adapter);
        tvSelection = findViewById(R.id.tv_selection);

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvSelection.setText("position: " + i + "; value= " + pList.get(i));
            }
        };
        lvPerson.setOnItemClickListener(onItemClickListener);

        AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                pList.remove(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        };
        lvPerson.setOnItemLongClickListener(onItemLongClickListener);

    }
}