package com.example.lab02_4;

public class ParttimeEmployee extends Employee{

    public ParttimeEmployee(){};

    @Override
    public double TinhLuong() {
        return 150;
    }

    @Override
    public String toString() {
        return super.toString() + " - PartTime=" + TinhLuong();
    }
}
