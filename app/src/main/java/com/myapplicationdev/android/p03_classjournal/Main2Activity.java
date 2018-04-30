package com.myapplicationdev.android.p03_classjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<DailyGrade> grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv = (ListView) this.findViewById(R.id.lvWeek);
        Button btnInfo = (Button)findViewById(R.id. buttonInfo);
        Button btnAdd = (Button)findViewById(R.id. buttonAdd);
        Button btnEmail = (Button)findViewById(R.id. buttonEmail);

        Intent i = getIntent();
        String item = i.getStringExtra("item");

        // Create a few food objects in Food array
        grade = new ArrayList<DailyGrade>();
        if(item.equals("C347")){
            grade.add(new DailyGrade("week 1", "B"));
            grade.add(new DailyGrade("week 2", "C"));
            grade.add(new DailyGrade("week 3", "A"));
        }

        // Link this Activity object, the row.xml layout for
        //  each row and the food String array together
        aa = new GradeAdapter(this, R.layout.row, grade);
        lv.setAdapter(aa);

    }
}
