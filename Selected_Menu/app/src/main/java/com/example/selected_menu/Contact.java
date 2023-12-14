package com.example.selected_menu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Contact {
    private  int Id;
    private  String Name;
    private  String PhoneNumber;
    //private boolean status;
    private  String Images;


//    public Contact(int id, String name, String phoneNumber, boolean status) {
//        Id = id;
//        Name = name;
//        PhoneNumber = phoneNumber;
//        this.status = status;
//    }

    public Contact(int id,  String images, String name, String phoneNumber) {
        Id = id;
        Name = name;
        PhoneNumber = phoneNumber;
        Images = images;
    }
    public final int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public  String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public  String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }

    public void setImages(String images) {
        this.Images = images;
    }
    public  String getImages() {
        return Images;
    }
}
