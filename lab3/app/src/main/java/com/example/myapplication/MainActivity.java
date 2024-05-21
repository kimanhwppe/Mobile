package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvSinhVien;
    private ArrayList<SinhVien> SinhVienList;
    private ArrayAdapter<SinhVien> adapter;
    private Button btnAdd, btn_delete;
    private Button btnEdit;
    private EditText etId;
    private EditText etName;
    private EditText etPhone;
    private EditText etClass;
    private SinhVien NewSinhVien;
    private DatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();
        dbAdapter.deleteAllUsers();

        SinhVienList = new ArrayList<SinhVien>();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,SinhVienList);

        lvSinhVien = findViewById(R.id.lv_employee);

        etId = findViewById(R.id.et_mssv);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etClass = findViewById(R.id.et_class);
        btnAdd = findViewById(R.id.btn_add);
        btnEdit = findViewById(R.id.btn_edit);
        btn_delete = findViewById(R.id.btn_delete);

        lvSinhVien.setAdapter(adapter);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        rvSinhVien.setLayoutManager(linearLayoutManager);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (etId.getText() != null && etName.getText() != null && etPhone.getText() != null && etClass.getText()!= null)
                {
                    int id = Integer.parseInt(etId.getText().toString());
                    String name = etName.getText().toString();
                    String phone = etPhone.getText().toString();
                    String lop = etClass.getText().toString();
                    //SinhVien newSV = new SinhVien(id,name,phone,lop);
                    dbAdapter.addSV(id,name,phone,lop);
                    SinhVienList.add(new SinhVien(id,name,phone,lop));
                    adapter.notifyDataSetChanged();
                    showDataBase(dbAdapter);

                    etId.setText(null);
                    etName.setText(null);
                    etPhone.setText(null);
                    etClass.setText(null);
                }
            }

        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (etId.getText() != null && etName.getText() != null && etPhone.getText() != null && etClass.getText()!= null)
                {
                    int id = Integer.parseInt(etId.getText().toString());
                    String name = etName.getText().toString();
                    String phone = etPhone.getText().toString();
                    String lop = etClass.getText().toString();

                    SinhVien newsv = new SinhVien(id,name,phone,lop);
                    //SinhVien newSV = new SinhVien(id,name,phone,lop);
                    dbAdapter.deleteSV(newsv);
//                    SinhVienList.add(new SinhVien(id,name,phone,lop));
                    SinhVienList.remove(newsv);
                    adapter.notifyDataSetChanged();
//                    showDataBase(dbAdapter);

                    etId.setText(null);
                    etName.setText(null);
                    etPhone.setText(null);
                    etClass.setText(null);
                }
            }

        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etId.getText() != null && etName.getText() != null && etPhone.getText() != null && etClass.getText()!= null)
                {
                    int id = Integer.parseInt(etId.getText().toString());
                    String name = etName.getText().toString();
                    String phone = etPhone.getText().toString();
                    String lop = etClass.getText().toString();
                    SinhVien newsv = new SinhVien(id,name,phone,lop);
                    Log.e("Sinh vien:", Integer.toString(id));
                    Log.e("Sinh vien:", SinhVienList.toString());

                    for (SinhVien sv : SinhVienList)
                    {
                        Log.e("Sinh vien:", Integer.toString(sv.getMssv()) + "==" + etId.getText().toString());

                        if (sv.getMssv() == id){
                            dbAdapter.updateSV(newsv);
                            int index = SinhVienList.indexOf(sv);
                            SinhVienList.set(index,newsv);
                            Log.e("Sinh vien:", SinhVienList.toString());
                            adapter.notifyDataSetChanged();
                            break;
                        }
                    }
                    showDataBase(dbAdapter);

                    etId.setText(null);
                    etName.setText(null);
                    etPhone.setText(null);
                    etClass.setText(null);
                }
            }
        });

//        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        };
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        SinhVien sv = SinhVienList.get(i);
                        etId.setText(Integer.toString(sv.getMssv()));
                        etName.setText(sv.getName());
                        etPhone.setText(sv.getPhone());
                        etClass.setText(sv.getLop());
                    }
                });
            }
        });

//        lvSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                int mssv = SinhVienList.get(i).getMssv();
//                dbAdapter.deleteSV(mssv);
//                SinhVienList.remove(i);
//                adapter.notifyDataSetChanged();
//                showDataBase(dbAdapter);
//                return true;
//            }
//        });





    }

    public void showDataBase(DatabaseAdapter dbAdapter){
        Log.e("Reading: ", "Reading all Sinh vien");
        ArrayList<SinhVien> svList = dbAdapter.selectAllSV();

        for (SinhVien sv : svList) {
            String log = "MSSV: "+sv.getMssv()+", Name: " + sv.getName() + ", Phone: " + sv.getPhone() + ", Class: " + sv.getLop();
            // Writing Contacts to log
            Log.e("Sinh vien", log);
        }
    }
}
