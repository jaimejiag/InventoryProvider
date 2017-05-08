package com.jaime.inventory.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by usuario on 20/04/17.
 */

public class Subcategory implements Parcelable {
    private int id;
    private String name;
    private String sortname;
    private String description;
    private int idcategory;

    public Subcategory(int id, String name, String sortname, String description, int idcategory) {
        this.id = id;
        this.name = name;
        this.sortname = sortname;
        this.description = description;
        this.idcategory = idcategory;
    }


    protected Subcategory(Parcel in) {
        id = in.readInt();
        name = in.readString();
        sortname = in.readString();
        description = in.readString();
        idcategory = in.readInt();
    }


    public static final Creator<Subcategory> CREATOR = new Creator<Subcategory>() {
        @Override
        public Subcategory createFromParcel(Parcel in) {
            return new Subcategory(in);
        }

        @Override
        public Subcategory[] newArray(int size) {
            return new Subcategory[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }


    @Override
    public String toString() {
        return "Subcategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sortname='" + sortname + '\'' +
                ", description='" + description + '\'' +
                ", idcategory=" + idcategory +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(sortname);
        dest.writeString(description);
        dest.writeInt(idcategory);
    }
}
