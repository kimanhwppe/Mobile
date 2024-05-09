package com.example.lab02_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvEmployee;
    private ArrayList<Employee> EmployeeList;
    private EmployeeAdapter adapter;
    private TextView tvSelection;
    private Button btnAdd;
    private EditText etId;
    private EditText etName;
    private Employee NewEmployee;
    private CheckBox cbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmployeeList = new ArrayList<Employee>();
        adapter = new EmployeeAdapter (this, android.R.layout.simple_list_item_1, EmployeeList);

        etId = findViewById(R.id.et_id);
        etName = findViewById(R.id.et_name);
        btnAdd = findViewById(R.id.btn_input);
        cbManager = findViewById(R.id.cb_manager);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (etId.getText() != null && etName.getText() != null )
                {
                    NewEmployee = new Employee();
                    String id = etId.getText().toString();
                    String name = etName.getText().toString();
                    NewEmployee.setId(id);
                    NewEmployee.setName(name);
                    NewEmployee.setManager(cbManager.isChecked());
                    //FullTime hay Partime thì cũng là NewEmployee nên có các hàm này là hiển nhiên

                    //Đưa NewEmployee vào ArrayList
                    EmployeeList.add(NewEmployee);
                    //Cập nhập giao diện
                    adapter.notifyDataSetChanged();
                }
            }
        });
        lvEmployee = (ListView) findViewById (R.id.lv_employee);
        lvEmployee.setAdapter(adapter);
    }
}