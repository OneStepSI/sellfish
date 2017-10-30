package com.example.eldi.sellfish;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class PenjualActivity extends AppCompatActivity {
    public static final String FRAGMENT_VIEWPAGER = "FRAGMENT_VIEWPAGER";
    public static final String FRAGMENT_PERTAMA = "FRAGMENT_PERTAMA";
    public static final String FRAGMENT_KEDUA = "FRAGMENT_KEDUA";
    public static final String FRAGMENT_KETIGA = "FRAGMENT_KETIGA";
    public static final String FRAGMENT_KEEMPAT = "FRAGMENT_KEEMPAT";
    String username,email,id;
    //ArrayList dataToFragment = new ArrayList();



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home_penjual:

                    replaceFragment(Fragment_home_penjual.newInstance());

                    return true;
                case R.id.navigation_jualan_penjual:
                    replaceFragment(Fragment_jualan_penjual.newInstance());
                    return true;
                case R.id.navigation_transaksi_penjual:
                    replaceFragment(Fragment_transaksi_penjual.newInstance());
                    return true;
                case R.id.navigation_profil_penjual:
                    Bundle data = new Bundle();
                    data.putString("username", username);
                    data.putString("email", email);
                    data.putString("id",id);
                    Fragment profil = Fragment_profil_penjual.newInstance();
                    profil.setArguments(data);
                    replaceFragment(profil);
                    //replaceFragment(Fragment_profil_penjual.newInstance());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjual);


        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");
        id=getIntent().getStringExtra("user_id");


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, Fragment_home_penjual.newInstance(), FRAGMENT_PERTAMA)
                .commit();

    }

    private void replaceFragment(Fragment newFragment) {


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, newFragment)
                .commit();

    }


}
