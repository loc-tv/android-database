package com.example.selected_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SubActivity extends AppCompatActivity {

    private EditText etID;
    private ImageView imgAvt;
    private EditText etFullname;
    private EditText etPhone;
    private Button btnOK;
    private Button btnCancel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        etID = findViewById(R.id.etID_SubAc);
        imgAvt = findViewById(R.id.imgAvt_SubAc);
        etFullname = findViewById(R.id.etFullName_SubAc);
        etPhone = findViewById(R.id.etPhone_SubAc);
        btnOK = findViewById(R.id.btnOk_SubAc);
        btnCancel = findViewById(R.id.btnCancel_SubAc);

        //Lay du lieu khi ben MainActivity bam edit de sua
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            int id =bundle.getInt("Id");
            String name =  bundle.getString("Name");
            String phone = bundle.getString("Phone");
            etFullname.setText(name);
            etPhone.setText(phone);
            etID.setText(String.valueOf(id)); //convert int to string
            btnOK.setText("Edit");
        }

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(etID.getText().toString());

                String name = etFullname.getText().toString();
                String phone = etPhone.getText().toString();

//                MyDB myDB = new MyDB(SubActivity.this, "ContactDBase", null, 1);
//                int rowsAffected = myDB.updateContact(id, name, "", phone);

                //if (rowsAffected > 0){
                    Intent intent = new Intent();

                    Bundle bundle = new Bundle();
                    bundle.putInt("Id", id);
                    bundle.putString("Name", name);
                    bundle.putString("Phone", phone);

                    intent.putExtras(bundle);
                    setResult(150, intent);
                    finish();
                //}

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SubActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}