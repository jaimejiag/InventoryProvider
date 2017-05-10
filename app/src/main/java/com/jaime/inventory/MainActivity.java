package com.jaime.inventory;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaime.inventory.fragments.AddProductFragment;
import com.jaime.inventory.fragments.ListProductFragment;

public class MainActivity extends AppCompatActivity implements ListProductFragment.ListProductListener,
        AddProductFragment.AddProductListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ListProductFragment fragment = new ListProductFragment();
        transaction.replace(R.id.layout_main, fragment).commit();
    }


    /**
     * Se inicializa el fragment que permite añadir un producto.
     */
    @Override
    public void onAddProductListener() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        AddProductFragment fragment = new AddProductFragment();
        transaction.replace(R.id.layout_main, fragment).commit();
    }

    
    @Override
    public void onListProductListener() {

    }
}
