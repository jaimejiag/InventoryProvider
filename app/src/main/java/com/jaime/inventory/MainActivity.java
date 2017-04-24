package com.jaime.inventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaime.inventory.fragments.ListProductFragment;

public class MainActivity extends AppCompatActivity implements ListProductFragment.ListProductListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Se inicializa el fragment que permite añadir un producto.
     */
    @Override
    public void onAddProductListener() {

    }
}
