package com.herballife.main.model;

/**
 * Created by Achmad on 18/07/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class KatalogModel {
    private static final String DB_NAME = "data2.sqlite";
    private static final String TABLE_NAME = "katalog";
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE_QUERY = "CREATE TABLE " +
            KatalogModel.TABLE_NAME + " (id integer primary key autoincrement nama text not null);";

    private final SQLiteDatabase database;

    private final SQLiteOpenHelper helper;

    public KatalogModel(final Context ctx) {
        this.helper = new SQLiteOpenHelper(ctx, KatalogModel.DB_NAME,
                null, KatalogModel.DB_VERSION) {
            @Override
            public void onCreate(final SQLiteDatabase db) {
                db.execSQL(KatalogModel.DB_CREATE_QUERY);
            }

            @Override
            public void onUpgrade(final SQLiteDatabase db,
                                  final int oldVersion, final int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + KatalogModel.TABLE_NAME);
                this.onCreate(db);
            }
        };

        this.database = this.helper.getWritableDatabase();
    }

    public void addKatalog(ContentValues data) {
        this.database.insert(KatalogModel.TABLE_NAME, null, data);
    }

    public void deleteKatalog(final String field_params) {
        this.database.delete(KatalogModel.TABLE_NAME, field_params, null);
    }

    public Cursor loadAllKatalog() {
        final Cursor c = this.database.query(KatalogModel.TABLE_NAME,
                new String[]{"nama"}, null, null, null, null, null);

        return c;
    }
    public Cursor loadOneKatalog(String nama) {
        final Cursor c = this.database.query(KatalogModel.TABLE_NAME,
                new String[]{"*"}, " nama ='"+nama+"'", null, null, null, null);

        return c;
    }
}

