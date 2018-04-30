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
    final int requestCodeForAdd = 1;
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, AddActivity.class);
                String weeks = intent.getStringExtra("week");
                String dailygrades = intent.getStringExtra("dailygrade");

                DailyGrade newGrade = new DailyGrade(weeks, dailygrades);
                startActivityForResult(intent,requestCodeForAdd);
                grade.add(newGrade);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Only handle when 2nd activity closed normally
        //  and data contains something
        if(resultCode == RESULT_OK){
            if (data != null) {
                // Get data passed back from 2nd activity
                String dailygrade = data.getStringExtra("dailygrade");
                String statement = "";
                grade = new ArrayList<DailyGrade>();
                int weeknum=(grade.size()+2);

                grade.add(new DailyGrade("Week "+weeknum , dailygrade));
                // If it is activity started by clicking 	//  Superman, create corresponding String
                if(requestCode == requestCodeForAdd){
                    statement = "New item Added";
                }
                Toast.makeText(Main2Activity.this, statement,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
