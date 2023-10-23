package com.example.projectmobile.basic_graph_resource;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectmobile.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Basic_Graph extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{
    private EditText editText1, editText2, editText3, editText4, editText5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_graph, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.basic_graph_bnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //1. Gọi các thành phần từ Layout, thiết lập tham số
        LineChart lineChart = view.findViewById(R.id.chart);
        editText1 = view.findViewById(R.id.parameter1);
        editText2 = view.findViewById(R.id.parameter2);
        editText3 = view.findViewById(R.id.parameter3);
        editText4 = view.findViewById(R.id.parameter4);
        editText5 = view.findViewById(R.id.parameter5);

        Button executeB = view.findViewById(R.id.executeB);
        Button deleteB = view.findViewById(R.id.deleteButton);
        Button undoB = view.findViewById(R.id.undoB);


        //2. Tạo đường trục tung, trục hoành
        //2.1. Xây dựng Entry - tập hợp các phần tử để tạo thành đồ thị
        ArrayList<Entry> zeroLineEntriesX = new ArrayList<>();
        for (float x0 = -66; x0 <= 66; x0 += 0.1) {
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
        xAxis.setAxisMaximum(61.724f);
        xAxis.setAxisMinimum(-61.724f);
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
        lg.setEnabled(true);
        lg.setTextColor(Color.WHITE);
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
                Float p1 = Float.parseFloat(editText1.getText().toString());
                Float p2 = Float.parseFloat(editText2.getText().toString());
                Float p3 = Float.parseFloat(editText3.getText().toString());
                Float p4 = Float.parseFloat(editText4.getText().toString());
                Float p5 = Float.parseFloat(editText5.getText().toString());

                //4.2. Tạo mảng chứa tập hợp các điểm hình thành nên đồ thị
                List<Entry> entries = new ArrayList<>();
                for (float x = -65; x <= 65; x += 0.01) {
                    float y = p1 * x * x * x * x + p2 * x * x * x + p3 * x * x + p4 * x + p5;
                    entries.add(new Entry(x, y));
                }
                //4.3 Khai báo dataSet
                //4.3.1. Tạo label
//                float paraArray[] = {p1, p2, p3, p4, p5};
//                String label = "y = ";
//                for(int i = 0; i<paraArray.length; i+=1)
//                {

//                    if(paraArray[i]==0&&i==0){
//                        label += "";
//                    }else if(paraArray[i]<0){
//                        if(paraArray[i]==-1){
//                            label += "-x^"+(4-i);
//                        } else if (paraArray[i]==0) {
//                            label += "";
//                        } else{
//                            label += "-"+Math.abs(paraArray[i])+"x^"+(4-i);
//                        }
//                    }else{
//                        if(paraArray[i]==1){
//                            label += "+x^"+(4-i);
//                        } else if (paraArray[i]==0){
//                            label += "";
//                        } else{
//                            label += Math.abs(paraArray[i])+"x^"+(4-i);
//                        }
//                    }
                String label = formatPolynomial(p1, p2, p3, p4, p5);
                LineDataSet dataSet = new LineDataSet(entries, label);
                int i = lineData.getDataSetCount();
                if (i % 7 == 0) {
                    dataSet.setColor(Color.YELLOW);
                } else if (i % 7 == 1) {
                    dataSet.setColor(Color.parseColor("#FFAE00"));
                } else if (i % 7 == 2) {
                    dataSet.setColor(Color.WHITE);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if(item.getItemId()==R.id.basic_graph_item){
            transaction.replace(R.id.basic_frame, new Basic_Graph()).commit();
        }
        else if (item.getItemId()==R.id.calculator_item){
            transaction.replace(R.id.basic_frame, new Basic_Calculator()).commit();
        }
        return true;
    }
    public String formatPolynomial(float a, float b, float c, float d, float e) {
        StringBuilder polynomial = new StringBuilder();
        polynomial.append("y=");
        // Xử lý hệ số a (mũ bằng 4)
        if (a != 0) {
            if (a != 1) {
                polynomial.append(a);
            }
            polynomial.append("x^4");
        }

        // Xử lý hệ số b (mũ bằng 3)
        if (b != 0) {
            if (a != 0) {
                if (b > 0) {
                    polynomial.append(" + ");
                } else {
                    polynomial.append(" - ");
                }
            }
            if (Math.abs(b) != 1) {
                if (b > 0) {
                    polynomial.append(" + ");
                } else {
                    polynomial.append(" - ");
                }
                polynomial.append(Math.abs(b));
            }
            polynomial.append("x^3");
        }

        // Xử lý hệ số c (mũ bằng 2)
        if (c != 0) {
            if (a != 0 || b != 0) {
                if (c > 0) {
                    polynomial.append(" + ");
                } else {
                    polynomial.append(" - ");
                }
            }
            if (Math.abs(c) != 1) {
                if (c > 0) {
                    polynomial.append(" + ");
                } else {
                    polynomial.append(" - ");
                }
                polynomial.append(Math.abs(c));
            }
            polynomial.append("x^2");
        }
        // Xử lý hệ số d (mũ bằng 1)
        if (d != 0) {
            if (a != 0 || b != 0 || c != 0) {
                if (d > 0) {
                    polynomial.append(" + ");
                } else {
                    polynomial.append(" - ");
                }
            }
            if (Math.abs(d) != 1) {
                if (d > 0) {
                    polynomial.append(" + ");
                } else {
                    polynomial.append(" - ");
                }
                polynomial.append(Math.abs(d));
            }
            if (d < 0) {
                polynomial.append(" - ");
            }
            polynomial.append("x");
        }
        // Xử lý hệ số e (mũ bằng 0)
        if (e != 0) {
            if (a != 0 || b != 0 || c != 0 || d != 0) {
                if (e > 0) {
                    polynomial.append(" + ");
                } else {
                    polynomial.append(" - ");
                }
            }
            if (e < 0) {
                polynomial.append(" - ");
            }
            polynomial.append(Math.abs(e));
        }
        return polynomial.toString();
    }
}