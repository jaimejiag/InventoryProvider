package com.jaime.inventoryprovider.fragments;


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

import com.jaime.inventoryprovider.R;
import com.jaime.inventoryprovider.interfaces.CategoryPresenter;
import com.jaime.inventoryprovider.interfaces.SubcategoryPresenter;
import com.jaime.inventoryprovider.pojo.Product;
import com.jaime.inventoryprovider.presenter.CategoryPresenterImpl;
import com.jaime.inventoryprovider.presenter.SubcategoryPresenterImpl;


public class AddProductFragment extends Fragment implements CategoryPresenter.View,
        SubcategoryPresenter.View, AdapterView.OnItemSelectedListener {
    public static final String UPDATE_PRODUCT = "updateProduct";

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
    }


    @Override
    public void setCursorSubcategory(Cursor cursor) {
        mAdapterSubcategory.changeCursor(cursor);
    }


    @Override
    public void onResume() {
        super.onResume();

        if (getArguments() != null)
            setDataFields();
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
        int id;
        String serial = edtSerial.getText().toString();
        String sortname = edtSortname.getText().toString();
        String description = edtDescription.getText().toString();
        int category = ((Cursor) spCategory.getSelectedItem()).getInt(0);
        int subcategory = ((Cursor) spSubcategory.getSelectedItem()).getInt(0);

        Product product = new Product(serial, sortname, description, category, subcategory, 0);

        if (getArguments() == null)
            mPresenterCategory.petitionToAddProduct(product);
        else {
            id = ((Product)getArguments().getParcelable(UPDATE_PRODUCT)).getId();
            product.setId(id);
            mPresenterCategory.requestUpdateProduct(product);
        }
    }


    private void setDataFields() {
        Product product = getArguments().getParcelable(UPDATE_PRODUCT);

        edtSerial.setText(product.getSerial());
        edtSortname.setText(product.getSortname());
        edtDescription.setText(product.getDescription());

        if (product.getCategory() == 1)
            spCategory.setSelection(0);
        else if (product.getCategory() == 2)
            spCategory.setSelection(1);

        switch (product.getSubcategory()) {
            case 1:
                spSubcategory.setSelection(0);
                break;

            case 2:
                spSubcategory.setSelection(1);
                break;

            case 3:
                spSubcategory.setSelection(0);
                break;

            case 4:
                spSubcategory.setSelection(1);
                break;
        }
    }
}
