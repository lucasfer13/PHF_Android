package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class ActivityRegister extends AppCompatActivity {
    Button registrar;
    EditText nom;
    EditText NomUsuari;
    EditText Cognoms;
    EditText Dni;
    EditText Correu;
    EditText Contrasenya;
    String Nom1;
    String NomUsuari1;
    String Cognoms1;
    String Dni1;
    String Correu1;
    String Contrasenya1;
    Context context=this;
    Conexion conexio =new Conexion();
    Connection cn = null;
    Statement stm = null;
    ResultSet rs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nom=findViewById(R.id.txtRegisterNom);
        NomUsuari=findViewById(R.id.txtRegisterUsuari);
        Cognoms=findViewById(R.id.txtRegisterConoms);
        Dni=findViewById(R.id.txtRegisterDni);
        Correu=findViewById(R.id.txtRegisterCorreu);
        Contrasenya=findViewById(R.id.txtRegisterPassword);
        registrar=findViewById(R.id.btnRegistrarRegistrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Nom1= String.valueOf(nom.getText());//
                    NomUsuari1= String.valueOf(NomUsuari.getText());//
                    Cognoms1= String.valueOf(Cognoms.getText());//
                    Dni1= String.valueOf(Dni.getText());//
                    Correu1= String.valueOf(Correu.getText());
                    Contrasenya1= String.valueOf(Contrasenya.getText());//
                    String[] AllCognoms=Cognoms1.split(" ");
                    String Cognom1=AllCognoms[0];
                    String Cognom2=AllCognoms[1];
                    MessageDigest digest;
                    String sha1 = "";
                    try {
                        digest = MessageDigest.getInstance("SHA-1");
                        digest.reset();
                        digest.update(Contrasenya1.getBytes("utf8"));
                        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    cn=conexio.conectar();
                    stm = cn.createStatement();
                    rs = stm.executeQuery("INSERT INTO `usuaris` (`documentIdentitat`, `nom`, `cognom1`, `cognom2`, `nomUsuari`, `contrasenya`, `correu`) VALUES ("+Dni1+", "+Nom1+", "+Cognom1+", "+Cognom2+", "+NomUsuari1+", "+sha1+", "+Correu1+");");
                }catch(SQLException e) {
                    e.printStackTrace();
                    Log.d("Connexion","Conexion fail");
                }finally {
                    try {
                        if (rs!=null) {
                            rs.close();
                        }
                        if (stm!=null) {
                            stm.close();
                        }
                        if (rs!=null) {
                            cn.close();
                        }
                    }catch(Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }
}