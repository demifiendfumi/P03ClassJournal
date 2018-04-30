package com.myapplicationdev.android.p03_classjournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity {
    TextView tvHeader,tvDG;
    Button btnSubmit;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnSubmit = (Button)findViewById(R.id.buttonSubmit);
        tvHeader = (TextView)findViewById(R.id.textViewHeader);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the RadioGroup object
                RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);
                //Get the Id of the selected radio button in the RadioGroup
                int selectedButtonId = rg.getCheckedRadioButtonId();
                //Get the radio button object from the Id we had gotten above
                RadioButton rb = (RadioButton)findViewById(selectedButtonId);
                //Show a Toast that display the text on the selected radio button
                Toast.makeText(getBaseContext(),rb.getText(), Toast.LENGTH_LONG).show();
                Intent i = new Intent();
                i.putExtra("dailygrade", rb.getText());
                i.putExtra("week",tvHeader.getText());
                // Set result to RESULT_OK to indicate normal
                // response and pass in the intent containing the
                // like
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }

}
