package com.jaime.inventory.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaime.inventory.R;


public class AddProductFragment extends Fragment {
    private AddProductListener mCallBack;


    public interface AddProductListener {
        void onListProductListener();
    }


    public AddProductFragment() {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }

}
