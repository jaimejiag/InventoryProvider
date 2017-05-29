package com.jaime.inventoryprovider.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.jaime.inventoryprovider.R;
import com.jaime.inventoryprovider.adapters.ProductAdapter;
import com.jaime.inventoryprovider.interfaces.ProductPresenter;
import com.jaime.inventoryprovider.pojo.Product;
import com.jaime.inventoryprovider.presenter.ProductPresenterImpl;

public class ListProductFragment extends ListFragment implements ProductPresenter.View {
    private FloatingActionButton fabAddProduct;
    private ListProductListener mCallBack;
    private ProductPresenter mPresenter;
    private ProductAdapter mAdapter;


    public interface ListProductListener {
        void onAddProductListener();
        void onUpdateProductListener(Product product);
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
        mPresenter = new ProductPresenterImpl(this);
        mAdapter = new ProductAdapter(getActivity(), null);
        //Se guarda el fragment en la pila de llamada.
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_product, container, false);
        fabAddProduct = (FloatingActionButton) rootView.findViewById(R.id.fab_addproduct);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Se inserta el adapter en este método ya que las vistas están creadas.
        setListAdapter(mAdapter);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product) mAdapter.getItem(position);
                showDeleteProductDialog(product.getId());
                return false;
            }
        });

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product) mAdapter.getItem(position);
                mCallBack.onUpdateProductListener(product);
            }
        });

        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onAddProductListener();
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        mPresenter.requestAllProduct();
    }


    @Override
    public void setCursor(Cursor cursor) {
        mAdapter.changeCursor(cursor);
    }


    private void showDeleteProductDialog(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Eliminar Producto")
                .setMessage("¿Desea eliminar el producto?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.requestDeleteProduct(id);
                        mPresenter.requestAllProduct();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
