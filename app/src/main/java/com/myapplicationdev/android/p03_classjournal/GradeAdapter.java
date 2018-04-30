package com.myapplicationdev.android.p03_classjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GradeAdapter extends ArrayAdapter<DailyGrade>{
    private ArrayList<DailyGrade> grade;
    private Context context;
    private TextView tvWeek, tvDG, tvGrade;
    private ImageView ivBook;

    public GradeAdapter(Context context, int resource,  ArrayList<DailyGrade> objects) {
        super(context, resource, objects);
        grade = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvWeek = (TextView) rowView.findViewById(R.id.textViewWeek);
        tvDG = (TextView) rowView.findViewById(R.id.textViewDG);
        tvGrade = (TextView) rowView.findViewById(R.id.textViewGrade);
        // Get the ImageView object
        ivBook = (ImageView) rowView.findViewById(R.id.imageView);


        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        DailyGrade currentGrade = grade.get(position);
        // Set the TextView to show the food

        tvWeek.setText(currentGrade.getWeek());
        tvGrade.setText(currentGrade.getGrade());

        // Return the nicely done up View to the ListView
        return rowView;
    }
}
