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
import com.example.studentmanagement.model.Subject;

public class ActivityAddSubject extends AppCompatActivity {

    Button btnAddSubject;
    EditText edtSubjectTitle, edtSubjectCredit, edtSubjectTime, edtSubjectPlace;
    com.example.studentmanagement.database.database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        btnAddSubject = findViewById(R.id.ButtonAddSubject);
        edtSubjectTitle = findViewById(R.id.EditTextSubjectTitle);
        edtSubjectCredit = findViewById(R.id.EditTextSubjectCredit);
        edtSubjectTime = findViewById(R.id.EditTextSubjectTime);
        edtSubjectPlace = findViewById(R.id.EditTextSubjectPlace);

        database = new database(this);

        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
            }
        });
    }

    private void DialogAdd() {
        //Tạo đối tượng cửa sổ
        Dialog dialog = new Dialog(this);
        //nạp layout vào dialog
        dialog.setContentView(R.layout.dialogadd);
        //Tắt click ngoài là thoát
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjecttitle = edtSubjectTitle.getText().toString().trim();
                String credit = edtSubjectCredit.getText().toString().trim();
                String time = edtSubjectTime.getFontFeatureSettings().trim().trim();
                String place = edtSubjectPlace.getFontFeatureSettings().toString().trim();

                //Nếu dữ liệu chưa nhập đủ
                if(subjecttitle.equals("") || credit.equals("") || time.equals("") || place.equals("")){
                    Toast.makeText(ActivityAddSubject.this, "Did not enter enough information",Toast.LENGTH_SHORT).show();
                }
                //Gán cho đối tượng subject giá trị được nhập vào
                else {
                    Subject subject = CreaSubject();

                    //Add trong database
                    database.AddSubjects(subject);

                    //Add thành công thì chuyển qua giao diện subject
                    Intent intent = new Intent(ActivityAddSubject.this,ActivitySubject.class);
                    startActivity(intent);

                    Toast.makeText(ActivityAddSubject.this,"more success",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Nếu không add nữa
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        //Show dialog
        dialog.show();
    }

    //Tạo subject
    private Subject CreaSubject(){
        String subjecttitle = edtSubjectTitle.getText().toString().trim();
        int credit = Integer.parseInt(edtSubjectCredit.getText().toString().trim());
        String time = edtSubjectTime.getFontFeatureSettings().trim().trim();
        String place = edtSubjectPlace.getFontFeatureSettings().toString().trim();

        Subject subject = new Subject(subjecttitle, credit, time, place);
        return subject;
    }
}