package com.herballife.main.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.herballife.main.model.KatalogModel;
import com.herballife.main.model.PenyakitModel;
import com.herballife.main.util.SQLiteDBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achmad on 19/07/2017.
 */

public class PenyakitController {
        SQLiteDBHelper helper;
        public static SQLiteDatabase db;
        ArrayList<String> listitem1;
        private PenyakitModel model;

        public PenyakitController(Context app_context) {
            listitem1 = new ArrayList<String>();
            model = new PenyakitModel(app_context);
        }

        public void addPenyakit(final String title) {
            final ContentValues data = new ContentValues();
            data.put("nama", title);
            model.addPenyakit(data);
        }

        public void deletePenyakit(final String title) {
            model.deletePenyakit("nama='" + title + "'");
        }

        public void deletePenyakit(final long id) {
            model.deletePenyakit("nama='" + id + "'");
        }

        public void deleteAllPenyakit() {
            model.deletePenyakit(null);
        }


        //mendapatkan data katalog dari model dan memasukannya kedalam List String
        public List<String> getKatalogs() {
            Cursor c = model.loadAllPenyakit();
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
            Cursor c = model.loadOnePenyakit(nama);

            return c;
        }
}
