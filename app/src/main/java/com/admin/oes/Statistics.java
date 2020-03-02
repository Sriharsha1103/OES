package com.admin.oes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Statistics extends AppCompatActivity {

    DatabaseReference databaseReference;
    BarChart barChart;
    FirebaseAuth firebaseAuth;
    PieChart pieChart ;
    PieDataSet pieDataSet ;
    List<PieEntry> entries;
    ArrayList<String> xAxisLabel = new ArrayList<>();
    PieData pieData;
    FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

             barChart = findViewById(R.id.barchart);
            barChart.setDrawBarShadow(false);
            barChart.setTouchEnabled(false);
            barChart.setDragEnabled(false);
            barChart.setScaleEnabled(false);
            barChart.setScaleXEnabled(false);
            barChart.setScaleYEnabled(false);
            barChart.setDrawValueAboveBar(true);
            barChart.getDescription().setEnabled(false);
            // if more than 60 entries are displayed in the barChart, no values will be
            // drawn
            barChart.setMaxVisibleValueCount(60);
            // scaling can now only be done on x- and y-axis separately
            barChart.setPinchZoom(true);
            barChart.setDrawGridBackground(false);
            // barChart.setDrawYLabels(false);

            //    ValueFormatter xAxisFormatter = new DayAxisValueFormatter(barChart);

            XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setGranularity(1f); // only intervals of 1 day
         //   xAxis.setLabelCount(7);


            //   xAxis.setValueFormatter(xAxisFormatter);

            //   ValueFormatter custom = new MyValueFormatter("$");

            YAxis leftAxis = barChart.getAxisLeft();
            leftAxis.setLabelCount(8, false);
            //  leftAxis.setValueFormatter(custom);
            leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            leftAxis.setSpaceTop(15f);
            leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

            YAxis rightAxis = barChart.getAxisRight();
            rightAxis.setDrawGridLines(false);
            rightAxis.setLabelCount(8, false);
            //  rightAxis.setValueFormatter(custom);
            rightAxis.setSpaceTop(15f);
            rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

            Legend l = barChart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(false);
            l.setForm(Legend.LegendForm.SQUARE);
            l.setFormSize(9f);
            l.setTextSize(11f);
            l.setXEntrySpace(4f);

//            XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
//            mv.setChartView(barChart); // For bounds control
//            barChart.setMarker(mv);

        pieChart = (PieChart) findViewById(R.id.chart1);



        //PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY();

        pieDataSet = new PieDataSet(entries, "n");
        pieData = new PieData(pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(pieData);
        pieChart.animateY(3000);


        //TODO: Teahc diff



//        Date d = new Date();
//        s  = DateFormat.format("MMMM d, yyyy HH:mm:ss", d.getTime());
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Last_used_on/" + sharedPreferences.getString("uid","Not aval"));
//        databaseReference.child("Name").setValue(sharedPreferences.getString("name","Not aval"));
//        databaseReference.child("Email").setValue(sharedPreferences.getString("email","Not aval"));
//        databaseReference.child("Date").setValue(s);
        setDataBar();

    }

    public void AddValuesToPIEENTRY(){
        entries= new ArrayList<>();
        entries.add(new PieEntry(18.5f,"green"));
        entries.add(new PieEntry(26.7f, "yellow"));
        entries.add(new PieEntry(24.0f,"red"));
        entries.add(new PieEntry(30.8f,"blue"));

    }

    private void setDataBar() {
        final ArrayList<BarEntry> values = new ArrayList<>();
        databaseReference.child("Users").child(firebaseUser.getUid()).child("Exams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                values.clear();
                float i=1;

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                   values.add(new BarEntry(i,Float.parseFloat(childDataSnapshot.child("Correctans").getValue().toString())));
                   //xAxisLabel.add(childDataSnapshot.getKey());
                   i++;


                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
      });


//        values.add(new BarEntry(2f, 60,));
//        values.add(new BarEntry(1f, 20));
//        values.add(new BarEntry(3f, 30));
//        values.add(new BarEntry(4f, 40));
//        values.add(new BarEntry(5f, 50));

//        final ArrayList<String> xAxisLabel = new ArrayList<>();
//        xAxisLabel.add("Temp");
        databaseReference.child("Users").child(firebaseUser.getUid()).child("Exams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                      xAxisLabel.add(childDataSnapshot.getKey());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
        XAxis xAxis = barChart.getXAxis();


        BarDataSet set1;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "Stats of student ");

            set1.setDrawIcons(false);

//            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            /*int startColor = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor = ContextCompat.getColor(this, android.R.color.holo_blue_bright);
            set1.setGradientColor(startColor, endColor);*/

            int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
            int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
            int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
            int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
            int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
            int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
            int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);

            List<GradientColor> gradientColors = new ArrayList<>();
            gradientColors.add(new GradientColor(startColor1, endColor1));
            gradientColors.add(new GradientColor(startColor2, endColor2));
            gradientColors.add(new GradientColor(startColor3, endColor3));
            gradientColors.add(new GradientColor(startColor4, endColor4));
            gradientColors.add(new GradientColor(startColor5, endColor5));

            set1.setGradientColors(gradientColors);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            barChart.setData(data);
        }


    }


}
