package com.example.studentmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.studentmanagement.model.TaiKhoan;

public class databasetaikhoan extends SQLiteOpenHelper {

    //Tên database
    private static String DATABASE_NAME = "studenmanagement";

    //biến bảng tài khoản
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";
    private static int VERSION = 1;


    private Context context;

    //biến lưu câu lệnh tạo tài khoản
    private String SQLQuery = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+" TEXT UNIQUE, "
            +MAT_KHAU+" TEXT, "
            +EMAIL+" TEXT, "
            + PHAN_QUYEN+" INTEGER) ";
    //Insert dữ liệu vào bảng tài khoản
    //Chú ý phần quyền: 1.Admin / 2.User
    private String SQLQuery2 = "INSERT INTO TaiKhoan VAlUES (null,'admin','admin','admin@gmail.com',2)";
    private String SQLQuery3 = "INSERT INTO TaiKhoan VAlUES (null,'khanh','khanh','khanh@gmail.com',1)";

    public databasetaikhoan(@Nullable Context context) {
        super(context, DATABASE_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        sqLiteDatabase.execSQL(SQLQuery2);
        sqLiteDatabase.execSQL(SQLQuery3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Phương thức lấy tất cả tài khoản
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_TAIKHOAN,null);
        return res;
    }

    //phương thức add tài khoản vào database
    public void AddTaiKhoan(TaiKhoan taiKhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        //Thực hiện phương thức insert thông qua ContentValues
        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN,taiKhoan.getmTenTaiKhoan());
        values.put(MAT_KHAU,taiKhoan.getmMatkhau());
        values.put(EMAIL,taiKhoan.getmEmail());
        values.put(PHAN_QUYEN,taiKhoan.getmPhanQuyen());

        db.insert(TABLE_TAIKHOAN, null, values);

        //Đóng lại khi không dùng
        db.close();
        Log.e("Add TK ","TC");
    }
}
