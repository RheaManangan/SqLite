package com.example.studentinformation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText studentid,fname,address;
    Button btninsert,btnupdate,btndelete,btnview;
    DataBase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentid=findViewById(R.id.studentid);
        fname=findViewById(R.id.fname);
        address=findViewById(R.id.address);

        btninsert=findViewById(R.id.btninsert);
        btnupdate=findViewById(R.id.btnupdate);
        btndelete=findViewById(R.id.btndelete);
        btnview=findViewById(R.id.btnview);
        DB=new DataBase(this);


        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String student=studentid.getText().toString();
                String name=fname.getText().toString();
                String ad=address.getText().toString();

                Boolean checkinsertdata=DB.insertuserdata(student,name,ad);
                if (checkinsertdata==true)
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();



            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String student=studentid.getText().toString();
                String name=fname.getText().toString();
                String ad=address.getText().toString();

                Boolean checkupdatedata=DB.updateuserdata(student,name,ad);
                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_SHORT).show();



            }
        });



        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String student=studentid.getText().toString();

                Boolean checkudeletedata=DB.deletedata(student);
                if (checkudeletedata==true)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_SHORT).show();



            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Data Exists",Toast.LENGTH_SHORT).show();
                    return;
                }



                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){

                    buffer.append("Student ID: "+res.getString(0)+"\n");
                    buffer.append("Name: "+res.getString(1)+"\n");
                    buffer.append("Address: "+res.getString(2)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Information");
                builder.setMessage(buffer.toString());
                builder.show();


            }
        });















    }
}
