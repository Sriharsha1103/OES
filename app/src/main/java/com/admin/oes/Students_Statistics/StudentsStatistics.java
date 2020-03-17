package com.admin.oes.Students_Statistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.admin.oes.QuestionsModel;
import com.admin.oes.R;
import com.admin.oes.Statistics.StatisticsAdapter;
import com.admin.oes.Statistics.StatisticsModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentsStatistics extends AppCompatActivity {
    BarChart barChart;
    String role="";
    FirebaseAuth firebaseAuth;
    ArrayList<BarEntry> values = new ArrayList<>();
    private BarDataSet set1;
    List<StatisticsModel> listData;
    RecyclerView rv;
    StudentsStatisticsAdapter adapter;
    public  ArrayList<String> question=new ArrayList<>() ;
    public ArrayList<ArrayList> x ;
    ArrayList<String> xAxisLabel = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    String name="",uid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_statistics);
        Intent i=getIntent();
        name=i.getStringExtra("Student_name");
        setTitle(name);
        uid=i.getStringExtra("Student_uid");
        barChart = findViewById(R.id.barchart);
        listData = new ArrayList<>();
        adapter = new StudentsStatisticsAdapter(StudentsStatistics.this);
        rv=(RecyclerView)findViewById(R.id.id_user_test_details_recycler_view);
        sharedPreferences = getApplicationContext().getSharedPreferences("sp", 0);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //  barChart.setOnChartValueSelectedListener(DetailsDisplay.this);
        {
            barChart.setDrawBarShadow(false);
            barChart.setTouchEnabled(true);
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
            barChart.setPinchZoom(false);
            barChart.setDrawGridBackground(false);
            // barChart.setDrawYLabels(false);
            //    ValueFormatter xAxisFormatter = new DayAxisValueFormatter(barChart);
            XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setGranularity(1f); // only intervals of 1 day
            xAxis.setLabelCount(7);
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
        }


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users/"+ uid+"/Exams").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                listData.clear();
                x=new ArrayList<>();
                for (final DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String Test_Name=childDataSnapshot.getKey();
                    QuestionsModel questionModel = childDataSnapshot.child(Test_Name).getValue(QuestionsModel.class);
                    Log.d("data", String.valueOf(question.size()));
                    String Name = childDataSnapshot.child("Name").getValue().toString();
                    String TotalQ = childDataSnapshot.child("TotalQ").getValue().toString();
                    String role=databaseReference.child("Users").child("uid").child("Role").toString();
                    String Correctans = childDataSnapshot.child("Correctans").getValue().toString();
                    String wrongans = childDataSnapshot.child("wrongans").getValue().toString();
                    String Time = childDataSnapshot.child("Time").getValue().toString();
                    String Teacher = childDataSnapshot.child("Teacher").getValue().toString();
                    String ID = childDataSnapshot.child("ID").getValue().toString();
                    listData.add(new StatisticsModel(Test_Name,Name,TotalQ,role,Correctans,wrongans,Time,Teacher,ID,questionModel.getQuestions()));
                    Log.d("data12", String.valueOf(questionModel.getQuestions()));
                }
                adapter.setlist(listData);
                rv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        setDataBar();
    }


    private void setDataBar() {

        float start = 1f;

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").child(uid).child("Exams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                float i = 1;
                String parent = dataSnapshot.getKey();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String v = childDataSnapshot.child("Correctans").getValue().toString();
                    values.add(new BarEntry(i, Float.parseFloat(v)));
                    i++;
                }


                barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));

                if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
                    //set1 = new BarDataSet(values, "Stats of student ");
                    set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
                    set1.setValues(values);
                    barChart.getData().notifyDataChanged();
                    barChart.notifyDataSetChanged();

                } else {
                    set1 = new BarDataSet(values, "Stats of student ");

                    set1.setDrawIcons(false);

                    set1.setColors(ColorTemplate.MATERIAL_COLORS);


                    int startColor = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_blue_dark);
                    int endColor = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_blue_bright);
                    set1.setGradientColor(startColor, endColor);

                    int startColor1 = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_orange_light);
                    int startColor2 = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_blue_light);
                    int startColor3 = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_orange_light);
                    int startColor4 = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_green_light);
                    int startColor5 = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_red_light);
                    int endColor1 = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_blue_dark);
                    int endColor2 = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_purple);
                    int endColor3 = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_green_dark);
                    int endColor4 = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_red_dark);
                    int endColor5 = ContextCompat.getColor(StudentsStatistics.this, android.R.color.holo_orange_dark);

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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
