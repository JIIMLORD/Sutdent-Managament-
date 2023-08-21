package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Student;

public class ActivityAddStudent extends AppCompatActivity {

    Button buttonAddStudent;
    EditText editTextStudentName, editTextStudentCode, editTextDateofBirth;
    RadioButton radiobuttonMale, radiobuttonFemale;
    database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        buttonAddStudent = findViewById(R.id.ButtonAddStudent);
        editTextDateofBirth = findViewById(R.id.EditTextStudentBirthday);
        editTextStudentCode = findViewById(R.id.EditTextStudentCode);
        editTextStudentName = findViewById(R.id.EditTextStudentName);
        radiobuttonMale = findViewById(R.id.radionbuttonMale);
        radiobuttonFemale = findViewById(R.id.radionbuttonFemale);


        //lay id subject
        Intent intent = getIntent();
        int id_subject = intent.getIntExtra("id_subject",0);
        database = new database(this);
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd(id_subject);
            }
        });

    }
    //dialog add
    private void DialogAdd(int id_subject) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogaddstudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesAddStudent);
        Button btnNo = dialog.findViewById(R.id.btnNoAddStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextStudentName.getText().toString().trim();
                String code = editTextStudentCode.getText().toString().trim();
                String birthday = editTextDateofBirth.getText().toString().trim();
                String sex = "";
                if (radiobuttonMale.isChecked()) {
                    sex = "Male";
                } else {
                    if (radiobuttonFemale.isChecked())
                        sex = "Female";
                }
                if (name.equals("") || code.equals("") || birthday.equals("") || sex.equals(""))
                {
                    Toast.makeText(ActivityAddStudent.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Student student = CreateStudent(id_subject);
                    database.AddStudent(student);
                    Intent intent = new Intent(ActivityAddStudent.this, ActivityStudent.class);
                    intent.putExtra("id_subject", id_subject);
                    startActivity(intent);
                    Toast.makeText(ActivityAddStudent.this, "more success", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
        //run


    }

    private Student CreateStudent(int id_subject ) {

        String name = editTextStudentName.getText().toString().trim();
        String code = editTextStudentCode.getText().toString().trim();
        String birthday = editTextDateofBirth.getText().toString().trim();
        String sex = "";
        if (radiobuttonMale.isChecked()) {
            sex = "Male";
        } else {
            if (radiobuttonFemale.isChecked())
                sex = "Female";

        }
        Student student = new Student(name, sex, code, birthday, id_subject);
        return student;

    }

}