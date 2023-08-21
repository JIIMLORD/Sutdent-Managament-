package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagement.database.database;

public class AdminControl extends AppCompatActivity {

    Button ButtonAddClass;
    EditText EditTextTeacherCode,EditTextSubjectName,EditTextSubjectClass,EditTextTeacherName;
    database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_control);

        ButtonAddClass = findViewById(R.id.ButtonAddClass);
        EditTextTeacherCode = findViewById(R.id.EditTextTeacherCode);
        EditTextSubjectClass = findViewById(R.id.EditTextSubjectClass);
        EditTextSubjectName = findViewById(R.id.EditTextSubjectName);
        EditTextTeacherName =findViewById(R.id.EditTextTeacherName);

        Intent intent = getIntent();

        int id_subject = intent.getIntExtra("id_subject",0);
        database = new database(this);
        ButtonAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddClass(id_subject);
            }
        });
    }

    private void DialogAddClass(int id_subject) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogaddclass);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYesAddClass = dialog.findViewById(R.id.btnYesAddClass);
        Button btnNoAddClass = dialog.findViewById(R.id.btnNoAddClass);

        btnYesAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameclass = EditTextSubjectClass.getText().toString().trim();
                String namesubject = EditTextSubjectName.getText().toString().trim();
                String nameteacher = EditTextTeacherName.getText().toString().trim();
                String code = EditTextTeacherCode.getText().toString().trim();

                if (nameclass.equals("") || code.equals("") || namesubject.equals("") || nameteacher.equals("")) {
                    Toast.makeText(AdminControl.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}