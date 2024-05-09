package com.example.myapplication;

import androidx.annotation.NonNull;

public class SinhVien {
    private int mssv;
    private String name;
    private String phone;
    private String lop;

    public SinhVien() {
    }

    public SinhVien(int mssv, String name, String phone, String lop) {
        this.mssv = mssv;
        this.name = name;
        this.phone = phone;
        this.lop = lop;
    }

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    @NonNull
    @Override
    public String toString() {
        return "MSSV: " + this.mssv + " - Tên: " + this.name + " - SĐT: " + this.phone + " - Lớp: " + this.lop;
    }
}
