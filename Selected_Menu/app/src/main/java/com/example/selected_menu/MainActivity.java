package com.example.selected_menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper myDB;
    //widgets
    private EditText idEdit, nameEdit, emailEdit;

    private Button addButton, deleteButton, showButton, showAllButton, deleteAllButton, updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(MainActivity.this);

        idEdit = findViewById(R.id.idid);
        nameEdit = findViewById(R.id.name);
        emailEdit = findViewById(R.id.email);

        addButton = findViewById(R.id.addbtn);
        deleteAllButton = findViewById(R.id.deleteallbtn);
        deleteButton = findViewById(R.id.deletebtn);
        showAllButton = findViewById(R.id.showallbtn);
        showButton = findViewById(R.id.showbtn);
        updateButton = findViewById(R.id.updatebtn);

        addData();
        showData();
        updateData();
        deleteData();
        getAllData();
        deleteAllData();

    }
    public void addData(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(nameEdit.getText().toString() , emailEdit.getText().toString());

                if (isInserted){
                    Toast.makeText(MainActivity.this, "Data Inserted...", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void showData(){
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = idEdit.getText().toString();
                if(id.equals(String.valueOf(""))){
                    idEdit.setError("Please Enter Id");
                    return;
                }
                Cursor cursor = myDB.getData(id);
                String data = null;

                if (cursor.moveToNext()){
                    data = "ID : " + cursor.getString(0) + "\n" +
                            "NAME : " + cursor.getString(1) + "\n" +
                            "EMAIL : " + cursor.getString(2) + "\n" ;
                    showMessage("DATA" , data);
                }else{
                    showMessage("DATA" , "There is no Data");
                }



            }
        });
    }

    public void updateData(){
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdated = myDB.updateData(idEdit.getText().toString() , nameEdit.getText().toString() , emailEdit.getText().toString());
                if(isUpdated){
                    Toast.makeText(MainActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteData(){
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEdit.getText().toString();
                if(id.equals(String.valueOf(""))){
                    idEdit.setError("Please Enter Id");
                    return;
                }
                Integer var = myDB.deleteData(id);

                if(var > 0){
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "Deletion Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAllData(){
        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = myDB.getAllData();
                StringBuffer buffer = new StringBuffer();
                if(cursor.getCount() == 0){
                    showMessage("Data" , "Nothing found");
                    return;
                }
                while (cursor.moveToNext()){
                    buffer.append("ID : " + cursor.getString(0) + "\n");
                    buffer.append("NAME : " + cursor.getString(1) + "\n");
                    buffer.append("EMAIL : " + cursor.getString(2) + "\n\n");
                }
                showMessage("DATA" , buffer.toString());

            }
        });
    }

    public void deleteAllData(){
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer var = myDB.deleteAllData();
                if(var > 0){
                    Toast.makeText(MainActivity.this, "All Data has been deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Deletion Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showMessage(String title , String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();

    }
}
