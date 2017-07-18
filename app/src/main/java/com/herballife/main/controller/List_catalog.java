package com.herballife.main.controller;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.herballife.main.model.MvCModel;
import com.herballife.main.util.SQLiteDBHelper;

import java.util.ArrayList;
import java.util.List;

//ini adalah controller untuk catalog
public class List_catalog {
    SQLiteDBHelper helper;
    public static SQLiteDatabase db;
    ArrayList<String> listitem1;
    private MvCModel model;

    public List_catalog(Context app_context) {
        listitem1 = new ArrayList<String>();
        model = new MvCModel(app_context);
    }

    public void addKatalog(final String title) {
        final ContentValues data = new ContentValues();
        data.put("nama", title);
        model.addKatalog(data);
    }

    public void deleteKatalog(final String title) {
        model.deleteKatalog("nama='" + title + "'");
    }

    public void deleteKatalog(final long id) {
        model.deleteKatalog("nama='" + id + "'");
    }

    public void deleteAllKatalog() {
        model.deleteKatalog(null);
    }


    //mendapatkan data katalog dari model dan memasukannya kedalam List String
    public List<String> getKatalogs() {
        Cursor c = model.loadAllKatalog();
        listitem1.clear();
        if (c != null) {
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                listitem1.add(c.getString(0));
                c.moveToNext();
            }
            c.close();
        }
        return listitem1;
    }

    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_katalog);
        try {
            helper = new SQLiteDBHelper(this);
            db = helper.getDb();
            String sql = "select * from Katalog";
            Cursor c = db.rawQuery(sql, null);
            c.moveToFirst();


            int i = 0;
            while (!c.isAfterLast()) {
                int index = c.getColumnIndex("Nama");
                String temp = c.getString(index);
                listitem1.add(temp);

                i++;
                c.moveToNext();
            }


            list = (ListView) findViewById(R.id.list_tumbuhan);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listitem1);

            list.setAdapter(adapter);
            list.setOnItemClickListener(new OnItemClickListener() {


                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    checked = arg2;
                    String tumbuhan = listitem1.get(checked);

                    Intent tambah = new Intent(List_catalog.this, Detail_katalog.class);
                    tambah.putExtra("kirim_tumbuhan", tumbuhan);
                    startActivity(tambah);
                    //Toast.makeText(getApplicationContext(),"anda mengklick = " +listitem1.get(checked).toString(),Toast.LENGTH_SHORT).show();

                }

            });

        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }*/
}