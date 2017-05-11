package com.jaime.inventory.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by usuario on 20/04/17.
 */

public class Product implements Parcelable {
    private int id;
    private String serial;
    private String sortname;
    private String description;
    private int category;
    private int subcategory;
    private int productclass;


    public Product(String serial, String sortname, String description, int category, int subcategory, int productclass) {
        this.serial = serial;
        this.sortname = sortname;
        this.description = description;
        this.category = category;
        this.subcategory = subcategory;
        this.productclass = productclass;
    }


    public Product(int id, String serial, String sortname, String description, int category, int subcategory, int productclass) {
        this.id = id;
        this.serial = serial;
        this.sortname = sortname;
        this.description = description;
        this.category = category;
        this.subcategory = subcategory;
        this.productclass = productclass;
    }


    protected Product(Parcel in) {
        id = in.readInt();
        serial = in.readString();
        sortname = in.readString();
        description = in.readString();
        category = in.readInt();
        subcategory = in.readInt();
        productclass = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }

    public int getProductclass() {
        return productclass;
    }

    public void setProductclass(int productclass) {
        this.productclass = productclass;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", serial='" + serial + '\'' +
                ", sortname='" + sortname + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", subcategory=" + subcategory +
                ", productclass=" + productclass +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(serial);
        dest.writeString(sortname);
        dest.writeString(description);
        dest.writeInt(category);
        dest.writeInt(subcategory);
        dest.writeInt(productclass);
    }
}
