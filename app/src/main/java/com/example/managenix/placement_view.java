package com.example.managenix;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class placement_view extends AppCompatActivity {
    private BarChart barChart1, barChart2, barChart3;
    private PieChart pieChart;
    private ImageSlider imageSlider;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_view);

        barChart1 = findViewById(R.id.bar1);
        barChart2 = findViewById(R.id.bar2);
        barChart3 = findViewById(R.id.bar3);
        pieChart = findViewById(R.id.pie);
        imageSlider = findViewById(R.id.slder);

        textView = findViewById(R.id.link);

        ArrayList<BarEntry> visitor = new ArrayList<>();
        visitor.add(new BarEntry(2018, 64));
        visitor.add(new BarEntry(2019, 69));
        visitor.add(new BarEntry(2020, 71));
        visitor.add(new BarEntry(2021, 80));
        visitor.add(new BarEntry(2022, 95));

        BarDataSet barDataSet = new BarDataSet(visitor, "%Students Placed");
        barDataSet.setColors(Color.BLUE);
        barDataSet.setValueTextColor(Color.RED);
        barDataSet.setValueTextSize(14f);

        BarData barData = new BarData(barDataSet);

        barChart1.setFitBars(true);
        barChart1.setData(barData);
        barChart1.getDescription().setText("bar chart");
        barChart1.animateY(2000);

        ArrayList<BarEntry> student = new ArrayList<>();
        student.add(new BarEntry(2018, 16));
        student.add(new BarEntry(2019, 24));
        student.add(new BarEntry(2020, 23));
        student.add(new BarEntry(2021, 14));
        student.add(new BarEntry(2022, 10));

        BarDataSet barDataSet2 = new BarDataSet(student, "%Students for higher studies");
        barDataSet2.setColors(Color.BLUE);
        barDataSet2.setValueTextColor(Color.RED);
        barDataSet2.setValueTextSize(14f);

        BarData barData2 = new BarData(barDataSet2);

        barChart2.setFitBars(true);
        barChart2.setData(barData2);
        barChart2.getDescription().setText("bar chart");
        barChart2.animateY(2000);

        ArrayList<BarEntry> dream = new ArrayList<>();
        dream.add(new BarEntry(2018, 57));
        dream.add(new BarEntry(2019, 52));
        dream.add(new BarEntry(2020, 44));
        dream.add(new BarEntry(2021, 63));
        dream.add(new BarEntry(2022, 40));

        BarDataSet barDataSet3 = new BarDataSet(dream, "%Students got their Dream Package");
        barDataSet3.setColors(Color.BLUE);
        barDataSet3.setValueTextColor(Color.RED);
        barDataSet3.setValueTextSize(14f);

        BarData barData3 = new BarData(barDataSet3);

        barChart3.setFitBars(true);
        barChart3.setData(barData3);
        barChart3.getDescription().setText("bar chart");
        barChart3.animateY(2000);



        ArrayList<PieEntry> lakh = new ArrayList<>();
        lakh.add(new PieEntry(57f, "COMPS"));
        lakh.add(new PieEntry(66f, "IT"));
        lakh.add(new PieEntry(27f, "EXTC"));
        lakh.add(new PieEntry(28f, "MECH"));

        PieDataSet pieDataSet = new PieDataSet(lakh, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(14f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Highest Placement Percentage, Department wise");
        pieChart.animateY(2000);


        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.amazon));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/DeltaX.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/seclore.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/sap.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/godrejboyce.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/newgen.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/infosys.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/Accelya.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/cactus.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/Vistar_logo.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/xoriant.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/zoya-logo.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/Jio.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/TCS.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/capegemini.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/Cimpress.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/ingram.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/majesco-social-logo.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/aonesalasar.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/Accelya.webp"));
        slideModels.add(new SlideModel("https://placement.dbit.in/images/companyLogos/ENDURANCE.webp"));

        imageSlider.setImageList(slideModels, true);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://placement.dbit.in/");
            }
        });


    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}
