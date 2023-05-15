package com.example.phf_android.Activities;

import static com.example.phf_android.SQL.Constants.AFEGIR_USUARI_REGISTRE;
import static com.example.phf_android.SQL.Constants.MODIFICAR_USUARI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phf_android.Clases.Usuari;
import com.example.phf_android.R;
import com.example.phf_android.SQL.Conexion;
import com.example.phf_android.SQL.ControlUsuario;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class ActivityConfiguracioPerfil extends AppCompatActivity {
    Button guardar;
    Button canviaContrasenya;
    EditText editText;
    EditText nom;
    EditText NomUsuari;
    EditText Cognoms;
    EditText Dni;
    EditText Correu;
    EditText Telefon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracio_perfil);
        nom=findViewById(R.id.txtConfiguracioPerfilNom);
        NomUsuari=findViewById(R.id.txtConfiguracioPerfilUsuari);
        Cognoms=findViewById(R.id.txtConfiguracioPerfilConoms);
        Dni=findViewById(R.id.txtConfiguracioPerfilDni);
        Correu=findViewById(R.id.txtConfiguracioPerfilCorreu);

        Usuari temp = ControlUsuario.usuari;

        NomUsuari.setText(temp.getNomUsuari());
        nom.setText(temp.getNom());
        Cognoms.setText(temp.getCognoms());
        Dni.setText(temp.getDni());
        Correu.setText(temp.getCorreu());

        guardar=findViewById(R.id.btnConfiguracioPerfilGuardar);
        canviaContrasenya=findViewById(R.id.btnConfiguracioPerfilCanviarContrasenya);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp.setNom(String.valueOf(nom.getText()));
                temp.setNomUsuari(String.valueOf(NomUsuari.getText()));
                temp.setCognoms(String.valueOf(Cognoms.getText()));
                temp.setDni(String.valueOf(Dni.getText()));
                temp.setCorreu(String.valueOf(Correu.getText()));
                String[] AllCognoms = temp.getCognoms().split(" ");
                String Cognom1 = AllCognoms[0];
                String Cognom2 = "";
                if (AllCognoms.length >= 2) {
                    Cognom2 = AllCognoms[1];
                }
                Conexion.update(String.format(MODIFICAR_USUARI, temp.getDni(), temp.getNom(), Cognom1, Cognom2, temp.getNomUsuari(), temp.getCorreu(), temp.getId()));
                Toast.makeText(getApplicationContext(), "S'ha modificat el registre", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent (ActivityConfiguracioPerfil.this, ActivityConfiguracio.class);
                //startActivity(intent);
            }
        });
        canviaContrasenya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityConfiguracioPerfil.this, ActivityCanviarContrasenya.class);
                startActivity(intent);
            }
        });
    }
}