package com.example.phf_android.Activities;

import static com.example.phf_android.SQL.Constants.FER_LOGIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phf_android.SQL.Conexion;
import com.example.phf_android.R;
import com.example.phf_android.Clases.Usuari;
import com.example.phf_android.SQL.ControlUsuario;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityLogin extends AppCompatActivity {
    EditText NomUsuari;
    EditText Contrasenya;
    Usuari temp = new Usuari();
    ResultSet rs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        NomUsuari=findViewById(R.id.txtLoginEmail);
        Contrasenya=findViewById(R.id.txtLoginContrasenya);
        Button reg = findViewById(R.id.btnLoginRegistrar);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityRegister.class);
                startActivity(intent);
            }
        });

        Button in = findViewById(R.id.btnLoginEntrar);
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp.setNomUsuari(String.valueOf(NomUsuari.getText()));
                String contra = String.valueOf(Contrasenya.getText());
                MessageDigest digest;
                try {
                    digest = MessageDigest.getInstance("SHA-1");
                    digest.reset();
                    digest.update(contra.getBytes("utf8"));
                    contra = String.format("%040x", new BigInteger(1, digest.digest()));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    rs = Conexion.query(String.format(FER_LOGIN , temp.getNomUsuari()));
                    while(rs.next()) {
                        temp.setId(rs.getInt(1));
                        temp.setDni(rs.getString(2));
                        temp.setNom(rs.getString(3));
                        temp.setCognoms(rs.getString(4));
                        temp.setContrasenya(rs.getString(7));
                        temp.setTelefon(rs.getString(8));
                        temp.setCorreu(rs.getString(9));
                        temp.setActiu(rs.getInt(10));
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if(contra.equals(temp.getContrasenya())&&temp.getActiu()==1){
                    ControlUsuario.usuari = temp;
                    Intent intent = new Intent (v.getContext(), ActivityPrincipal.class);
                    startActivity(intent);
                } else {

                }
            }
        });
    }
}