package com.herballife.main.view;

/**
 * Created by Achmad on 18/07/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.herballife.main.R;
import com.herballife.main.controller.Detail_katalog;
import com.herballife.main.controller.List_catalog;

import java.util.ArrayList;
import java.util.List;

public class MVCView extends Activity {
    public static final String APP_TAG = "com.mrbool.mvc";
    public ListView list;
    int checked = -1;
    ArrayList<String> listitem1;
    ArrayAdapter<String> adapter;
    private List_catalog controller;


    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.list_katalog);
        this.controller = new List_catalog(this);
        list = (ListView) findViewById(R.id.list_tumbuhan);

        this.populateKatalog();

}

    private void populateKatalog() {
        final List<String> katalogs = this.controller.getKatalogs();
        Log.d(MVCView.APP_TAG, String.format("%d found tasks ", katalogs.size()));
        //adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listitem1);
        //list.setAdapter(adapter);
        this.list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, katalogs.toArray(new String[]{})));
        this.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
           /* public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                Log.d(MVCView.APP_TAG, String.format("task id: %d and position: %d", id, position));
                final TextView v = (TextView) view;
                MVCView.this.controller.deleteKatalog(v.getText().toString());
                MVCView.this.populateKatalog();
            }*/
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                checked = arg2;
                String tumbuhan = katalogs.get(checked);

                Intent tambah = new Intent(getApplicationContext(), Detail_katalog.class);
                tambah.putExtra("kirim_tumbuhan", tumbuhan);
                startActivity(tambah);
                //Toast.makeText(getApplicationContext(),"anda mengklick = " +listitem1.get(checked).toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
