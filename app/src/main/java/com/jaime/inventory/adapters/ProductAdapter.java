package com.jaime.inventory.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.jaime.inventory.R;

/**
 * Created by usuario on 27/04/17.
 */

public class ProductAdapter extends CursorAdapter {

    public ProductAdapter(Context context, Cursor c) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.product_item, parent, false);
        ProductHolder holder = new ProductHolder();

        holder.txvSerial = (TextView) rootView.findViewById(R.id.txv_serial);
        holder.txvSortname = (TextView) rootView.findViewById(R.id.txv_sortname);
        holder.txvDescription = (TextView) rootView.findViewById(R.id.txv_description);
        holder.txvCategory = (TextView) rootView.findViewById(R.id.txv_category);
        holder.txvSubcategory = (TextView) rootView.findViewById(R.id.txv_subcategory);
        holder.txvProductclass = (TextView) rootView.findViewById(R.id.txv_productclass);
        rootView.setTag(holder);

        return rootView;
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ProductHolder holder = (ProductHolder) view.getTag();

        holder.txvSerial.setText(cursor.getString(1));
        holder.txvSortname.setText(cursor.getString(2));
        holder.txvDescription.setText(cursor.getString(3));
        holder.txvCategory.setText(cursor.getInt(4));
        holder.txvSubcategory.setText(cursor.getInt(5));
        holder.txvProductclass.setText(cursor.getInt(6));
    }


    private class ProductHolder {
        TextView txvSerial;
        TextView txvSortname;
        TextView txvDescription;
        TextView txvCategory;
        TextView txvSubcategory;
        TextView txvProductclass;
    }
}
