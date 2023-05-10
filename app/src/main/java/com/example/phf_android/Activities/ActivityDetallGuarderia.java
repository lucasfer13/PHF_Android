package com.example.phf_android.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.phf_android.Adapters.AdapterRatings;
import com.example.phf_android.Adapters.ViewPagerAdapter;
import com.example.phf_android.Clases.Picture;
import com.example.phf_android.Clases.PicturesGuarderia;
import com.example.phf_android.FTPConexio.Connection;
import com.example.phf_android.FTPConexio.XMLReader;
import com.example.phf_android.Clases.Guarderia;
import com.example.phf_android.R;
import com.example.phf_android.SQL.ControlUsuario;


import org.apache.commons.net.ftp.FTPClient;

import java.io.File;

public class ActivityDetallGuarderia extends AppCompatActivity {
    Guarderia g;
    TextView titol;
    TextView desc;
    Button reserva;
    FTPClient ftp;
    Connection c;
    ViewPager vp;
    Bitmap[] images;
    RatingBar rating;
    RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_guarderia);
        init();
        setImages();
    }

    private void init() {
        g = getIntent().getParcelableExtra("GUARDERIA");
        titol = findViewById(R.id.lblDetallGuarderiaTitolGuarderia);
        desc = findViewById(R.id.lblDetallGuarderiaDesc);
        desc.setText(g.getDescripcio());
        titol.setText(g.getNom());
        c = new Connection();
        ftp = c.conect();
        vp = findViewById(R.id.vwpDetallGuarderiaImatges);
        rating = findViewById(R.id.rtbDetallGuarderiaRating);
        rating.setRating(Float.parseFloat(g.getValoracio()));
        rating.setEnabled(false);
        g.getRelations();
        rc = findViewById(R.id.rcvDetallGuarderiaValoracions);
        AdapterRatings adapter = new AdapterRatings(g.getRatings(), this);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);
        reserva = findViewById(R.id.btnDetallGuarderiaReserva);
    }

    private void getImages(File f, PicturesGuarderia pg) {
        images = new Bitmap[pg.getPictures().size()];
        int i = 0;
        for (Picture p : pg.getPictures()) {
            c.downloadFile(ftp, "/"+g.getIdGuarderia()+"/"+p.getName(), f.getPath()+"/"+p.getName());
            images[i]  = BitmapFactory.decodeFile(new File(f.getPath()+"/"+p.getName()).getAbsolutePath());
            i++;
        }
    }

    private void getImatgeLocal(File f) {
        File[] files = f.listFiles();
        images = new Bitmap[files.length-1];
        int i = 0;
        for (File tmp : files) {
            if (!tmp.getName().equals("version.xml")) {
                images[i] = BitmapFactory.decodeFile(tmp.getAbsolutePath());
                i++;
            }
        }
    }

    private void haveNew() {
        File f = new File(getFilesDir(), g.getIdGuarderia()+"");
        File tmp = new File(getFilesDir(), "tmp.xml");
        if (c.directoryExists(ftp, "/"+g.getIdGuarderia())) {
            XMLReader xml = new XMLReader();
            c.downloadFile(ftp, "/"+g.getIdGuarderia()+"/version.xml", tmp.getPath());
            PicturesGuarderia pgRemot = xml.xmlToGuarderia(tmp);
            if (f.exists()) {
                PicturesGuarderia pgLocal = xml.xmlToGuarderia(new File(f, "version.xml"));
                if (pgRemot.getData().compareTo(pgLocal.getData()) != 0) {
                    c.downloadFile(ftp, "/"+g.getIdGuarderia()+"/version.xml", f.getPath()+"/version.xml" +
                            "ion.xml");
                    deleteImages(f);
                    getImages(f, pgRemot);
                } else {
                    getImatgeLocal(f);
                }
            } else {
                f.mkdir();
                c.downloadFile(ftp, "/"+g.getIdGuarderia()+"/version.xml", f.getPath()+"/version.xml");
                getImages(f, pgRemot);

            }
        }
        c.disconect(ftp);
    }

    private void setImages() {
        new Thread(() -> {
            haveNew();
            if (images != null) {
                runOnUiThread(() -> {
                    ViewPagerAdapter adapter = new ViewPagerAdapter(ActivityDetallGuarderia.this, images);
                    vp.setAdapter(adapter);
                });
            }
        }).start();
    }

    private void deleteImages(File f) {
        File[] files = f.listFiles();
        for (File tmp : files) {
            if (!tmp.getName().equals("version.xml")) {
                f.delete();
            }
        }
    }

    private View.OnClickListener clickReserva() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                if (ControlUsuario.usuari != null) {
                    i = new Intent(ActivityDetallGuarderia.this, ActivityReservar.class);
                    i.putExtra("GUARDERIA", g);

                } else {
                    i = new Intent(ActivityDetallGuarderia.this, ActivityLogin.class);

                }
                startActivity(i);
            }
        };
    }
}