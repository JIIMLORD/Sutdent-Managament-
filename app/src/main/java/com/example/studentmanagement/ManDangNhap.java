package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.studentmanagement.database.database;

public class ManDangNhap extends AppCompatActivity {

    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap, btnDangKy;

    com.example.studentmanagement.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_nhap);

        AnhXa();

        database = new database(this);

        //Tạo sự kiện click button chuyển sang màn hình đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManDangNhap.this, ManDangKy.class);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Gán cho các biến là giá trị nhập vào từ EditText
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();

                //Sử dụng con trỏ để lấy dữ liệu gọi tới getData() để lấy tất cả tài khoản từ database
                Cursor cursor = database.getData();

                //Thực hiện vòng lặp để lấy dữ liệu từ Cursor với moveToNext() di chuyển tiếp
                while (cursor.moveToNext()){
                    //Lấy dữ liệu và gắn bào biến , dữ liệu idtaikhoan ở ô 0, tài khoản ở ô 1, mật khẩu ở ô 2, email ở ô 3 và phanquyen ở ô 4
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

                    //Nếu tài khoán và mặt khẩu nhập vào từ bàn phím khớp với database
                    if (datatentaikhoan.equals(tentaikhoan) && datatentaikhoan.equals(matkhau)){
                        //Lấy dữ liệu phanquyen và id
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        Intent intent = new Intent(ManDangNhap.this,MainActivity.class);

                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);

                        startActivity(intent);
                    }
                }

                cursor.moveToFirst();
                cursor.close();
            }
        });
    }

    private void AnhXa() {
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.dangky);
        btnDangNhap = findViewById(R.id.dangnhap);
    }
}