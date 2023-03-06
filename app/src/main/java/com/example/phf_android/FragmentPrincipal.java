package com.example.phf_android;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class FragmentPrincipal extends Fragment {


    public FragmentPrincipal() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentPrincipal newInstance(String param1, String param2) {
        FragmentPrincipal fragment = new FragmentPrincipal();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        ImageButton btnLanzarActivity = (ImageButton) view.findViewById(R.id.btnFragmentUsuari);
        btnLanzarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ActivityLogin.class);
                startActivity(intent);
            }
        });
        return view;
    }
}