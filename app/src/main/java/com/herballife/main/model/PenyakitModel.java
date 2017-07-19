package com.herballife.main.model;

/**
 * Created by Achmad on 18/07/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class PenyakitModel {
    private static final String DB_NAME = "data2.sqlite";
    private static final String TABLE_NAME = "data_penyakit";
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE_QUERY = "CREATE TABLE " +
            PenyakitModel.TABLE_NAME + " (id integer primary key autoincrement nama text not null);";

    private final SQLiteDatabase database;

    private final SQLiteOpenHelper helper;

    public PenyakitModel(final Context ctx) {
        this.helper = new SQLiteOpenHelper(ctx, PenyakitModel.DB_NAME,
                null, PenyakitModel.DB_VERSION) {
            @Override
            public void onCreate(final SQLiteDatabase db) {
                db.execSQL(PenyakitModel.DB_CREATE_QUERY);
            }

            @Override
            public void onUpgrade(final SQLiteDatabase db,
                                  final int oldVersion, final int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + PenyakitModel.TABLE_NAME);
                this.onCreate(db);
            }
        };

        this.database = this.helper.getWritableDatabase();
    }

    public void addPenyakit(ContentValues data) {
        this.database.insert(PenyakitModel.TABLE_NAME, null, data);
    }

    public void deletePenyakit(final String field_params) {
        this.database.delete(PenyakitModel.TABLE_NAME, field_params, null);
    }

    public Cursor loadAllPenyakit() {
        final Cursor c = this.database.query(PenyakitModel.TABLE_NAME,
                new String[]{"nama"}, null, null, null, null, null);

        return c;
    }
    public Cursor loadOnePenyakit(String nama) {
        final Cursor c = this.database.query(PenyakitModel.TABLE_NAME,
                new String[]{"*"}, "nama = '"+nama+"'", null, null, null, null);

        return c;
    }
}

