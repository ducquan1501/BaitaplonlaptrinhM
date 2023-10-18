package com.example.projectmobile;


import android.graphics.Color;
import android.os.Bundle;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
public class Trigo_graph extends Fragment {
    private EditText editText1, editText2, editText3, editText4, editText5, editText6,
            editText7, editText8;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trigo_graph, container, false);
        //1. Gọi các thành phần từ Layout, thiết lập tham số
        LineChart lineChart = view.findViewById(R.id.chart);
        EditText[] editTextArray = new EditText[16];

        editText1 = view.findViewById(R.id.parameter1);
        editText2 = view.findViewById(R.id.parameter2);
        editText3 = view.findViewById(R.id.parameter3);
        editText4 = view.findViewById(R.id.parameter4);
        editText5 = view.findViewById(R.id.parameter5);
        editText6 = view.findViewById(R.id.parameter6);
        editText7 = view.findViewById(R.id.parameter7);
        editText8 = view.findViewById(R.id.parameter8);

        Button executeB = view.findViewById(R.id.executeB);
        Button deleteB = view.findViewById(R.id.deleteButton);
        Button undoB = view.findViewById(R.id.undoB);

        //2. Tạo đường trục tung, trục hoành
        //2.1. Xây dựng Entry - tập hợp các phần tử để tạo thành đồ thị
        ArrayList<Entry> zeroLineEntriesX = new ArrayList<>();
        for (float x0 = -55; x0 <= 55; x0 += 0.1) {
            float y0 = 0;
            zeroLineEntriesX.add(new Entry(x0, y0));
        }
        ArrayList<Entry> zeroLineEntriesY = new ArrayList<>();
        zeroLineEntriesY.add(new Entry(0f, 100f));
        zeroLineEntriesY.add(new Entry(0f, -100f));

        //2. Tạo DataSet cho các đường trục tung, trục hoành
        LineDataSet zeroLineDataSetX = new LineDataSet(zeroLineEntriesX, "Ox");
        zeroLineDataSetX.setDrawValues(false);
        zeroLineDataSetX.setDrawCircles(false);
        zeroLineDataSetX.setColor(Color.BLACK);
        zeroLineDataSetX.setLineWidth(3f);

        LineDataSet zeroLineDataSetY = new LineDataSet(zeroLineEntriesY, "Oy");
        zeroLineDataSetX.setDrawValues(false);
        zeroLineDataSetY.setDrawCircles(false);
        zeroLineDataSetY.setColor(Color.BLACK);
        zeroLineDataSetY.setLineWidth(3f);

        LineData lineData = new LineData(zeroLineDataSetX, zeroLineDataSetY);

        //3. Cấu hình đồ thị
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMaximum(55f);
        xAxis.setAxisMinimum(-55f);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(0.1f);
        xAxis.setLabelCount(10);
//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getAxisLabel(float value, AxisBase axis) {
//                return String.valueOf(value);
//            }
//        });

        YAxis yAxis = lineChart.getAxisLeft();
        YAxis yAxisR = lineChart.getAxisRight();
        yAxisR.setEnabled(false);
        yAxis.setDrawLabels(true);
        yAxis.setAxisMaximum(15f);
        yAxis.setAxisMinimum(-15f);
        yAxis.setGranularity(0.1f);
        yAxis.setLabelCount(16);

        lineChart.setData(lineData);
        Legend lg = lineChart.getLegend();
        lg.setEnabled(false);

        Description dp = new Description();
        dp.setText("Nguyễn Đức Quân CNTT 14-04");
        dp.setTextColor(Color.BLUE);
        dp.setTextSize(10f);
        lineChart.setDescription(dp);

        lineChart.setSaveEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.invalidate();

        //4. Thiết lập chức năng cho nút Button
        executeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //4.1. Xử lý các EditText trống
                String et1 = editText1.getText().toString();
                String et2 = editText2.getText().toString();
                String et3 = editText3.getText().toString();
                String et4 = editText4.getText().toString();
                String et5 = editText5.getText().toString();
                String et6 = editText6.getText().toString();
                String et7 = editText7.getText().toString();
                String et8 = editText8.getText().toString();
                if(et1.isEmpty()||et1.equals("-")||et1.equals('.')){
                    editText1.setText("0");
                }
                if(et2.isEmpty()||et2.equals("-")||et2.equals('.')){
                    editText2.setText("1");
                }
                if(et3.isEmpty()||et3.equals("-")||et3.equals('.')){
                    editText3.setText("0");
                }
                if(et4.isEmpty()||et4.equals("-")||et4.equals('.')){
                    editText4.setText("1");
                }
                if(et5.isEmpty()||et5.equals("-")||et5.equals('.')){
                    editText5.setText("0");
                }
                if(et6.isEmpty()||et6.equals("-")||et6.equals('.')){
                    editText6.setText("1");
                }
                if(et7.isEmpty()||et7.equals("-")||et7.equals('.')){
                    editText7.setText("0");
                }
                if(et8.isEmpty()||et8.equals("-")||et8.equals('.')){
                    editText8.setText("1");
                }
                double p1 = Double.parseDouble(editText1.getText().toString());
                double p2 = Double.parseDouble(editText2.getText().toString());
                double p3 = Double.parseDouble(editText3.getText().toString());
                double p4 = Double.parseDouble(editText4.getText().toString());
                double p5 = Double.parseDouble(editText5.getText().toString());
                double p6 = Double.parseDouble(editText6.getText().toString());
                double p7 = Double.parseDouble(editText7.getText().toString());
                double p8 = Double.parseDouble(editText8.getText().toString());
                //4.2. Tạo mảng chứa tập hợp các điểm hình thành nên đồ thị
                List<Entry> entries = new ArrayList<>();
                for (double x = -55; x < 55; x += 0.01) {
                    double y = p1*Math.sin(p2*x)
                            + p3*Math.cos(p4*x)
                            + p5*Math.tan(p6*x)
                            + p7*Math.cosh(p8*x);
                    entries.add(new Entry((float) x, (float) y));
                }
                //4.3 Khai báo dataSet
                LineDataSet dataSet = new LineDataSet(entries, "");
                int i = lineData.getDataSetCount();
                if (i % 7 == 0) {
                    dataSet.setColor(Color.YELLOW);
                } else if (i % 7 == 1) {
                    dataSet.setColor(Color.parseColor("#FFAE00"));
                } else if (i % 7 == 2) {
                    dataSet.setColor(Color.RED);
                } else if (i % 7 == 3) {
                    dataSet.setColor(Color.GREEN);
                } else if (i % 7 == 4) {
                    dataSet.setColor(Color.parseColor("#02C7FC"));
                } else if (i % 7 == 5) {
                    dataSet.setColor(Color.parseColor("#2E00FF"));
                } else if (i % 7 == 6) {
                    dataSet.setColor(Color.parseColor("#A320D5"));
                }
                dataSet.setDrawValues(false);
                dataSet.setDrawCircles(false);
                dataSet.setLineWidth(2f);
                //4.4. Tạo một LineData chứa LineDataSet của đường kẻ
                lineData.addDataSet(dataSet);
                lineChart.setData(lineData);

                //4.5. Vẽ đồ thị
                lineChart.invalidate();
            }
        });
        //4.6 Xóa editText
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText5.setText("");
                editText6.setText("");
                editText7.setText("");
                editText8.setText("");
            }
        });
        //4.7. Nút Undo
        undoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = lineData.getDataSetCount();
                if (i > 2) {
                    lineData.removeDataSet(i - 1);
                }
                lineChart.invalidate();
            }
        });
        return view;
    }
}
