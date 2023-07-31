package com.example.studentmanagement;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnSubject,btnAuthor,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAuthor = findViewById(R.id.btnAuthor);
        btnExit = findViewById(R.id.btnExit);
        btnSubject = findViewById(R.id.btnSubject);

        //Click tác giả
        btnAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAuthor();
            }
        });

        //Click Subject
        btnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Chuyển qua activity subject
                Intent intent = new Intent(MainActivity.this, ActivitySubject.class);
                startActivity(intent);

            }
        });

        //Click exit app
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogExit();
            }
        });

    }

    //Hàm hiển thị cửa sổ dialog exit
    private void DialogExit() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogexit);

        //Tắt click ngoài là thoát
        dialog.setCanceledOnTouchOutside(true);

        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivitySubject.class);
                startActivity(intent);

                //Thoát
                Intent intent1 = new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent1);
            }
        });

        //Nếu click no thì đóng cửa sổ
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    //Hiển thị thông tin tác giả
    private void DialogAuthor() {
        //Tạo đối tượng cửa sổ dialog
        Dialog dialog = new Dialog(this);

        //Nạp layout vào dialog
        dialog.setContentView(R.layout.dialoginformation);
        dialog.show();
    }
    //Angay
    //Run...
}
