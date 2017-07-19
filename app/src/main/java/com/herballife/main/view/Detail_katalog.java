package com.herballife.main.view;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.herballife.main.R;
import com.herballife.main.controller.List_catalog;
import com.herballife.main.util.SQLiteDBHelper;

import java.util.ArrayList;

public class Detail_katalog extends Activity
{
	public static SQLiteDatabase db;
    SQLiteDBHelper helper;
	TextView nama;
	TextView guna;
	ImageView gambar;
	String cek;
    private List_catalog controller;
	protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_katalog);
        Intent qwe = getIntent();
		cek = qwe.getStringExtra("kirim_tumbuhan");
        this.controller = new List_catalog(this);
        /*try
        {
            helper = new SQLiteDBHelper(this);
        	db = helper.getDb();
        	
        }
        catch (SQLException e)
        {
        	Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }*/
       Cursor c = this.controller.getOneKatalogs(cek);
       c.moveToFirst();
       nama = (TextView)findViewById(R.id.nama_tumbuhan);
       guna = (TextView)findViewById(R.id.kegunaan);
       gambar = (ImageView)findViewById(R.id.gambar);
       nama.setText(cek);
       int index1 = c.getColumnIndex("Kegunaan");
       int index3 = c.getColumnIndex("Gambar");
       byte[] image_byte = c.getBlob(index3); 
       Bitmap image = BitmapFactory.decodeByteArray(image_byte, 0, image_byte.length);
	   gambar.setImageBitmap(image);
       String use = c.getString(index1);
       guna.setText("Kegunaan : "+use);
	}
}