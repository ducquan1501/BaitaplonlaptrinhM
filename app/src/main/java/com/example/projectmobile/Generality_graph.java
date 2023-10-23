package com.example.projectmobile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class Generality_graph extends Fragment {

    private EditText editText1, editText2, editText3, editText4, editText5, editText6;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generality_graph, container, false);

        LineChart lineChart = view.findViewById(R.id.chart);
        editText1 = view.findViewById(R.id.parameter1);
        editText2 = view.findViewById(R.id.parameter2);
        editText3 = view.findViewById(R.id.parameter3);
        editText4 = view.findViewById(R.id.parameter4);
        editText5 = view.findViewById(R.id.parameter5);
        editText6 = view.findViewById(R.id.parameter6);

        Button executeB = view.findViewById(R.id.executeB);
        Button deleteB = view.findViewById(R.id.deleteButton);
        Button undoB = view.findViewById(R.id.undoB);

        //2. Tạo đường trục tung, trục hoành
        //2.1. Xây dựng Entry - tập hợp các phần tử để tạo thành đồ thị
        ArrayList<Entry> zeroLineEntriesX = new ArrayList<>();
        for (float x0 = -65; x0 <= 65; x0 += 0.1) {
            float y0 = 0;
            zeroLineEntriesX.add(new Entry(x0, y0));
        }

        ArrayList<Entry> zeroLineEntriesY = new ArrayList<>();
        zeroLineEntriesY.add(new Entry(0f, 100f));
        zeroLineEntriesY.add(new Entry(0f, -100f));

        //2. Tạo DataSet cho các đường trục tung, trục hoành, đồng thời cấu hình đồ họa cho các trục
        LineDataSet zeroLineDataSetX = new LineDataSet(zeroLineEntriesX, "Ox");
        zeroLineDataSetX.setDrawValues(false);
        zeroLineDataSetX.setDrawCircles(false);
        zeroLineDataSetX.setColor(Color.WHITE);
        zeroLineDataSetX.setLineWidth(3f);

        LineDataSet zeroLineDataSetY = new LineDataSet(zeroLineEntriesY, "Oy");
        zeroLineDataSetX.setDrawValues(false);
        zeroLineDataSetY.setDrawCircles(false);
        zeroLineDataSetY.setColor(Color.WHITE);
        zeroLineDataSetY.setLineWidth(3f);

        LineData lineData = new LineData(zeroLineDataSetX, zeroLineDataSetY);

        //3. Cấu hình đồ thị
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMaximum(62.621f);
        xAxis.setAxisMinimum(-62.621f);
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
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisMinimum(-100f);
        yAxis.setGranularity(0.1f);
        yAxis.setLabelCount(16);

        lineChart.setData(lineData);
        Legend lg = lineChart.getLegend();
        lg.setEnabled(false);

        Description dp = new Description();
        dp.setText("Nguyễn Đức Quân CNTT 14-04");
        dp.setTextColor(Color.WHITE);
        dp.setTextSize(10f);

        lineChart.getXAxis().setTextColor(Color.WHITE);
        lineChart.getAxisLeft().setTextColor(Color.WHITE);
        lineChart.setDescription(dp);
        lineChart.setSaveEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.invalidate();

        //4. Thiết lập chức năng cho nút Button
        executeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //4.1. Xử lý các editText trống
                String et1 = editText1.getText().toString();
                if (et1.isEmpty() || et1.equals("-") || et1.equals(".")) {
                    editText1.setText("0");
                }
                String et2 = editText2.getText().toString();
                if (et2.isEmpty() || et2.equals("-") || et2.equals(".")) {
                    editText2.setText("0");
                }
                String et3 = editText3.getText().toString();
                if (et3.isEmpty() || et3.equals("-") || et3.equals(".")) {
                    editText3.setText("0");
                }
                String et4 = editText4.getText().toString();
                if (et4.isEmpty() || et4.equals("-") || et4.equals(".")) {
                    editText4.setText("0");
                }
                String et5 = editText5.getText().toString();
                if (et5.isEmpty() || et5.equals("-") || et5.equals(".")) {
                    editText5.setText("0");
                }
                String et6 = editText6.getText().toString();
                if (et6.isEmpty() || et6.equals("-") || et6.equals(".")) {
                    editText6.setText("0");
                }
                Double p1 = Double.parseDouble(editText1.getText().toString());
                Double p2 = Double.parseDouble(editText2.getText().toString());
                Double p3 = Double.parseDouble(editText3.getText().toString());
                Double p4 = Double.parseDouble(editText4.getText().toString());
                Double p5 = Double.parseDouble(editText5.getText().toString());
                Double p6 = Double.parseDouble(editText6.getText().toString());

                //4.2. Tạo mảng chứa tập hợp các điểm hình thành nên đồ thị
                List<Entry> entries = new ArrayList<>();
                List<Entry> entries2 = new ArrayList<>();
                if(ExponentCheck(p2)||ExponentCheck(p4)||ExponentCheck(p6)) {
                    for (double x = 0.0001; x < 65; x += 0.01) {
                        double y = p1 * Math.pow(x, p2) + p3 * Math.pow(x, p4) + p5 * Math.pow(x, p6);
                        entries.add(new Entry((float) x, (float) y));
                    }
                } else{
                    for (double x = -65.0001; x <= 55; x += 0.01) {
                        double y = p1 * Math.pow(x, p2) + p3 * Math.pow(x, p4) + p5 * Math.pow(x, p6);
                        if (x < 0) {
                            entries.add(new Entry((float) x, (float) y));
                        } else if (x>0) {
                            entries2.add(new Entry((float) x, (float) y));
                        }
                    }
                }
                //4.3 Khai báo dataSet
                LineDataSet dataSet = new LineDataSet(entries, "");
                LineDataSet dataSet2 = new LineDataSet(entries2, "");
                int i = lineData.getDataSetCount();
                if((i-2)/2 % 3 == 0){
                    dataSet.setColor(Color.GREEN);
                    dataSet2.setColor(Color.GREEN);
                } else if ((i-2)/2 % 3 == 1){
                    dataSet.setColor(Color.RED);
                    dataSet2.setColor(Color.RED);
                } else{
                    dataSet.setColor(Color.BLUE);
                    dataSet2.setColor(Color.BLUE);
                }
                dataSet.setDrawValues(false);
                dataSet.setDrawCircles(false);
                dataSet2.setDrawValues(false);
                dataSet2.setDrawCircles(false);
                dataSet.setLineWidth(2f);
                dataSet2.setLineWidth(2f);

                //4.4. Tạo một LineData chứa LineDataSet của đường kẻ
                lineData.addDataSet(dataSet);
                lineData.addDataSet(dataSet2);
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
    public static boolean ExponentCheck(double p){
        if(0<p && p<1){
            return true;
        }else
            return false;
    }
    public static boolean ExponnetCheck2(double p){
        if(p<0){
            return true;
        }
        else
            return false;
    }
}