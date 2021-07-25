package com.example.guardian_assignment.models;

import android.os.Parcelable;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields implements Parcelable
{

    @ColumnInfo(name = "body")
    private String body;

    @ColumnInfo(name = "thumbnail")
    private String thumbnail;

    public final static Creator<Fields> CREATOR = new Creator<Fields>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Fields createFromParcel(android.os.Parcel in) {
            return new Fields(in);
        }

        public Fields[] newArray(int size) {
            return (new Fields[size]);
        }

    };

    protected Fields(android.os.Parcel in) {
        this.body = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnail = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Fields() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Fields.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("body");
        sb.append('=');
        sb.append(((this.body == null)?"<null>":this.body));
        sb.append(',');
        sb.append("thumbnail");
        sb.append('=');
        sb.append(((this.thumbnail == null)?"<null>":this.thumbnail));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(body);
        dest.writeValue(thumbnail);
    }

    public int describeContents() {
        return 0;
    }

}
