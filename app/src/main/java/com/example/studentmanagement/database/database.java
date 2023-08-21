package com.example.studentmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.model.Subject;

public class database extends SQLiteOpenHelper {

    //Tên database
    private static String DATABASE_NAME = "studentmanagement";
    //Bảng môn học
    private static String TABLE_SUBJECT = "subject";
    private static String ID_SUBJECT = "idsubject";
    private static String SUBJECT_TITLE = "subjecttitle";
    private static String CREIDTS = "credits";
    private static String TIME = "time";
    private static String PLACE = "place";
    private static int VERSION = 1;

    //Bảng sinh viên
    private static String TABLE_STUDENT = "student";
    private static String ID_STUDENT = "idstudent";
    private static String STUDENT_NAME = "studentname";
    private static String SEX = "sex";
    private static String STUDENT_CODE = "studentcode";
    private static String DATE_OF_BIRTH = "dateofbirth";

    //Tạo bảng môn học
    private String SQLQuery = "CREATE TABLE " + TABLE_SUBJECT +" ( "+ID_SUBJECT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SUBJECT_TITLE+" TEXT, "
            +CREIDTS+" INTERGER, "
            +TIME+" TEXT, "
            + PLACE+" TEXT) ";

    //Tạo bảng sinh viên
    private String SQLQuery1 = "CREATE TABLE " + TABLE_STUDENT +" ( "+ID_STUDENT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +STUDENT_NAME+" TEXT, "
            +SEX+" TEXT, "
            +STUDENT_CODE+" TEXT, "
            +DATE_OF_BIRTH+" TEXT, "
            +ID_SUBJECT+" INTEGER , FOREIGN KEY ( "+ID_SUBJECT +" ) REFERENCES "+
            TABLE_SUBJECT+"("+ID_SUBJECT+"))";

    public database(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        sqLiteDatabase.execSQL(SQLQuery1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Phương thức insert subject
    public void AddSubjects(Subject subject){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SUBJECT_TITLE,subject.getSubject_title());
        values.put(CREIDTS,subject.getNumber_of_credit());
        values.put(TIME,subject.getTime());
        values.put(PLACE,subject.getPlace());

        db.insert(TABLE_SUBJECT, null,values);
        db.close();
    }

    //Cập nhật môn học
    public boolean UpdateSubject(Subject subject,int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SUBJECT_TITLE,subject.getSubject_title());
        values.put(CREIDTS,subject.getNumber_of_credit());
        values.put(TIME,subject.getTime());
        values.put(PLACE,subject.getPlace());

        db.update(TABLE_SUBJECT,values,ID_SUBJECT+" = "+id,null);
        return true;
    }

    //Lấy dữ liệu subject
    public Cursor getDataSubject(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_SUBJECT,null);
        return cursor;
    }

    public int DeleteSubject(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_SUBJECT,ID_SUBJECT+" = "+i,null);
        return res;
    }

    //Dùng để xóa các Student đã bị xóa của Subject
    public int DeleteSubjectStudent(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT,ID_STUDENT+" = "+i,null);
        return res;
    }

    //Insert Student
    public void AddStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME,student.getStudent_name());
        values.put(SEX,student.getSex());
        values.put(STUDENT_CODE,student.getStudent_code());
        values.put(DATE_OF_BIRTH,student.getDate_of_birth());
        values.put(ID_SUBJECT,student.getId_subject());

        db.insert(TABLE_STUDENT,null,values);
        db.close();
    }

    //Lấy tất cả sinh viên thuộc môn học đó
    public Cursor getDataStudent(int id_subject){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_STUDENT+" WHERE "+ID_SUBJECT+" = "+id_subject,null);
        return res;
    }

    //Xóa Student
    public int DeleteStudent(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT,ID_STUDENT+" = "+i,null);
        return res;
    }

    //Cập nhật sv
    public boolean updateStudent(Student student, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME,student.getStudent_name());
        values.put(SEX,student.getSex());
        values.put(DATE_OF_BIRTH,student.getDate_of_birth());
        values.put(STUDENT_CODE,student.getStudent_code());
        db.update(TABLE_STUDENT,values,ID_STUDENT+" = "+id, null);
        return true;
    }
}


