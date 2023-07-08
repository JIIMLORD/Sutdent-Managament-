package com.example.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.studentmanagement.adapter.adaptersubject;
import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Subject;

import java.util.ArrayList;

public class ActivitySubject extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewSubject;
    ArrayList<Subject> ArrayListSubject;
    com.example.studentmanagement.database.database database;
    com.example.studentmanagement.adapter.adaptersubject adaptersubject;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarSubject);
        listViewSubject = findViewById(R.id.listviewSubject);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListSubject = new ArrayList<>();

        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            int credit = cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            ArrayListSubject.add(new Subject(id, title, credit, time, place));
        }

        adaptersubject = new adaptersubject(ActivitySubject.this, ArrayListSubject);
        listViewSubject.setAdapter(adaptersubject);
        cursor.moveToFirst();
        cursor.close();

        //Tạo sự kiện click vào item subject
        listViewSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivitySubject.this,ActivityStudent.class);
                int id_subject = ArrayListSubject.get(i).getId();
                //truyền dữ liệu id subject qua activity studnet
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
            }
        });
    }

    //Thêm một menu là add vào toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //Nếu click vào add thì chuyển qua màn hình add subject
        if (item.getItemId() == R.id.menuadd) {
            Intent intent1 = new Intent(ActivitySubject.this, ActivityAddSubject.class);
            startActivity(intent1);

            //Nếu click vào nút còn lại là nút back thì quay lại main
        } else {
            Intent intent = new Intent(ActivitySubject.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    //Nếu click back ở điện thoại sẽ trở về main activity
    @Override
    public void onBackPressed() {
        count++;
        if(count>=1){
            Intent intent = new Intent(ActivitySubject.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    //Run

    public void information(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if(id == pos){
                Intent intent = new Intent(ActivitySubject.this,ActivitySubjectInformation.class);

                intent.putExtra("id",id);
                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);
            }
        }
    }

    //Phươngt thức xóa subject
    public void delete(final int position){
        //Đối tượng cửa sổ
        Dialog dialog = new Dialog(this);

        //Nạp layout vào dialog
        dialog.setContentView(R.layout.dialogdeletesubject);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesDelSub);
        Button btnNo = dialog.findViewById(R.id.btnNoDelSub);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Xóa subject trong CSDL
                database.DeleteSubject(position);

                //Xóa Student
                database.DeleteSubjectStudent(position);

                //Cập nhật lại acctivitysubject
                Intent intent = new Intent(ActivitySubject.this,ActivitySubject.class);
                startActivity(intent);
            }
        });

        //Đóng dialog nếu click No
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        //Show dialog
        dialog.show();
    }

    public void update(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if(id == pos){
                Intent intent = new Intent(ActivitySubject.this, ActivityUpdateSubject.class);

                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);
                //Gửi dữ liệu qu activity update
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);
            }
        }
    }
}