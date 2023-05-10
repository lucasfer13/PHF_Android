package com.example.phf_android.Activities;

import static com.example.phf_android.SQL.Constants.AFEGIR_USUARI_REGISTRE;
import static com.example.phf_android.SQL.Constants.MODIFICAR_CONTRASENYA;

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
import com.example.phf_android.SQL.controlUsuario;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class ActivityCanviarContrasenya extends AppCompatActivity {
    Button guardar;
    EditText ContrasenyaAnterior;
    EditText ContrasenyaNew;
    EditText ContrasenyaRepeat;
    Usuari temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canviar_contrasenya);
        guardar=findViewById(R.id.btnCanviarContrasenyaGuardar);
        temp = controlUsuario.usuari;
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContrasenyaAnterior=findViewById(R.id.txtCanviarContrasenyaAnterior);
                ContrasenyaNew=findViewById(R.id.txtCanviarContrasenyaNova);
                ContrasenyaRepeat=findViewById(R.id.txtCanviarContrasenyaRepeteix);
                String contraAnt = String.valueOf(ContrasenyaAnterior.getText());
                String contraNew = String.valueOf(ContrasenyaNew.getText());
                String contraRepeat = String.valueOf(ContrasenyaRepeat.getText());
                if(contraRepeat.equals(contraNew)){
                    MessageDigest digest;
                    MessageDigest digest1;
                    try {
                        digest = MessageDigest.getInstance("SHA-1");
                        digest.reset();
                        digest.update(contraNew.getBytes("utf8"));
                        contraNew = String.format("%040x", new BigInteger(1, digest.digest()));
                        digest1 = MessageDigest.getInstance("SHA-1");
                        digest1.reset();
                        digest1.update(contraAnt.getBytes("utf8"));
                        contraAnt = String.format("%040x", new BigInteger(1, digest1.digest()));
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (contraAnt.equals(temp.getContrasenya())){
                        try {
                            Conexion.update(String.format(MODIFICAR_CONTRASENYA , contraNew, temp.getId()));
                            Intent intent = new Intent (ActivityCanviarContrasenya.this, ActivityConfiguracioPerfil.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "S'ha canviat la contrasenya correctament.", Toast.LENGTH_LONG).show();
                        } catch (SQLException e) {
                            Toast.makeText(getApplicationContext(), "No s'ha pogut canviar la contrasenya.", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "La contrasenya es incorrecta.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Les contrasenyes no coincideixen.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}