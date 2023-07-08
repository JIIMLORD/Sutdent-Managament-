package com.example.studentmanagement;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Student;

public class ActivityUpdateStudent extends AppCompatActivity {

    EditText editTextUpdateName, editTextUpdateCode, editTextUpdateBirthday;
    RadioButton rdMale, rdFemale;
    Button btnUpdateStudent;
    database database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        editTextUpdateBirthday = findViewById(R.id.EditTextStudentBirthdayUpdate);
        editTextUpdateCode = findViewById(R.id.EditTextStudentCodeUpdate);
        editTextUpdateName = findViewById(R.id.EditTextStudentNameUpdate);
        rdFemale = findViewById(R.id.radionbuttonFemaleUpdate);
        rdMale = findViewById(R.id.radionbuttonMaleUpdate);
        btnUpdateStudent = findViewById(R.id.ButtonUpdateStudent);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        int id_subject = intent.getIntExtra("id_subject", 0);

        //Gan gia tri len editText va radioButton
        editTextUpdateName.setText(name);
        editTextUpdateCode.setText(code);
        editTextUpdateBirthday.setText(birthday);

        if (sex.equals("Male")) {
            rdMale.setChecked(true);
            rdFemale.setChecked(false);

        }
        else
        {
            rdFemale.setChecked(true);
            rdMale.setChecked(false);
        }
        database = new database(this);

        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiallogUpdate(id,id_subject);
            }
        });

    }

    private void DiallogUpdate(int id, int id_subject)
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdatestudent);
        dialog.setCanceledOnTouchOutside(false);
        Button btnYes = dialog.findViewById(R.id.btnYesUpdateStudent);
        Button btnNo = dialog.findViewById(R.id.btnNoUpdateStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextUpdateName.getText().toString().trim();
                String code = editTextUpdateCode.getText().toString().trim();
                String birthday = editTextUpdateName.getText().toString().trim();

                Student student = createstudent();

                
                if (name.equals("") || code.equals("") || birthday.equals("")){
                    Toast.makeText(ActivityUpdateStudent.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                }
                else {
                    //update
                    database.updateStudent(student,id);

                    //Chuyển qua activity student và cập nhật la danh sách sinh viên
                    Intent intent = new Intent(ActivityUpdateStudent.this,ActivityUpdateStudent.class);
                    //Gửi id của subject
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);
                    Toast.makeText(ActivityUpdateStudent.this, "More Success", Toast.LENGTH_SHORT).show();

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

    }

    private Student createstudent(){
        String name = editTextUpdateName.getText().toString().trim();
        String code = editTextUpdateCode.getText().toString().trim();
        String birthday = editTextUpdateBirthday.getText().toString().trim();
        String sex ="";
        if (rdMale.isChecked()) {
            sex = "Male";
        }
        else
        {
            sex = "Female";
        }
        Student student = new Student(name,sex,code,birthday);
        return student;
    }


}
