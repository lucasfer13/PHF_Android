package com.example.phf_android.Activities;

import static com.example.phf_android.SQL.Constants.AFEGIR_USUARI_REGISTRE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phf_android.SQL.Conexion;
import com.example.phf_android.R;
import com.example.phf_android.Clases.Usuari;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityRegister extends AppCompatActivity {
    Button registrar;
    EditText nom;
    EditText NomUsuari;
    EditText Cognoms;
    EditText Dni;
    EditText Correu;
    EditText Telefon;
    EditText Contrasenya;
    Usuari temp = new Usuari();
    String Cognoms1;
    Context context=this;
    ResultSet rs = null;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nom=findViewById(R.id.txtRegisterNom);
        NomUsuari=findViewById(R.id.txtRegisterUsuari);
        Cognoms=findViewById(R.id.txtRegisterConoms);
        Dni=findViewById(R.id.txtRegisterDni);
        Correu=findViewById(R.id.txtRegisterCorreu);
        Telefon=findViewById(R.id.txtRegisterTelefon);
        Contrasenya=findViewById(R.id.txtRegisterPassword);
        registrar=findViewById(R.id.btnRegistrarRegistrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp.setNom(String.valueOf(nom.getText()));
                temp.setNomUsuari(String.valueOf(NomUsuari.getText()));
                temp.setCognoms(String.valueOf(Cognoms.getText()));
                temp.setDni(String.valueOf(Dni.getText()));
                temp.setCorreu(String.valueOf(Correu.getText()));
                temp.setContrasenya(String.valueOf(Contrasenya.getText()));
                String[] AllCognoms=temp.getCognoms().split(" ");
                String Cognom1=AllCognoms[0];
                String Cognom2=AllCognoms[1];
                MessageDigest digest;
                String sha1 = "";
                try {
                    digest = MessageDigest.getInstance("SHA-1");
                    digest.reset();
                    digest.update(temp.getContrasenya().getBytes("utf8"));
                    sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    Conexion.update(String.format(AFEGIR_USUARI_REGISTRE , temp.getDni(), temp.getNom(), Cognom1, Cognom2, temp.getNomUsuari(), sha1, temp.getTelefon(), temp.getCorreu()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                rs = Conexion.query(String.format(AFEGIR_USUARI_REGISTRE , temp.getDni(), temp.getNom(), Cognom1, Cognom2, temp.getNomUsuari(), sha1, temp.getCorreu()));
                Conexion.desconectar();
                Toast.makeText(getApplicationContext(), "S'ha completat el registre", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}