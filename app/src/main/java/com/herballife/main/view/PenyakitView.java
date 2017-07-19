package com.herballife.main.view;

/**
 * Created by Achmad on 18/07/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.herballife.main.R;
import com.herballife.main.controller.Detail;
import com.herballife.main.controller.PenyakitController;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.button;

public class PenyakitView extends Activity implements View.OnClickListener {
    public static final String APP_TAG = "com.mrbool.mvc";
    public ListView list;
    int checked = -1;
    Button button;
    ArrayList<String> listitem1;
    ArrayAdapter<String> adapter;
    private PenyakitController controller;


    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.list);
        this.controller = new PenyakitController(this);
        list = (ListView) findViewById(R.id.listpenyakit);

        this.populatePenyakit();
        button = (Button) findViewById(R.id.tombol_cari);
        button.setOnClickListener(this);
    }

    private void populatePenyakit() {
        final List<String> penyakits = this.controller.getPenyakits();
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, penyakits.toArray(new String[]{}));
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                checked = arg2;
                String penyakit = penyakits.get(checked);

                Intent tambah = new Intent(getApplicationContext(), Detail.class);
                tambah.putExtra("kirim_penyakit", penyakit);
                startActivity(tambah);
                //Toast.makeText(getApplicationContext(),"anda mengklick = " +listitem1.get(checked).toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent tombol = new Intent(this, Cari_Penyakit.class);
        startActivity(tombol);

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
