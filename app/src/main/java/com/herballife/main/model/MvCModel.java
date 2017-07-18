package com.herballife.main.model;

/**
 * Created by Achmad on 18/07/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public final class MvCModel {
    private static final String DB_NAME = "data2.sqlite";
    private static final String TABLE_NAME = "katalog";
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE_QUERY = "CREATE TABLE " +
            MvCModel.TABLE_NAME + " (id integer primary key autoincrement nama text not null);";

    private final SQLiteDatabase database;

    private final SQLiteOpenHelper helper;

    public MvCModel(final Context ctx) {
        this.helper = new SQLiteOpenHelper(ctx, MvCModel.DB_NAME,
                null, MvCModel.DB_VERSION) {
            @Override
            public void onCreate(final SQLiteDatabase db) {
                db.execSQL(MvCModel.DB_CREATE_QUERY);
            }

            @Override
            public void onUpgrade(final SQLiteDatabase db,
                                  final int oldVersion, final int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + MvCModel.TABLE_NAME);
                this.onCreate(db);
            }
        };

        this.database = this.helper.getWritableDatabase();
    }

    public void addKatalog(ContentValues data) {
        this.database.insert(MvCModel.TABLE_NAME, null, data);
    }

    public void deleteKatalog(final String field_params) {
        this.database.delete(MvCModel.TABLE_NAME, field_params, null);
    }

    public Cursor loadAllKatalog() {
        final Cursor c = this.database.query(MvCModel.TABLE_NAME,
                new String[]{"nama"}, null, null, null, null, null);

        return c;
    }
}

