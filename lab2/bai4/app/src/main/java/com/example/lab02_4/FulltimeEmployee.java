package com.example.lab02_4;

public class FulltimeEmployee extends Employee{

    public FulltimeEmployee(){};

    @Override
    public double TinhLuong() {
        return 500;
    }

    @Override
    public String toString() {
        return super.toString() + " - FullTime=" + TinhLuong();
    }
}
