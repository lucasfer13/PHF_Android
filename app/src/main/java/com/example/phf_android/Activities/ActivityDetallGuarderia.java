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
import com.example.phf_android.Adapters.AdapterServeis;
import com.example.phf_android.Adapters.ViewPagerAdapter;
import com.example.phf_android.Clases.Picture;
import com.example.phf_android.Clases.PicturesGuarderia;
import com.example.phf_android.FTPConexio.Connection;
import com.example.phf_android.FTPConexio.XMLReader;
import com.example.phf_android.Clases.Guarderia;
import com.example.phf_android.R;
import com.example.phf_android.SQL.ControlUsuario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ActivityDetallGuarderia extends AppCompatActivity {
    Guarderia g;
    TextView titol;
    TextView desc;
    Button reserva;
    ViewPager vp;
    Bitmap[] images;
    RatingBar rating;
    RecyclerView rc;
    RecyclerView serveis;

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
        vp = findViewById(R.id.vwpDetallGuarderiaImatges);
        rating = findViewById(R.id.rtbDetallGuarderiaRating);
        rating.setRating(Float.parseFloat(g.getValoracio()));
        rating.setEnabled(false);
        g.getRelations();

        rc = findViewById(R.id.rcvDetallGuarderiaValoracions);
        serveis = findViewById(R.id.rcvDetallGuarderiaServeis);
        AdapterServeis adapterServeis = new AdapterServeis(g.getServeis(), this);
        serveis.setHasFixedSize(true);
        serveis.setLayoutManager(new LinearLayoutManager(this));
        serveis.setAdapter(adapterServeis);
        AdapterRatings adapter = new AdapterRatings(g.getRatings(), this);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);
        reserva = findViewById(R.id.btnDetallGuarderiaReserva);
        reserva.setOnClickListener(clickReserva());
    }

    private void getImages(File f, PicturesGuarderia pg) {
        images = new Bitmap[pg.getPictures().size()];
        int i = 0;
        for (Picture p : pg.getPictures()) {
            downloadFile("http://pethotelfinder.000webhostapp.com/"+g.getIdGuarderia()+"/"+p.getName(), new File(f, p.getName()));
            images[i] = BitmapFactory.decodeFile(new File(f.getPath()+"/"+p.getName()).getAbsolutePath());
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

    private boolean xmlExists() {
        try {
            URL url =  new URL("http://pethotelfinder.000webhostapp.com/"+g.getIdGuarderia()+"/"+"version.xml");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setConnectTimeout(500);
            http.connect();
            int code = http.getResponseCode();
            if (code == HttpURLConnection.HTTP_NOT_FOUND) {
                return false;
            }
            http.disconnect();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void downloadFile(String path, File f) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setConnectTimeout(500);
            http.connect();
            FileOutputStream fos = new FileOutputStream(f);
            InputStream is = http.getInputStream();
            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ((bufferLength = is.read(buffer)) > 0) {
                fos.write(buffer, 0, bufferLength);
            }
            is.close();
            fos.flush();
            fos.close();
            http.disconnect();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void haveNew() {
        File f = new File(getFilesDir(), g.getIdGuarderia()+"");
        File tmp = new File(getFilesDir(), "tmp.xml");
        if (xmlExists()) {
            XMLReader xml = new XMLReader();
            downloadFile("http://pethotelfinder.000webhostapp.com/"+g.getIdGuarderia()+"/"+"version.xml", tmp);
            PicturesGuarderia pgRemot = xml.xmlToGuarderia(tmp);
            if (f.exists()) {
                PicturesGuarderia pgLocal = xml.xmlToGuarderia(new File(f, "version.xml"));
                if (pgRemot.getData().compareTo(pgLocal.getData()) != 0) {
                    downloadFile("http://pethotelfinder.000webhostapp.com/"+g.getIdGuarderia()+"/"+"version.xml", new File(f, "version.xml"));
                    deleteImages(f);
                    getImages(f, pgRemot);
                } else {
                    getImatgeLocal(f);
                }
            } else {
                f.mkdir();
                downloadFile("http://pethotelfinder.000webhostapp.com/"+g.getIdGuarderia()+"/"+"version.xml", new File(f, "version.xml"));
                getImages(f, pgRemot);

            }
        }
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