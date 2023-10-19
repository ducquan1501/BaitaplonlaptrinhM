package com.example.projectmobile;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projectmobile.library_resource.Library_home;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public ActionBarDrawerToggle toggle;
    public DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Đồ thị hàm cơ bản");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Basic_Graph())
                    .commit();
            navigationView.setCheckedItem(R.id.basic_graph);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.basic_graph) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Basic_Graph())
                    .commit();
            getSupportActionBar().setTitle("Đồ thị hàm đa thức");
        } else if (item.getItemId() == R.id.hyperbol_graph) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Hyperbol_Graph())
                    .commit();
            getSupportActionBar().setTitle("Đồ thị Hyperbol");
        } else if (item.getItemId() == R.id.ellipse_graph) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Ellipse_graph())
                    .commit();
            getSupportActionBar().setTitle("Đồ thị đường tròn & ellipse");
        } else if (item.getItemId() == R.id.logarithmic_graph) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Logarithm_graph())
                    .commit();
            getSupportActionBar().setTitle("Đồ thị Logarith");
        } else if (item.getItemId() == R.id.trigo_graph) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Trigo_graph())
                    .commit();
            getSupportActionBar().setTitle("Đồ thị lượng giác");
        } else if (item.getItemId() == R.id.library) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Library_home())
                    .commit();
            getSupportActionBar().setTitle("Thư viện");
        }else if (item.getItemId() == R.id.generality_graph){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Generality_graph())
                    .commit();
            getSupportActionBar().setTitle("Đồ thị tổng quát");
        }
            drawerLayout.closeDrawer(GravityCompat.START, true);
            return true;
        }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }
}