package com.example.projectmobile.basic_graph_resource;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.projectmobile.R;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Basic_Calculator extends Fragment {
    EditText editText1, editText2, editText3, editText4, editText5;
    TextView sol11, sol12, sol21, sol22, sol31, sol32, sol41, sol42;
    Button calButton, deleteB;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_calculator, container, false);
        editText1 = view.findViewById(R.id.parameter1);
        editText2 = view.findViewById(R.id.parameter2);
        editText3 = view.findViewById(R.id.parameter3);
        editText4 = view.findViewById(R.id.parameter4);
        editText5 = view.findViewById(R.id.parameter5);

        sol11 = view.findViewById(R.id.solution11);
        sol12 = view.findViewById(R.id.solution12);
        sol21 = view.findViewById(R.id.solution21);
        sol22 = view.findViewById(R.id.solution22);
        sol31 = view.findViewById(R.id.solution31);
        sol32 = view.findViewById(R.id.solution32);
        sol41 = view.findViewById(R.id.solution41);
        sol42 = view.findViewById(R.id.solution42);


        calButton = view.findViewById(R.id.calButton);
        deleteB = view.findViewById(R.id.deleteB);
        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Double p1 = Double.parseDouble(editText1.getText().toString());
                Double p2 = Double.parseDouble(editText2.getText().toString());
                Double p3 = Double.parseDouble(editText3.getText().toString());
                Double p4 = Double.parseDouble(editText4.getText().toString());
                Double p5 = Double.parseDouble(editText5.getText().toString());

                double[] coefficients = {p5, p4, p3, p2, p1};
                PolynomialFunction polynomial = new PolynomialFunction(coefficients);

                // Sử dụng LaguerreSolver để tìm nghiệm
                LaguerreSolver solver = new LaguerreSolver();
                Complex[] roots = solver.solveAllComplex(polynomial.getCoefficients(), 0);

                String[] solutionStrings = new String[roots.length];
                DecimalFormat decimalFormat = new DecimalFormat("#.###"); // Định dạng số với 3 chữ số sau dấu thập phân

                for (int i = 0; i < roots.length; i++) {
                    double realPart = roots[i].getReal();
                    double imaginaryPart = roots[i].getImaginary();

                    // Định dạng và tạo chuỗi cho nghiệm
                    String formattedReal = decimalFormat.format(realPart);
                    String formattedImaginary = decimalFormat.format(imaginaryPart);

                    if (imaginaryPart >= 0) {
                        solutionStrings[i] = formattedReal + " + " + formattedImaginary + "i";
                    } else {
                        solutionStrings[i] = formattedReal + " - " + formattedImaginary.substring(1) + "i";
                    }
                }
                if (roots.length >= 1) {
                    sol11.setText(solutionStrings[0]);
                    sol12.setText("0");
                }
                if (roots.length >= 2) {
                    sol21.setText(solutionStrings[1]);
                    sol22.setText("0");
                }
                if (roots.length >= 3) {
                    sol31.setText(solutionStrings[2]);
                    sol32.setText("0");
                }
                if (roots.length >= 4) {
                    sol41.setText(solutionStrings[3]);
                    sol42.setText("0");
                }
            }
        });
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText5.setText("");
                sol11.setText("--");
                sol12.setText("--");
                sol21.setText("--");
                sol22.setText("--");
                sol31.setText("--");
                sol32.setText("--");
                sol41.setText("--");
                sol42.setText("--");
            }
        });
        return view;
    }
}
