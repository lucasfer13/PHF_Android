package com.example.phf_android.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.phf_android.R;
import com.example.phf_android.SQL.ControlUsuario;

public class FragmentPrincipal extends Fragment {
    Button btnFracmentReserva;

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
        //btnFracmentReserva = btnFracmentReserva.findViewById(R.id.btnFracmentReserva);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        ImageButton btnLanzarActivity = (ImageButton) view.findViewById(R.id.btnFragmentUsuari);
        btnLanzarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (ControlUsuario.usuari != null) intent = new Intent(getActivity(), ActivityConfiguracio.class);
                else intent = new Intent(v.getContext(), ActivityLogin.class);
                startActivity(intent);
            }

        });
        Button buscar = view.findViewById(R.id.btnFragmentPrincipalCercar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityBusqueda.class);
                startActivity(intent);
            }
        });
        Button btnFracmentReserva = (Button) view.findViewById(R.id.btnFracmentReserva);
        btnFracmentReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (ControlUsuario.usuari != null) intent = new Intent(getActivity(), ActivityReserva.class);
                else intent = new Intent(v.getContext(), ActivityLogin.class);
                startActivity(intent);
            }
        });
        Button mascotes = view.findViewById(R.id.btnFracmentMascotes);
        mascotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (ControlUsuario.usuari != null) intent = new Intent (v.getContext(), ActivityMascotes.class);
                else intent = new Intent(v.getContext(), ActivityLogin.class);
                startActivity(intent);
            }
        });
        return view;
    }
}