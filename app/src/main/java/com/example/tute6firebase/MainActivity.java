package com.example.tute6firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText txtid,txtname,txtadd,txtcont;
    Button btnsave,btnshow,btnupdate,btndel;
    DatabaseReference dbreference;
    Student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtid=findViewById(R.id.edid);
        txtname=findViewById(R.id.edname);
        txtadd=findViewById(R.id.edaddress);
        txtcont=findViewById(R.id.edcontact);

        btnsave=findViewById(R.id.save);
        btnshow=findViewById(R.id.show);
        btnupdate=findViewById(R.id.update);
        btndel=findViewById(R.id.delete);

        std = new Student();
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbreference= FirebaseDatabase.getInstance().getReference().child("Student").child("std1");
                dbreference.removeValue();
                Toast.makeText(getApplicationContext(),"successfully deleted",Toast.LENGTH_LONG).show();

            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbreference=FirebaseDatabase.getInstance().getReference();
                dbreference.child("Student").child("std1").child("name").setValue(txtname.getText().toString().trim());
                dbreference.child("Student/std1/address").setValue(txtadd.getText().toString().trim());
                Toast.makeText(getApplicationContext(),"successfully updad",Toast.LENGTH_LONG).show();

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbreference=FirebaseDatabase.getInstance().getReference().child("Student");
                try{
                    if(TextUtils.isEmpty(txtid.getText().toString()))//chek krnawa field ek emptyda kila
                        Toast.makeText(getApplicationContext(),"Empty id",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(txtname.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty name",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(txtadd.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty name",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(txtcont.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty name",Toast.LENGTH_LONG).show();
                    else
                    {
                        std.setId(txtid.getText().toString().trim());//use setters convert to sring
                        std.setName(txtname.getText().toString().trim());
                        std.setAddress(txtadd.getText().toString().trim());
                        std.setContact(Integer.parseInt(txtcont.getText().toString().trim()));//int val
                        dbreference.child("std1").setValue(std);
                        Toast.makeText(getApplicationContext(),"sucessfully inserted",Toast.LENGTH_LONG).show();
                        //clearControls();
                    }
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getApplicationContext(),"invalide",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbreference=FirebaseDatabase.getInstance().getReference().child("Student/std2");
               dbreference.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.hasChildren())
                       {
                           txtid.setText(snapshot.child("id").getValue().toString());
                           txtname.setText(snapshot.child("name").getValue().toString());
                           txtadd.setText(snapshot.child("address").getValue().toString());
                           txtcont.setText(snapshot.child("contact").getValue().toString());
                           //kola patin "" athule tienne db eke column names ew enne api hadapu class eke dunna nam(Student)
                       }
                       else
                       {
                           Toast.makeText(getApplicationContext(),"can't find std1",Toast.LENGTH_LONG).show();
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });


            }
        });
    }
}