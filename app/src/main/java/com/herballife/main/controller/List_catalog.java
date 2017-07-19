package com.herballife.main.controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.herballife.main.model.KatalogModel;
import com.herballife.main.util.SQLiteDBHelper;

import java.util.ArrayList;
import java.util.List;

//ini adalah controller untuk catalog
public class List_catalog {
    SQLiteDBHelper helper;
    public static SQLiteDatabase db;
    ArrayList<String> listitem1;
    private KatalogModel model;

    public List_catalog(Context app_context) {
        listitem1 = new ArrayList<String>();
        model = new KatalogModel(app_context);
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

    public Cursor getOneKatalogs(String nama) {
        Cursor c = model.loadOneKatalog(nama);

        return c;
    }

}