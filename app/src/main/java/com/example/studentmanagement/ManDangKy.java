package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagement.database.database;
import com.example.studentmanagement.database.databasetaikhoan;
import com.example.studentmanagement.model.TaiKhoan;

public class ManDangKy extends AppCompatActivity {

    EditText edtDKTaiKhoan, edtDKMatKhau, edtDKEmail;
    Button btnDKDangKy, btnDKDangNhap;
    com.example.studentmanagement.database.databasetaikhoan databasetaikhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_ky);

        databasetaikhoan = new databasetaikhoan(this);

        AnhXa();

        //Click Button đăng ký
        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();
                String email = edtDKEmail.getText().toString();

                TaiKhoan taiKhoan = CreateTaiKhoan();
                if (taikhoan.equals(" ") || matkhau.equals(" ") || email.equals(" ")){
                    Log.e("Thông báo : ","Chưa nhập đầy đủ thông tin");
                }
                //Nếu đầy đủ thông tin nhập vào thì add tài khoàn vào database
                else {
                    databasetaikhoan.AddTaiKhoan(taiKhoan);
                    Toast.makeText(ManDangKy.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Trở về đăng nhập
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //Phương thức tạo tài khoản
    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatKhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen = 1;

        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,email,phanquyen);

        return tk;
    }

    private void AnhXa(){
        edtDKEmail = findViewById(R.id.dkemail);
        edtDKMatKhau = findViewById(R.id.dkmatkhau);
        edtDKTaiKhoan = findViewById(R.id.dktaikhoan);
        btnDKDangKy = findViewById(R.id.dkdangky);
        btnDKDangNhap = findViewById(R.id.dkdangnhap);
    }
}