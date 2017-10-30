package com.example.eldi.sellfish;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by eldi on 18/10/2017.
 */
public class Fragment_jualan_penjual extends Fragment {
    public Fragment_jualan_penjual() {

    }

    public static Fragment_jualan_penjual newInstance() {
        Fragment_jualan_penjual fragment = new Fragment_jualan_penjual();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jualan_penjual, container, false);
    }
}

