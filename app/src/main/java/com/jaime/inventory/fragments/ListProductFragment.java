package com.jaime.inventory.fragments;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaime.inventory.R;
import com.jaime.inventory.Loaders.ProductLoaderManager;
import com.jaime.inventory.adapters.ProductAdapter;
import com.jaime.inventory.interfaces.ProductPresenter;
import com.jaime.inventory.presenter.ProductPresenterImpl;

public class ListProductFragment extends ListFragment implements ProductPresenter.View{
    private ListProductListener mCallBack;
    private ProductPresenter presenter;
    private ProductLoaderManager mLoader;
    private ProductAdapter mAdapter;


    public interface ListProductListener {
        void onAddProductListener();
    }


    public ListProductFragment() {

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (ListProductListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " Class cast fail");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProductPresenterImpl(this);
        mAdapter = new ProductAdapter(getActivity(), null);
        //Se guarda el fragment en la pila de llamada.
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_product, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Se inserta el adapter en este método ya que las vistas están creadas.
        setListAdapter(mAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        presenter.requestAllProduct();
    }


    @Override
    public void setCursor(Cursor cursor) {
        mAdapter.changeCursor(cursor);
    }
}
