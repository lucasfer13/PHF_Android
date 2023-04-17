package com.example.phf_android;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String CONTROLADOR = "org.gjt.mm.mysql.Driver";
    private static final String URL = "jdbc:mysql://192.168.1.150:25230/pethotelDB";
    private static final String USUARIO="pethotel";
    private static final String PASSW="ElLuAlBe???!19876";

    static Connection conexion = null;
    public static Connection conectar() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conexion = null;
        try {
            Class.forName(CONTROLADOR);
            conexion = DriverManager.getConnection(URL,USUARIO,PASSW);
            Log.d("Connexion","Conexion ok");

        }catch(Exception e) {
            Log.d("Connexion", "Error en la conexion");
            e.printStackTrace();
        }
        return conexion;
    }
}
