package com.example.sqliteexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,contact,dob;
    Button insert,update,delete,view;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name_id);
        contact=findViewById(R.id.contact_id);
        dob=findViewById(R.id.dod_id);

        insert=findViewById(R.id.insert_button_id);
        update=findViewById(R.id.update_button_id);
        delete=findViewById(R.id.delete_button_id);
        view=findViewById(R.id.view_button_id);

        dbHelper=new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt=name.getText().toString().trim();
                String contactTxt=contact.getText().toString().trim();
                String dobTxt=dob.getText().toString();

                Boolean checkInsertData=dbHelper.insertUserDetails(nameTxt,contactTxt,dobTxt);
                if(checkInsertData){
                    Toast.makeText(MainActivity.this,"New entry inserted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"New entry not inserted",Toast.LENGTH_SHORT).show();

                }

            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt=name.getText().toString().trim();
                String contactTxt=contact.getText().toString().trim();
                String dobTxt=dob.getText().toString();

                Boolean checkUpdateData=dbHelper.updateUserDetails(nameTxt,contactTxt,dobTxt);
                if(checkUpdateData){
                    Toast.makeText(MainActivity.this,"entry updated",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"New entry not updated",Toast.LENGTH_SHORT).show();

                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt=name.getText().toString().trim();

                Boolean checkDeleteData=dbHelper.deleteUserDetails(nameTxt);
                if(checkDeleteData){
                    Toast.makeText(MainActivity.this,"entry deleted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"New entry not deleted",Toast.LENGTH_SHORT).show();

                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor cursorResult=dbHelper.getData();
               if(cursorResult.getCount()==0){
                   Toast.makeText(MainActivity.this,"no entry exist",Toast.LENGTH_SHORT).show();
                   return;
               }

               StringBuilder buffer=new StringBuilder();
               while(cursorResult.moveToNext()){
                   buffer.append("name :").append(cursorResult.getString(0)).append("\n");
                   buffer.append("contact :").append(cursorResult.getString(1)).append("\n");
                   buffer.append("dob :"+cursorResult.getString(2)+"\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
               builder.setCancelable(true);
               builder.setTitle("user entries");
               builder.setMessage(buffer.toString());
               builder.show();

            }
        });

    }
}