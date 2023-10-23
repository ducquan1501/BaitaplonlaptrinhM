package com.example.projectmobile.library_resource;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectmobile.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Library_home extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_home, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.libray_bnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        return view;
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if(item.getItemId()==R.id.library_home){
            transaction.replace(R.id.library_frame, new Library_home()).commit();
        }
        else if (item.getItemId()==R.id.constNum){
            transaction.replace(R.id.library_frame, new const_Fragment()).commit();
        }
        else if (item.getItemId()==R.id.function){
            transaction.replace(R.id.library_frame, new func_fragment()).commit();
        }
        return true;
    }
}
