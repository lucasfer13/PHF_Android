package com.example.phf_android.SQL;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.BlockingDeque;

public class Conexion {
    private static final String CONTROLADOR = "org.gjt.mm.mysql.Driver";
    private static final String URL = "jdbc:mysql://webapps.insjoanbrudieu.cat:25230/pethotelDB";
    private static final String URL_LOCAL = "jdbc:mysql://192.168.1.150:25230/pethotelDB";
    private static final String USUARIO="pethotel";
    private static final String PASSW="ElLuAlBe???!19876";

    static Connection conexion = null;
    static Conexion conexio =new Conexion();

    static Connection cn = null;
    static Statement stm = null;
    static ResultSet rs = null;

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

    public static void desconectar(){
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

    public static ResultSet query(String constant) {
        cn = conexio.conectar();
        try {
            stm = cn.createStatement();
            rs = stm.executeQuery(constant);
        } catch (Exception e) {
            Log.d("QUERY", e.getMessage());
        }
        return rs;
    }

    public static int insertID(String insert) {
        int id = -1;
        cn = conexio.conectar();
        try {
            stm = cn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stm.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            desconectar();
        } catch (Exception e) {
            Log.d("INSERT", e.getMessage());
        }
        return id;
    }

    public static void update(String constant) {
        try {
            cn = conexio.conectar();
            stm = cn.createStatement();
            stm.executeUpdate(constant);
        } catch (Exception e) {
            Log.d("COMANDA", e.getMessage());
        }
    }
}
