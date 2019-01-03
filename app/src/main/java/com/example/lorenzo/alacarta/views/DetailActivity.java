package com.example.lorenzo.alacarta.views;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lorenzo.alacarta.R;
import com.example.lorenzo.alacarta.Restaurante;
import com.example.lorenzo.alacarta.RestoAdapter;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    private TextView mNombreTv;
    private TextView mDireccionTv;
    private TextView mEspecialidadTv;
    private View card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        Restaurante clickedResto = (Restaurante) extras.getSerializable("mi_bundle");

        card = findViewById(R.id.listCard);
        mNombreTv = card.findViewById(R.id.nombreTv);
        mDireccionTv = card.findViewById(R.id.dirrecionTv);
        mEspecialidadTv = card.findViewById(R.id.especialidadTv);

        try {
            mNombreTv.setText(clickedResto.getNombre());
            mDireccionTv.setText(clickedResto.getDireccion());
            mEspecialidadTv.setText(clickedResto.getEspecialidad());
        } catch ( NullPointerException e){

        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.backButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.super.onBackPressed();
            }
        });


    }
}
