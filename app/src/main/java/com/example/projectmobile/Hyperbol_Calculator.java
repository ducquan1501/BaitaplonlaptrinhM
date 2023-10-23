package com.example.projectmobile;

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

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

import java.text.DecimalFormat;

public class Hyperbol_Calculator extends Fragment {
    EditText editText1, editText2, editText3, editText4, editText5, editText6;
    TextView sol11, sol12, sol21, sol22, tcn, tcd1, tcd2;
    Button calculatorB, deleteB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hyperbol_calculator, container, false);

        editText1 = view.findViewById(R.id.parameter1);
        editText2 = view.findViewById(R.id.parameter2);
        editText3 = view.findViewById(R.id.parameter3);
        editText4 = view.findViewById(R.id.parameter4);
        editText5 = view.findViewById(R.id.parameter5);
        editText6 = view.findViewById(R.id.parameter6);

        sol11 = view.findViewById(R.id.solution11);
        sol12 = view.findViewById(R.id.solution12);
        sol21 = view.findViewById(R.id.solution21);
        sol22 = view.findViewById(R.id.solution22);
        tcn = view.findViewById(R.id.tcn);
        tcd1 = view.findViewById(R.id.tcd1);
        tcd2 = view.findViewById(R.id.tcd2);

        calculatorB = view.findViewById(R.id.calculatorB);
        deleteB = view.findViewById(R.id.deleteB);

        calculatorB.setOnClickListener(new View.OnClickListener() {
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
                String et6 = editText6.getText().toString();
                if (et6.isEmpty() || et6.equals("-") || et6.equals(".")) {
                    editText6.setText("0");
                }
                if (et4.isEmpty() && et5.isEmpty() && et6.isEmpty()) {
                    editText6.setText("1");
                }
                double p1 = Double.parseDouble(editText1.getText().toString());
                double p2 = Double.parseDouble(editText2.getText().toString());
                double p3 = Double.parseDouble(editText3.getText().toString());
                double p4 = Double.parseDouble(editText4.getText().toString());
                double p5 = Double.parseDouble(editText5.getText().toString());
                double p6 = Double.parseDouble(editText6.getText().toString());

                if (p1 == 0 && p2 == 0) {
                    sol11.setText("--");
                    sol12.setText("--");
                    sol21.setText("--");
                    sol22.setText("--");
                    tcn.setText("y = 0");
                    double[] coefficients2 = {p6, p5, p4};
                    PolynomialFunction polynomial2 = new PolynomialFunction(coefficients2);
                    // Sử dụng LaguerreSolver để tìm nghiệm
                    LaguerreSolver solver = new LaguerreSolver();

                    Complex[] roots2 = solver.solveAllComplex(polynomial2.getCoefficients(), 0);

                    DecimalFormat decimalFormat = new DecimalFormat("#.###"); // Định dạng số với 3 chữ số sau dấu thập phân
                    String[] solutionStrings2 = new String[roots2.length];
                    for (int i = 0; i < roots2.length; i++) {
                        double realPart = roots2[i].getReal();
                        double imaginaryPart = roots2[i].getImaginary();

                        // Định dạng và tạo chuỗi cho nghiệm
                        String formattedReal = decimalFormat.format(realPart);
                        String formattedImaginary = decimalFormat.format(imaginaryPart);

                        if (imaginaryPart != 0) {
                            solutionStrings2[i] = "--";
                        } else {
                            solutionStrings2[i] = formattedReal;
                        }
                    }
                    if (roots2.length >= 1) {
                        tcd1.setText("x = "+solutionStrings2[0]);
                    }
                    if (roots2.length >= 2) {
                        tcd2.setText("x = "+solutionStrings2[1]);
                    }
                } else if (p4 == 0 && p5 == 0) {
                    p3 = p3 / p6;
                    p2 = p2 / p6;
                    p1 = p1 / p6;
                    double[] coefficients = {p3, p2, p1};
                    PolynomialFunction polynomial = new PolynomialFunction(coefficients);
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

                        if (imaginaryPart != 0) {
                            solutionStrings[i] = "--";
                        } else {
                            solutionStrings[i] = formattedReal;
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
                } else {
                    double[] coefficients = {p3, p2, p1};
                    PolynomialFunction polynomial = new PolynomialFunction(coefficients);

                    double[] coefficients2 = {p6, p5, p4};
                    PolynomialFunction polynomial2 = new PolynomialFunction(coefficients2);
                    // Sử dụng LaguerreSolver để tìm nghiệm
                    LaguerreSolver solver = new LaguerreSolver();

                    Complex[] roots = solver.solveAllComplex(polynomial.getCoefficients(), 0);
                    Complex[] roots2 = solver.solveAllComplex(polynomial2.getCoefficients(), 0);

                    String[] solutionStrings = new String[roots.length];
                    DecimalFormat decimalFormat = new DecimalFormat("#.###"); // Định dạng số với 3 chữ số sau dấu thập phân
                    for (int i = 0; i < roots.length; i++) {
                        double realPart = roots[i].getReal();
                        double imaginaryPart = roots[i].getImaginary();

                        // Định dạng và tạo chuỗi cho nghiệm
                        String formattedReal = decimalFormat.format(realPart);
                        String formattedImaginary = decimalFormat.format(imaginaryPart);

                        if (imaginaryPart != 0) {
                            solutionStrings[i] = "--";
                        } else {
                            solutionStrings[i] = formattedReal;
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
                    String[] solutionStrings2 = new String[roots2.length];
                    for (int i = 0; i < roots2.length; i++) {
                        double realPart = roots2[i].getReal();
                        double imaginaryPart = roots2[i].getImaginary();

                        // Định dạng và tạo chuỗi cho nghiệm
                        String formattedReal = decimalFormat.format(realPart);
                        String formattedImaginary = decimalFormat.format(imaginaryPart);

                        if (imaginaryPart != 0) {
                            solutionStrings2[i] = "--";
                        } else {
                            solutionStrings2[i] = formattedReal;
                        }
                    }
                    if (roots.length >= 1) {
                        tcd1.setText("x = "+solutionStrings2[0]);
                    }
                    if (roots.length >= 2) {
                        tcd2.setText("x = "+solutionStrings2[1]);
                    }
                    if(p1!=0 && p4 != 0){
                        tcn.setText("y = "+p1/p4);
                    }else if(p1!=0 && p4 == 0){
                        tcn.setText("y = "+p1/p5+" x");
                    }else if(p1==0&&p4==0&&p5!=0){
                        tcn.setText("y = "+p2/p5);
                    }else{
                        tcn.setText("--");
                    }
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
                editText6.setText("");
                sol11.setText("--");
                sol12.setText("--");
                sol21.setText("--");
                sol22.setText("--");
                tcn.setText("--");
                tcd1.setText("--");
                tcd2.setText("--");
            }
        });
        return view;
    }
}
