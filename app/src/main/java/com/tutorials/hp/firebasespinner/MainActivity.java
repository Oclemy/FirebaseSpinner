package com.tutorials.hp.firebasespinner;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tutorials.hp.firebasespinner.m_FireBase.FirebaseHelper;
import com.tutorials.hp.firebasespinner.m_Model.Spacecraft;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner sp= (Spinner) findViewById(R.id.sp);

        //SETUP FB
        db=FirebaseDatabase.getInstance().getReference();
        helper=new FirebaseHelper(db);

        sp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,helper.retrieve()));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 displayInputDialog();
            }
        });
    }

    //DISPLAY INPUT DILAOG
     private void displayInputDialog()
     {
         Dialog d=new Dialog(this);
         d.setTitle("Firebase database");
         d.setContentView(R.layout.input_dialog);

         final EditText nameTxt= (EditText) d.findViewById(R.id.nameEditText);
         Button saveBtn= (Button) d.findViewById(R.id.saveBtn);

         //SAVE
         saveBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //GET DATA
                 String name=nameTxt.getText().toString();

                 //set data
                 Spacecraft s=new Spacecraft();
                 s.setName(name);

                 //SAVE
                 if(name != null && name.length()>0)
                 {
                     if(helper.save(s))
                     {
                         nameTxt.setText("");
                     }

                 }else
                 {
                     Toast.makeText(MainActivity.this, "Name Cannot Be Empty", Toast.LENGTH_SHORT).show();
                 }

             }
         });

         d.show();
     }


}






















