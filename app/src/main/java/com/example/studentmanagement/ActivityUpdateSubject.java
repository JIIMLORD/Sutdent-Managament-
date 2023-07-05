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

import java.util.Stack;

public class ActivityUpdateSubject extends AppCompatActivity {

    EditText edtUpdateTitle, edtUpdateCredit, edtUpdateTime, edtUpdatePlace;
    Button btnUpdateSubject;

    com.example.studentmanagement.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        edtUpdateTitle = findViewById(R.id.EditTextUpdateSubjectTitle);
        edtUpdateCredit = findViewById(R.id.EditTextUpdateSubjectCredit);
        edtUpdateTime = findViewById(R.id.EditTextUpdateSubjectTime);
        edtUpdatePlace = findViewById(R.id.EditTextUpdateSubjectPlace);
        btnUpdateSubject = findViewById(R.id.ButtonUpdateSubject);

        //Lấy dữ liệu intent
        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);
        String title = intent.getStringExtra("title");
        int credit = intent.getIntExtra("credit",0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        edtUpdateTitle.setText(title);
        edtUpdateTime.setText(time);
        edtUpdatePlace.setText(place);
        edtUpdateCredit.setText(credit+"");

        database = new database(this);

        btnUpdateSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdate(id);
            }
        });
    }

    private void DialogUpdate(int id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdatesubject);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesUpdate);
        Button btnNo = dialog.findViewById(R.id.btnNoUpdate);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjecttitle = edtUpdateTitle.getText().toString().trim();
                String credit = edtUpdateCredit.getText().toString().trim();
                String time = edtUpdateTime.getText().toString().trim();
                String plcae = edtUpdatePlace.getText().toString().trim();

                if(subjecttitle.equals("") || credit.equals("") || time.equals("") || plcae.equals("")){
                    Toast.makeText(ActivityUpdateSubject.this,"This you enter enough information",Toast.LENGTH_SHORT).show();
                }
                else{
                    Subject subject = updatesubject();

                    database.UpdateSubject(subject,id);

                    //Update thành công thì chuyển qua màn hình activity subject
                    Intent intent = new Intent(ActivityUpdateSubject.this,ActivitySubject.class);
                    startActivity(intent);

                    Toast.makeText(ActivityUpdateSubject.this,"more success",Toast.LENGTH_SHORT).show();
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

    //Lữu trữ dữ liệu edittext cập nhật
    private Subject updatesubject(){
        String subjecttitle = edtUpdateTitle.getText().toString().trim();
        int credit = Integer.parseInt(edtUpdateCredit.getText().toString().trim());
        String time = edtUpdateTime.getText().toString().trim();
        String plcae = edtUpdatePlace.getText().toString().trim();

        Subject subject = new Subject(subjecttitle,credit,time,plcae);
        return subject;
    }
}