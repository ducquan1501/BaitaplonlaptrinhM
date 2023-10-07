package com.example.projectmobile;


import android.graphics.Color;
import android.os.Bundle;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editText1, editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1. Gọi các thành phần từ Layout, thiết lập tham số
        LineChart lineChart = findViewById(R.id.chart);
        editText1 = findViewById(R.id.parameter1);
        editText2 = findViewById(R.id.parameter2);
        Button button = (Button) findViewById(R.id.executeB);
        //Thiết lập chức năng cho nút Button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //Xử lý các editText trống và các chữ cái
            String et1 = editText1.getText().toString();
                if(et1.isEmpty()) {
                    editText1.setText("0");
                }

            String et2 = editText2.getText().toString();
                if(et2.isEmpty()){
                    editText2.setText("0");
                }
            Float p1 = Float.parseFloat(editText1.getText().toString());
            Float p2 = Float.parseFloat(editText2.getText().toString());
            //2. Tạo mảng chứa tập hợp các điểm hình thành nên đồ thị
            List<Entry> entries = new ArrayList<>();
            for (float x = -15; x <= 15; x += 0.02)
            {
                float y = p1*x + p2;
                entries.add(new Entry(x, y));
            }
            //2.2 Khai báo dataSet
            LineDataSet dataSet = new LineDataSet(entries, "y = "+p1+"x " +p2);
            dataSet.setColor(Color.BLUE);
            dataSet.setDrawValues(false);
            dataSet.setDrawCircles(false);

            ArrayList<Entry> zeroLineEntriesX = new ArrayList<>();
            zeroLineEntriesX.add(new Entry(25f, 0f));
            zeroLineEntriesX.add(new Entry(-25f, 0f));
            ArrayList<Entry> zeroLineEntriesY = new ArrayList<>();
            zeroLineEntriesY.add(new Entry(0f, 20f));
            zeroLineEntriesY.add(new Entry(0f, -20f));

            //2.3 Tạo zeroLineDataSet để vẽ trục hoành, trục tung
            LineDataSet zeroLineDataSetX = new LineDataSet(zeroLineEntriesX, "Ox");
            zeroLineDataSetX.setDrawCircles(false);
            zeroLineDataSetX.setColor(Color.BLACK);
            zeroLineDataSetX.setLineWidth(3f);

            LineDataSet zeroLineDataSetY = new LineDataSet(zeroLineEntriesY, "Oy");
            zeroLineDataSetY.setDrawCircles(false);
            zeroLineDataSetY.setColor(Color.BLACK);
            zeroLineDataSetY.setLineWidth(3f);
            //3. Tạo một LineData chứa LineDataSet của đường kẻ

            LineData lineData = new LineData(dataSet, zeroLineDataSetX, zeroLineDataSetY);
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setDrawLabels(true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setAxisMaximum(15f);
            xAxis.setAxisMinimum(-15f);
            xAxis.setDrawGridLines(true);
            xAxis.setGranularity(0.01f);
            xAxis.setLabelCount(10);
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getAxisLabel(float value, AxisBase axis) {
                    return String.valueOf(value);
                }
        });

        YAxis yAxis = lineChart.getAxisLeft();
        YAxis yAxisR = lineChart.getAxisRight();
        yAxisR.setEnabled(false);
        yAxis.setDrawLabels(true);
        yAxis.setAxisMaximum(20f);
        yAxis.setAxisMinimum(-20f);
        yAxis.setGranularity(0.01f);
        yAxis.setLabelCount(10);

        lineChart.setData(lineData);
                Description dp = new Description();
                dp.setText("Đồ thị hàm số bậc nhất");
                dp.setTextColor(Color.RED);
                dp.setTextSize(10f);
                lineChart.setDescription(dp);
                lineChart.invalidate();
                }
            });
    }
}
