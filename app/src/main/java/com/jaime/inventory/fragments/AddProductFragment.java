package com.jaime.inventory.fragments;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.jaime.inventory.R;
import com.jaime.inventory.interfaces.CategoryPresenter;
import com.jaime.inventory.interfaces.SubcategoryPresenter;
import com.jaime.inventory.pojo.Product;
import com.jaime.inventory.presenter.CategoryPresenterImpl;
import com.jaime.inventory.presenter.SubcategoryPresenterImpl;


public class AddProductFragment extends Fragment implements CategoryPresenter.View,
        SubcategoryPresenter.View, AdapterView.OnItemSelectedListener {
    private Spinner spCategory;
    private Spinner spSubcategory;
    private EditText edtSerial;
    private EditText edtSortname;
    private EditText edtDescription;
    private Button btnAdd;

    private AddProductListener mCallBack;
    private CategoryPresenter mPresenterCategory;
    private SubcategoryPresenter mPresenterSubcategory;
    private SimpleCursorAdapter mAdapterCategory;
    private SimpleCursorAdapter mAdapterSubcategory;


    public interface AddProductListener {
        void onListProductListener();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (AddProductListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " Class cast fail");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterCategory = new CategoryPresenterImpl(this);
        mPresenterSubcategory = new SubcategoryPresenterImpl(this);

        mAdapterCategory = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item,
                null, mPresenterCategory.requestCategoryColumnName(), new int[] {android.R.id.text1},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mAdapterSubcategory = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item,
                null, mPresenterSubcategory.requestSubcategoryColumnName(), new int[] {android.R.id.text1},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mAdapterSubcategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_product, container, false);
        spCategory = (Spinner) rootView.findViewById(R.id.sp_category);
        spSubcategory = (Spinner) rootView.findViewById(R.id.sp_subcategory);
        edtSerial = (EditText) rootView.findViewById(R.id.edt_serial);
        edtSortname = (EditText) rootView.findViewById(R.id.edt_sortname);
        edtDescription = (EditText) rootView.findViewById(R.id.edt_description);
        btnAdd = (Button) rootView.findViewById(R.id.btn_add);

        spCategory.setOnItemSelectedListener(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendProductData();
                mCallBack.onListProductListener();
            }
        });

        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spCategory.setAdapter(mAdapterCategory);
        spSubcategory.setAdapter(mAdapterSubcategory);
    }


    @Override
    public void onStart() {
        super.onStart();
        mPresenterCategory.requestAllCategory();
    }


    @Override
    public void setCursorCategory(Cursor cursor) {
        mAdapterCategory.changeCursor(cursor);
        //mAdapterCategory.notifyDataSetChanged();
    }


    @Override
    public void setCursorSubcategory(Cursor cursor) {
        mAdapterSubcategory.changeCursor(cursor);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int idSpinner = adapterView.getId();

        switch (idSpinner) {
            case R.id.sp_category:
                int idCategory = ((Cursor)spCategory.getSelectedItem()).getInt(0);
                mPresenterSubcategory.requestSubcategorySelection(idCategory);
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void sendProductData() {
        String serial = edtSerial.getText().toString();
        String sortname = edtSortname.getText().toString();
        String description = edtDescription.getText().toString();
        int category = ((Cursor) spCategory.getSelectedItem()).getInt(0);
        int subcategory = ((Cursor) spSubcategory.getSelectedItem()).getInt(0);

        Product product = new Product(serial, sortname, description, category, subcategory, 0);
        mPresenterCategory.petitionToAddProduct(product);
    }
}
