package com.herballife.main.view;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.herballife.main.R;
import com.herballife.main.controller.PenyakitController;
import com.herballife.main.util.SQLiteDBHelper;

/**
 * Created by Achmad on 19/07/2017.
 */

public class DetailView extends Activity implements View.OnClickListener {
    public static SQLiteDatabase db;
    SQLiteDBHelper helper;
    TextView nama;
    TextView bahan;
    TextView tutor;
    Button button;
    String cek;
    private PenyakitController controller;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_penyakit);
        this.controller = new PenyakitController(this);
        Intent qwe = getIntent();
        cek = qwe.getStringExtra("kirim_penyakit");
        Cursor c = this.controller.getOnePenyakits(cek);
        c.moveToFirst();

        nama = (TextView) findViewById(R.id.daftar_penyakit);
        bahan = (TextView) findViewById(R.id.bahan);
        button = (Button) findViewById(R.id.button1);
        tutor = (TextView) findViewById(R.id.tutorial);
        nama.setText(cek);
        int index1 = c.getColumnIndex("BahanObat");
        int index2 = c.getColumnIndex("Tutorial");
        String alat = c.getString(index1);
        String tutorial = c.getString(index2);
        bahan.setText("Bahan : " + alat);
        tutor.setText("Cara Menggunakan : " + tutorial);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        Intent tombol = new Intent(this, KatalogView.class);
        startActivity(tombol);
    }
}
