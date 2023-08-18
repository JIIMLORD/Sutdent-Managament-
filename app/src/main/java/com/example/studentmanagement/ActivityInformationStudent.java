package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityInformationStudent extends AppCompatActivity {

    TextView txtName, txtSex, txtCode, txtBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_student);

        txtName = findViewById(R.id.txtStudentName);
        txtBirthday = findViewById(R.id.txtStudetDateofbirth);
        txtCode = findViewById(R.id.txtStudentCode);
        txtSex =  findViewById(R.id.txtStudentSex);

        //Nhan du lieu
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");


        //Gan len textView tuong ung
        txtName.setText(name);
        txtCode.setText(code);
        txtSex.setText(sex);
        txtBirthday.setText(birthday);



    }
}