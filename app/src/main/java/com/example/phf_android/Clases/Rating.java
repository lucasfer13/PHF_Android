package com.example.phf_android.Clases;

import com.example.phf_android.SQL.Conexion;
import com.example.phf_android.SQL.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Rating {
    private String user;
    private float rating;

    public Rating(String user, float rating) {
        this.user = user;
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public static ArrayList<Rating> getRatingGuarderia(int idGuarderia) {
        ArrayList<Rating> ratings = new ArrayList<>();
        Conexion.conectar();
        ResultSet rs = Conexion.query(String.format(Constants.CERCAR_RATINGS_BY_IDGUARDERIA, idGuarderia));
        putResult(rs, ratings);
        Conexion.desconectar();
        return ratings;
    }

    public static void putResult(ResultSet rs, ArrayList<Rating> ratings) {
            try {
                while (rs.next()) {
                    ratings.add(new Rating(rs.getString(1), rs.getInt(2)));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
