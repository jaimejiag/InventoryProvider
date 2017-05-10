package com.jaime.inventory.fragments;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.jaime.inventory.R;
import com.jaime.inventory.interfaces.AddProductPresenter;
import com.jaime.inventory.presenter.AddProductPresenterImpl;


public class AddProductFragment extends Fragment implements AddProductPresenter.View,
        AdapterView.OnItemSelectedListener {
    private Spinner spCategory;
    private Spinner spSubcategory;

    private AddProductListener mCallBack;
    private AddProductPresenter mPresenter;
    private SimpleCursorAdapter mAdapterCategory;
    private SimpleCursorAdapter mAdapterSubcategory;
    private boolean mIsCategoryAdapterPopulated;


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
        mPresenter = new AddProductPresenterImpl(this);

        mAdapterCategory = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item,
                null, mPresenter.requestCategoryColumnName(), new int[] {android.R.id.text1},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mAdapterSubcategory = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item,
                null, mPresenter.requestSubcategoryColumnName(), new int[] {android.R.id.text1},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mAdapterSubcategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_product, container, false);
        spCategory = (Spinner) rootView.findViewById(R.id.sp_category);
        spCategory.setOnItemSelectedListener(this);
        spSubcategory = (Spinner) rootView.findViewById(R.id.sp_subcategory);
        spSubcategory.setOnItemSelectedListener(this);

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
        mIsCategoryAdapterPopulated = false;
        mPresenter.requestAllCategory();
    }


    @Override
    public void setCursor(Cursor cursor) {
        if (!mIsCategoryAdapterPopulated) {
            mAdapterCategory.changeCursor(cursor);
            mIsCategoryAdapterPopulated = true;
        } else
            mAdapterSubcategory.changeCursor(cursor);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int idSpinner = adapterView.getId();

        switch (idSpinner) {
            case R.id.sp_category:
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
                int idCategory = cursor.getInt(0);
                mPresenter.requestSubcategorySelection(idCategory);
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
