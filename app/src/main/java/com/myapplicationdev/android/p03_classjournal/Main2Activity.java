package com.myapplicationdev.android.p03_classjournal;

import android.content.Intent;
import android.net.Uri;
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
    ArrayList<DailyGrade> grade = new ArrayList<DailyGrade>();
    final int requestCodeForAdd = 1;
    int weeknum = 0;

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
        if(item.equals("C347")){
            grade.add(new DailyGrade("week 1", "B"));
            grade.add(new DailyGrade("week 2", "C"));
            grade.add(new DailyGrade("week 3", "A"));
        }

        weeknum = grade.size();

        // Link this Activity object, the row.xml layout for
        //  each row and the food String array together
        aa = new GradeAdapter(this, R.layout.row, grade);
        lv.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, AddActivity.class);
                //String weeks = intent.getStringExtra("week");
                //String dailygrades = intent.getStringExtra("dailygrade");

                //DailyGrade newGrade = new DailyGrade(weeks, dailygrades);
                startActivityForResult(intent,requestCodeForAdd);
                //grade.add(newGrade);
            }
        });


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rpIntent = new Intent(Intent.ACTION_VIEW);



                // Set the URL to be used.
                rpIntent.setData(Uri.parse("http://www.rp.edu.sg"));
                startActivity(rpIntent);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";

                Intent email = new Intent(Intent.ACTION_SEND);

                email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"jason_lim@rp.edu.sg"});
                email.putExtra(Intent.EXTRA_SUBJECT,
                        "Hi faci," +
                                "I am ..." +
                                "\n" +
                                "Please see my remarks so far, thankyou!");
                for(int i = 0; i < grade.size(); i++) {
                    message += grade.get(i).getWeek() +
                                    " DG: " +
                                    grade.get(i).getGrade()
                    + "\n";
                }
                email.putExtra(Intent.EXTRA_TEXT,
                message);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,
                        "Choose an Email client :"));
            }
        });
        //lv.setAdapter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Only handle when 2nd activity closed normally
        //  and data contains something
        if (requestCode == requestCodeForAdd) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    // Get data passed back from 2nd activity
                    String dailygrade = data.getStringExtra("dailygrade");
                    String statement = "";
                    // If it is activity started by clicking 	//  Superman, create corresponding String
                    if (requestCode == requestCodeForAdd) {
                        weeknum += 1;
                        grade.add(new DailyGrade("Week " + weeknum, dailygrade));
                        statement = "Week " + weeknum + " DG " + dailygrade;
                        aa.notifyDataSetChanged();
                    }
                    Toast.makeText(Main2Activity.this, statement,
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
